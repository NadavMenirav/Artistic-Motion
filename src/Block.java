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
    public Rectangle getCollisionShape() {
        return new Rectangle(shape);
    }
}