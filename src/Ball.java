//Nadav Menirav 330845678

import java.util.Random;
import java.awt.Color;
import biuoop.DrawSurface;

/**
 * Ball class.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private Paddle paddle;

    /**
     * Constructor of the Ball class.
     * @param center Center of ball
     * @param r Radius of ball
     * @param color Color of ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = new Point(center);
        this.radius = r;
        this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.velocity = new Velocity();
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * Constructor of the Ball class.
     * @param x The x value of center
     * @param y The y value of center
     * @param r The radius value of ball
     * @param color The color of ball
     */
    public Ball(double x, double y, int r, Color color) {
        this((int) x, (int) y, r, color);
    }

    /**
     * Constructs a Point then a Ball utilizing it.
     * Calls the Point based constructor
     * @param x The X value of the Point
     * @param y The Y value of the Point
     * @param r The radius value of the Ball
     * @param color The color of the Ball
     */
    public Ball(int x, int y, int r, Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * Random constructor of the Ball class.
     * @param r Radius of ball
     * @param color Color of ball
     */
    public Ball(int r, Color color) {
        Random rand = new Random();
        boolean flag = false;
        double x, y;
        //The loop creates a random Ball until the ball it creates is valid
        while (!flag) {
            x = rand.nextInt(201);
            y = rand.nextInt(201);
            boolean edgeCollision = (Threshold.isDoubleGreaterEqual(this.radius, x)
                    && Threshold.isDoubleGreaterEqual(this.radius, y)
                    && Threshold.isDoubleGreaterEqual(x, 195 - this.radius)
                    && Threshold.isDoubleGreaterEqual(y, 195 - this.radius)
            );
            if (!edgeCollision) {
                this.center = new Point(x, y);
                flag = true;
            }
        }
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity();
    }

    /**
     * Get the x value of the center.
     * @return X value of ball center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Get the y value of the center.
     * @return Y value of ball center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Getter of the radius field.
     * @return Radius of ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Getter of the Color field.
     * @return The color of this Ball.
     */
    public Color getColor() {
        return new Color(this.color.getRGB());
    }

    /**
     * Changes Velocity of ball.
     * @param v new velocity of ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v);
    }

    /**
     * Changes the Velocity of the Ball.
     * @param dx The new dx value of the Velocity
     * @param dy The new dy value of the Velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Sets the center of the Ball.
     * @param newCenter The new center for the Ball
     */
    public void setCenter(Point newCenter) {
        this.center = new Point(newCenter);
    }

    /**
     * Sets the color of the Ball.
     * @param color New radius of the Ball
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the radius of the Ball.
     *
     * @param radius new radius of the Ball
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * Moves the center Point one step.
     */
    public void moveOneStep() {
        final double changeParam = 0.9;
        Line trajectory = new Line(this.center, this.velocity.applyToPoint(this.center));
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);

        //If this Ball is in the Paddle, we need to get it out
        if (this.isBallInPaddle()) {
            this.getOutOfPaddle();
            return;
        }

        //If there is now collision
        if (collisionInfo == null) {
            this.center = this.velocity.applyToPoint(this.center);
            return;
        }

        /*
         * Now we know this Ball collides with a collidable object, so we need to bring it close to the collision point
         * and change its velocity
         */
        Collidable collisionObject = collisionInfo.collisionObject();
        Point collisionPoint = collisionInfo.collisionPoint();
        this.center = this.velocity.moveCloseToIntersection(this.center, collisionPoint, changeParam);

        this.velocity = collisionObject.hit(this, collisionPoint, this.velocity);
    }

    /**
     * This method adds a collidable to this object's gameEnvironment.
     * @param c The collidable we add
     */
    public void addCollidable(Collidable c) {
        this.gameEnvironment.addCollidable(c);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * This method adds the Ball to the given Game.
     * @param g The game we add the Ball to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        this.gameEnvironment = g.getEnvironment();
        this.paddle = g.getPaddle();
    }

    /**
     * Removes this ball from the given Game.
     * @param game The game we remove this ball from
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }

    /**
     * This method is used to get this Ball out of its Paddle.
     */
    public void getOutOfPaddle() {
        //If this Ball is in the Paddle, we will change its y value, so it will be just above it
        final int desiredDistanceFromTopEdge = 1;
        this.center.setY(this.paddle.getCollisionRectangle().getUpperLeft().getY() - desiredDistanceFromTopEdge);

    }

    /**
     * This method checks whether this Ball is in its related Paddle.
     * @return True if this Ball is in its Paddle, false otherwise
     */
    public boolean isBallInPaddle() {
        //We check if this Ball is in the Paddle by checking if its coordinates are between the Paddle's coordinates
        final double lowestYValueOfPaddle = this.paddle.getCollisionRectangle().getUpperLeft().getY();
        final double highestYValueOfPaddle = this.paddle.getCollisionRectangle().getBottomLeft().getY();
        final double lowestXValueOfPaddle = this.paddle.getCollisionRectangle().getUpperLeft().getX();
        final double highestXValueOfPaddle = this.paddle.getCollisionRectangle().getUpperRight().getX();
        final double xBall = this.center.getX();
        final double yBall = this.center.getY();
        boolean isYInPaddle = Threshold.isDoubleGreaterEqual(yBall, lowestYValueOfPaddle)
                            && Threshold.isDoubleGreaterEqual(highestYValueOfPaddle, yBall);
        boolean isXInPaddle = Threshold.isDoubleGreaterEqual(xBall, lowestXValueOfPaddle)
                            && Threshold.isDoubleGreaterEqual(highestXValueOfPaddle, xBall);

        return isYInPaddle && isXInPaddle;
    }
}