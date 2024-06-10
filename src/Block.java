//Nadav Menirav 330845678

/**
 * Block class.
 */
public class Block implements Collidable {
    //Fields of the Block class
    private Rectangle shape;

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

        boolean hittingLeftEdge =
                Threshold.isDoubleGreaterEqual(collisionPoint.getX(), this.shape.getUpperLeft().getX())
                && isYInRange;


        boolean hittingRightEdge =
                Threshold.isDoubleGreaterEqual(
                        this.shape.getUpperLeft().getX() + this.shape.getWidth(),
                        collisionPoint.getX()
                )
                && isYInRange;

        return hittingLeftEdge || hittingRightEdge;
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

        boolean hittingTopEdge =
                Threshold.isDoubleGreaterEqual(collisionPoint.getY(), this.shape.getUpperLeft().getY())
                && isXInRange;

        boolean hittingBottomEdge =
                Threshold.isDoubleGreaterEqual(
                        this.shape.getUpperLeft().getY() + this.shape.getHeight(),
                        collisionPoint.getY()
                )
                && isXInRange;

        return hittingTopEdge || hittingBottomEdge;
    }

    /**
     * The function will be used whenever an object hits this block.
     * It will do the necessary changes and will return the new velocity of the hitting object
     * @param collisionPoint The collision point between the hitting object and the collidable one
     * @param currentVelocity The current velocity of the hitting object
     * @return The new Velocity that needs to be applied on the hitting object
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {

    }
}