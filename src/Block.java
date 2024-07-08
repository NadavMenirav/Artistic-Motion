//Nadav Menirav 330845678

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * Block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //Fields of the Block class
    private final Rectangle shape;
    private final Color color;
    private final List<HitListener> hitListeners;

    /**
     * Constructor of the Block class.
     * @param shape The shape of the block
     */
    public Block(Rectangle shape) {
        //If no color was provided, the block will be black
        this.shape = new Rectangle(shape);
        this.color = Color.BLACK;
        hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Constructor of the Block class.
     * @param shape The shape of the block
     * @param color The color of the block
     */
    public Block(Rectangle shape, Color color) {
        this.shape = new Rectangle(shape);
        this.color = new Color(color.getRGB());
        hitListeners = new ArrayList<HitListener>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(shape);
    }

    /**
     * The function checks whether the hitting Point is horizontal.
     * @param collisionPoint The collision point we check
     * @return True if the collision point is horizontal, false otherwise
     */
    public boolean isHittingPointHorizontally(Point collisionPoint) {
        /*
         * We know that if the object is hitting horizontally, its x value should be equals to one of the vertexes
         * and the y value should be equal to the top or bottom edges
         */
        boolean isYInRange =
                Threshold.isDoubleGreaterEqual(
                        this.shape.getBottomLeft().getY(),
                        collisionPoint.getY()
                )
                        && Threshold.isDoubleGreaterEqual(
                        collisionPoint.getY(),
                        this.shape.getUpperLeft().getY()
                );

        boolean hittingLeftEdge =
                Threshold.isDoublesEqual(collisionPoint.getX(), this.shape.getUpperLeft().getX())
                        && isYInRange;


        boolean hittingRightEdge =
                Threshold.isDoublesEqual(
                        this.shape.getUpperRight().getX(),
                        collisionPoint.getX()
                )
                        && isYInRange;

        return hittingLeftEdge || hittingRightEdge;
    }

    /**
     * The function checks whether the hitting Point is Vertical.
     * @param collisionPoint The collision point we check
     * @return true if the collision point is vertical, false otherwise
     */
    public boolean isHittingPointVertically(Point collisionPoint) {
        /*
         * We know that if the object is hitting vertically, its y value should be equals to one of the vertexes
         * and the x value should be equal to the left or right edges
         */

        boolean isXInRange =
                Threshold.isDoubleGreaterEqual(
                        this.shape.getUpperRight().getX(),
                        collisionPoint.getX()
                )
                        && Threshold.isDoubleGreaterEqual(
                        collisionPoint.getX(),
                        this.shape.getUpperLeft().getX()
                );

        boolean hittingTopEdge =
                Threshold.isDoublesEqual(collisionPoint.getY(), this.shape.getUpperLeft().getY())
                        && isXInRange;

        boolean hittingBottomEdge =
                Threshold.isDoublesEqual(
                        this.shape.getBottomLeft().getY(),
                        collisionPoint.getY()
                )
                        && isXInRange;

        return hittingTopEdge || hittingBottomEdge;
    }

    /**
     * This method checks whether a Ball has the same color as this Block.
     * @param ball The ball we check its color
     * @return True of they have the same color, false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color.getRed() == ball.getColor().getRed()
        && this.color.getGreen() == ball.getColor().getGreen()
        && this.color.getBlue() == ball.getColor().getBlue();
    }

    /**
     * This method removes a block from the given Game.
     * @param game The game we remove this ball from
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        /*
         * If the collision point was on the top or bottom edges, we will change the dy value of the velocity,
         * and if it was on the right or left edges, we will change the dx
         */
        Velocity newVelocity = new Velocity(currentVelocity);
        if (isHittingPointHorizontally(collisionPoint)) {
            newVelocity.setDx(-currentVelocity.getDx());
        }

        if (isHittingPointVertically(collisionPoint)) {
            newVelocity.setDy(-currentVelocity.getDy());
        }

        if (!ballColorMatch(hitter)) {
                this.notifyHit(hitter);
        }

        return new Velocity(newVelocity);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        //We want to fill the rectangle with its color, and give it a black outline
        surface.setColor(this.color);
        surface.fillRectangle(
                (int) this.shape.getUpperLeft().getX(),
                (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(),
                (int) this.shape.getHeight()
        );
        surface.setColor(Color.BLACK);
        surface.drawRectangle(
                (int) this.shape.getUpperLeft().getX(),
                (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(),
                (int) this.shape.getHeight()
        );
    }

    @Override
    public void timePassed() {
    }

    /**
     * This method will add this Block to the given Game.
     * @param g The game we add this Block to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Notifies after being hit.
     * @param hitter The ball that hits
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
        hl.hitEvent(this, hitter);
        }
    }
}