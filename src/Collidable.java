//Nadav Menirav 330845678

/**
 * Collidable interface, has the methods that will be implemented in other classes.
 */
public interface Collidable {
    /**
     * The function returns the 'collision shape' of the object.
     *
     * @return The collision shape of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * The function will be used whenever an object hits a collidable one.
     * It will do the necessary changes and will return the new velocity of the hitting object
     *
     * @param collisionPoint  The collision point between the hitting object and the collidable one
     * @param currentVelocity The current velocity of the hitting object
     * @return The new Velocity that needs to be applied on the hitting object
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);

}
