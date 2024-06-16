//Nadav Menirav 330845678

import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Paddle class.
 */
public class Paddle implements Sprite, Collidable {
    //Fields of the Paddle class

    //we want the paddle to have a specific starting Point.
    private final Rectangle shape = new Rectangle(new Point(350, 575), 125, 25);
    private biuoop.KeyboardSensor keyboard;
    private final Color color;

    /**
     * Constructor of the Paddle class.
     */
    public Paddle() {
        //If no color was assigned, this Paddle will be black
        this.color = Color.BLACK;
    }

    /**
     * Constructor of the Paddle class.
     * @param color The color of the Paddle
     */
    public Paddle(Color color) {
        this.color = new Color(color.getRGB());
    }

    /**
     * Slightly moves the paddle to the left.
     */
    public void moveLeft() {
        //Circular fashion
        final int changeValue = 8;
        if (Threshold.isDoubleGreaterEqual(changeValue, this.shape.getUpperRight().getX())) {
            this.shape.setUpperLeftXValue(800);
        }
        shape.setUpperLeftXValue(shape.getUpperLeft().getX() - changeValue);
    }

    /**
     * Slightly moves the paddle to the right.
     */
    public void moveRight() {
        //Circular fashion
        final int changeValue = 8;
        if (Threshold.isDoubleGreaterEqual(this.shape.getUpperLeft().getX(), 800 - changeValue)) {
            this.shape.setUpperLeftXValue(-this.shape.getWidth());
        }
        shape.setUpperLeftXValue(shape.getUpperLeft().getX() + changeValue);
    }

    //Sprite methods
    @Override
    public void timePassed() {
        //Move this Paddle according to the key pressed
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }

        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        //We want to fill this paddle with its assigned color, and give it a black outline
        d.setColor(this.color);
        d.fillRectangle(
                (int) this.shape.getUpperLeft().getX(),
                (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(),
                (int) this.shape.getHeight()
        );
        d.setColor(Color.BLACK);
        d.drawRectangle(
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
        //Fun paddle part, we need to change the velocity based on the collision region
        final double currentSpeed = Math.sqrt(
                Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2)
        ); //Using pythagoras to calculate the speed

        // zone 1 -- 60 deg (to the positive), and for every increase in region number, angle increases in 30 deg
        final int startingAngle = 60;
        final int desiredAngle;
        final int region;
        final Block block = new Block(this.shape);
        Velocity newVelocity;

        //If the collision point is horizontally, the collision is no different from the block collision.
        if (block.isHittingPointHorizontally(collisionPoint)) {
            return block.hit(collisionPoint, currentVelocity);
        }

        //We know now that the collision is vertical and the new Velocity will be determined by the fun paddle part
        region = getRegionOfCollision(collisionPoint);
        desiredAngle = startingAngle - (region - 1) * 30;
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
        final double lengthOfRegion = 0.2 * width; //We know that a region is a fifth of the Paddle
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