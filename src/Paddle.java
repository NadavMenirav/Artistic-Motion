//Nadav Menirav 330845678
import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import org.jetbrains.annotations.NotNull;


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
        final double currentSpeed = Math.sqrt(
                Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2)
        );
        // zone 1 -- 300 deg, and for every increase in region number, angle increases in 30 deg
        final int startingAngle = 300;
        final int desiredAngle;
        int region;
        Block block = new Block(this.shape);
        Velocity newVelocity = new Velocity(currentVelocity);
        if (block.isHittingPointHorizontally(collisionPoint)) {
            newVelocity.setDx(-currentVelocity.getDx());
            return newVelocity;
        }
        region = getRegionOfCollision(collisionPoint);
        desiredAngle = startingAngle + (region - 1) * 30;
        newVelocity = Velocity.fromAngleAndSpeed(desiredAngle, currentSpeed);
        return newVelocity;
    }

    /**
     * This method calculates wha region was the collision on.
     * @param collisionPoint The collision Point
     * @return The region the collision was occurred
     */
    public int getRegionOfCollision(Point collisionPoint) {
        final double upperLeftXValue = this.shape.getUpperLeft().getX();
        final double width = this.shape.getWidth();
        final double lengthOfRegion = 0.2 * width;
        if (collisionPoint.getX() < upperLeftXValue + lengthOfRegion) {
            return 1;
        }
        if (collisionPoint.getX() < upperLeftXValue  + 2 * lengthOfRegion) {
            return 2;
        }
        if (collisionPoint.getX() < upperLeftXValue + 3 * lengthOfRegion) {
            return 3;
        }
        if (collisionPoint.getX() < upperLeftXValue + 4 * lengthOfRegion) {
            return 4;
        }
        return 5;
    }

    /**
     * This method adds this Paddle to the game.
     * @param g The desired Game
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
        this.keyboard = g.getGui().getKeyboardSensor();
    }

}