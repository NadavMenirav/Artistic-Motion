//Nadav Menirav 330845678

import biuoop.DrawSurface;
import java.awt.Color;

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

    /**
     * Getter of the shape field.
     * @return A copy of the shape field
     */
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
                        this.shape.getUpperLeft().getY() + this.shape.getHeight(),
                        collisionPoint.getY()
                )
                && Threshold.isDoubleGreaterEqual(
                        collisionPoint.getY(),
                        this.shape.getUpperLeft().getY()
                );

        return Threshold.isDoubleGreaterEqual(
                collisionPoint.getX(),
                this.shape.getUpperLeft().getX()
        )
                && Threshold.isDoubleGreaterEqual(
                    this.shape.getUpperLeft().getX() + this.shape.getWidth(),
                    collisionPoint.getX()
                )
                && isYInRange;
    }

    /**
     * The function checks whether the hitting Point is vertical.
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
                        this.shape.getUpperLeft().getX() + this.shape.getWidth(),
                        collisionPoint.getX()
                )
                && Threshold.isDoubleGreaterEqual(
                        collisionPoint.getX(),
                        this.shape.getUpperLeft().getX()
                );
        return Threshold.isDoubleGreaterEqual(
                collisionPoint.getY(),
                this.shape.getUpperLeft().getY()
        )
                && Threshold.isDoubleGreaterEqual(
                this.shape.getUpperLeft().getY() + this.shape.getHeight(),
                collisionPoint.getY()
        )
                && isXInRange;
    }

    /**
     * The function will be used whenever an object hits this block.
     * It will do the necessary changes and will return the new velocity of the hitting object
     * @param collisionPoint The collision point between the hitting object and the collidable one
     * @param currentVelocity The current velocity of the hitting object
     * @return The new Velocity that needs to be applied on the hitting object
     */
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

    /**
     * This method draws this block on the provided DrawSurface.
     * @param surface The drawSurface we draw the ball on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.fillRectangle(
                (int) this.shape.getUpperLeft().getX(),
                (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(),
                (int) this.shape.getHeight()
        );
    }

    /**
     * This function notifies the Block that a certain period of tim has passed.
     * For now, it does not do anything
     */
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