//Nadav Menirav 330845678
import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;



/**
 * Paddle class.
 */
public class Paddle implements Sprite, Collidable {
    //Fields of the Paddle class
    private final Rectangle shape = new Rectangle(new Point(250, 750), 100, 50);
    private biuoop.KeyboardSensor keyboard;

    /**
     * Slightly moves the paddle to the left.
     */
    public void moveLeft() {
        shape.setUpperLeftXValue(shape.getUpperLeft().getX() - 1);
    }

    /**
     * Slightly moves the paddle to the right.
     */
    public void moveRight() {
        shape.setUpperLeftXValue(shape.getUpperLeft().getX() + 1);
    }

    //Sprite methods
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }

        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(
                (int) this.shape.getUpperLeft().getX(),
                (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(),
                (int) this.shape.getHeight()
        );
    }

    //Collidable methods
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.shape);
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {

    }
}