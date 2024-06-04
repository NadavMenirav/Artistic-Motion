import java.util.Random;
import java.awt.Color;
import biuoop.DrawSurface;
/**
 * Ball class.
 */
public class Ball {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;

    /**
     * Constructor.
     * @param center center of ball
     * @param r radius of ball
     * @param color color of ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = new Point(center);
        this.radius = r;
        this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.velocity = new Velocity();
    }
    /**
     * Constructor.
     * @param x x value of center
     * @param y y value of center
     * @param r radius value of ball
     * @param color color of ball
     */
    public Ball(double x, double y, int r, Color color) {
        this((int) x, (int) y, r, color);
    }
    /**
     * Constructs a Point then a Ball utilising it.
     * Calls the Point based constructor
     * @param x the X value of the Point
     * @param y the Y value of the Point
     * @param r the radius value of the Ball
     * @param color the color of the Ball
     */
    public Ball(int x, int y, int r, Color color) {
        this(new Point(x, y), r, color);
    }
    /**
     * random constructor.
     * @param r radius of ball
     * @param color color of ball
     */
    public Ball(int r, Color color) {
        Random rand = new Random();
        boolean flag = false;
        double x = 0, y = 0;
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
     * returns x value of ball center.
     * @return x value of ball center
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * returns y value of ball center.
     * @return y value of ball center
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * returns radius of ball.
     * @return radius of ball
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * moves the center Point one step.
     */
    public void moveOneStep() {
        if (
            Threshold.isDoubleGreaterEqual(this.radius, this.center.getX())
            || Threshold.isDoubleGreaterEqual(this.center.getX(), 200 - this.radius)
        ) {
            this.velocity.setDx(-this.velocity.getDx());
        }
        if (
            Threshold.isDoubleGreaterEqual(this.radius, this.center.getY())
            || Threshold.isDoubleGreaterEqual(this.center.getY(), 200 - this.radius)
        ) {
            this.velocity.setDy(-this.velocity.getDy());
        }
        this.center = this.velocity.applyToPoint(this.center);
    }
    /**
     * changes Velocity of ball.
     * @param v new velocity of ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v);
    }
    /**
     * draws the ball on DrawSurface.
     * @param surface surface where we draw the ball
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }
    /**
     * Changes the Velocity of the Ball.
     * @param dx the new dx value of the Velocity
     * @param dy the new dy value of the Velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
    /**
     * Sets the center of the Ball.
     * @param newCenter the new center for the Ball
     */
    public void setCenter(Point newCenter) {
        this.center = new Point(newCenter);
    }
    /**
     * Sets the color of the Ball.
     * @param color new radius of the Ball
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * Sets the radius of the Ball.
     * @param radius new radius of the Ball
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }
}