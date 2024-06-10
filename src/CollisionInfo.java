//Nadav Menirav 330845678
public class CollisionInfo {
    //Fields of the CollisionInfo class
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructor of the CollisionInfo class.
     * @param collisionPoint The collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = new Point(collisionPoint);
        this.collisionObject = collisionObject; //Encapsulation?
    }

    /**
     * Getter of the collisionPoint field.
     * @return A copy of this collision point
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * Getter of the collisionObject field.
     * @return A copy of this collision object
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}