//Nadav Menirav 330845678
import java.awt.Color;

import biuoop.DrawSurface;

/**
 * Block class.
 */
public class Block implements Collidable, Sprite {
    //Fields of the Block class
    private final Rectangle shape;

    /**
     * Constructor of the block class.
     * @param shape The shape of the block
     */
    public Block(Rectangle shape) {
        this.shape = new Rectangle(shape);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(shape);
    }

    /**
     * The function checks whether the hitting Point is horizontal.
     * @param collisionPoint The collision point we check
     * @return true if the collision point is horizontal, false otherwise
     */
    public boolean isHittingPointHorizontally(Point collisionPoint) {
        /*
         * We know that if the object is hitting horizontally, its x value should be equals to one of the vertexes
         * and the y value should be between the top and bottom edges
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
         * and the x value should be between the left and right edges
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

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity);
        if (isHittingPointHorizontally(collisionPoint)) {
            newVelocity.setDx(-currentVelocity.getDx());
        }

        if (isHittingPointVertically(collisionPoint)) {
            newVelocity.setDy(-currentVelocity.getDy());
        }
        return new Velocity(newVelocity);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.fillRectangle(
                (int) this.shape.getUpperLeft().getX(),
                (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(),
                (int) this.shape.getHeight()
        );
    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * This method will add this Block to the given Game.
     * @param g The game we add this Block to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}