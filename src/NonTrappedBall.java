import java.util.Random;
import java.awt.Color;
import biuoop.DrawSurface;
/**
 * NonTrappedBall class.
 */
public class NonTrappedBall {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    /**
     * Determines whether vertex touches.
     * @param vertex the vertex point
     * @return true if the touches and false otherwise
     */
    public boolean isTouchingVertex(Point vertex) {
        double x = vertex.getX();
        double y = vertex.getY();
        double a = this.center.getX();
        double b = this.center.getY();
        double r = this.radius;
        return Math.pow((x - a), 2) + Math.pow((y - b), 2) <= Math.pow(r, 2);
    }
    /**
     * checks if point is valid.
     * @param x x value of point
     * @param y y value of point
     * @param radius radius of Circle
     * @return true if point is valid, false otherwise
     */
    public static boolean isGeneratedPointValid(double x, double y, int radius) {
        boolean touchingGray = (
            Threshold.isDoubleGreaterEqual(x, 50 - radius)
            && Threshold.isDoubleGreaterEqual(500 + radius, x)
            && Threshold.isDoubleGreaterEqual(y, 50 - radius)
            && Threshold.isDoubleGreaterEqual(500 + radius, y)
        );
        boolean touchingYellow = (
            Threshold.isDoubleGreaterEqual(x, 450 - radius)
            && Threshold.isDoubleGreaterEqual(650 + radius, x)
            && Threshold.isDoubleGreaterEqual(y, 450 - radius)
            && Threshold.isDoubleGreaterEqual(650 + radius, y)
        );
        boolean touchingScreen = (
            Threshold.isDoubleGreaterEqual(radius, x)
            || Threshold.isDoubleGreaterEqual(radius, y)
            || Threshold.isDoubleGreaterEqual(x, 800 - radius)
            || Threshold.isDoubleGreaterEqual(y, 800 - radius)
        );
        return !touchingGray && !touchingYellow && !touchingScreen;
    }
    /**
     * random constructor.
     * @param r radius of non trapped ball
     * @param color color of non trapped ball
     */
    public NonTrappedBall(int r, Color color) {
        Random random = new Random();
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity();
        boolean found = false;
        while (!found) {
            Point randomPoint = new Point(800 * random.nextDouble(), 800 * random.nextDouble());
            if (NonTrappedBall.isGeneratedPointValid(randomPoint.getX(), randomPoint.getY(), r)) {
                this.center = randomPoint;
                found = true;
            }
        }
    }
    /**
     * Determines whether the Ball has a horizontal collision.
     * @return true if there is a collision and false otherwise
     */
    public boolean isTouchingHorizontally() {
        boolean isTouchingGreyLeft = (
            (
                Threshold.isDoubleGreaterEqual(this.center.getY(), 50)
                && Threshold.isDoubleGreaterEqual(500, this.center.getY())
                && Threshold.isDoubleGreaterEqual(50, this.center.getX())
                && Threshold.isDoubleGreaterEqual(this.center.getX(), 0)
                && Threshold.isDoubleGreaterEqual(this.center.getX(), 50 - this.radius)
            )
        );
        boolean isTouchingGreyRight = (
            (
                Threshold.isDoubleGreaterEqual(this.center.getY(), 50)
                && Threshold.isDoubleGreaterEqual(500, this.center.getY())
                && Threshold.isDoubleGreaterEqual(800, this.center.getX())
                && Threshold.isDoubleGreaterEqual(this.center.getX(), 500)
                && Threshold.isDoubleGreaterEqual(500 + this.radius, this.center.getX())
            )
        );
        boolean isTouchingYellowLeft = (
            Threshold.isDoubleGreaterEqual(this.center.getY(), 500)
            && Threshold.isDoubleGreaterEqual(650, this.center.getY())
            && Threshold.isDoubleGreaterEqual(this.center.getX(), 0)
            && Threshold.isDoubleGreaterEqual(450, this.center.getX())
            && Threshold.isDoubleGreaterEqual(this.center.getX(), 450 - this.radius)
        );
        boolean isTouchingYellowRight = (
            Threshold.isDoubleGreaterEqual(this.center.getY(), 450)
            && Threshold.isDoubleGreaterEqual(650, this.center.getY())
            && Threshold.isDoubleGreaterEqual(this.center.getX(), 650)
            && Threshold.isDoubleGreaterEqual(800, this.center.getX())
            && Threshold.isDoubleGreaterEqual(650 + this.radius, this.center.getX())
        );
        boolean isTouchingScreen = (
            Threshold.isDoubleGreaterEqual(this.radius, this.center.getX())
            || Threshold.isDoubleGreaterEqual(this.center.getX(), 800 - this.radius)
        );
        return (
            isTouchingGreyLeft
            || isTouchingGreyRight
            || isTouchingYellowLeft
            || isTouchingYellowRight
            || isTouchingScreen
            || isTouchingVertex(new Point(50, 50))
            || isTouchingVertex(new Point(450, 450))
            || isTouchingVertex(new Point(500, 500))
            || isTouchingVertex(new Point(650, 650))
        );
    }
    /**
     * Determines whether the Ball has a vertical collision.
     * @return true if there is a collision and false otherwise
     */
    public boolean isTouchingVertically() {
        boolean isTouchingScreen = (
            Threshold.isDoubleGreaterEqual(this.radius, this.center.getY())
            || Threshold.isDoubleGreaterEqual(this.center.getY(), 800 - this.radius)
        );
        boolean isTouchingGreyTop = (
            Threshold.isDoubleGreaterEqual(this.center.getY(), 0)
            && Threshold.isDoubleGreaterEqual(50, this.center.getY())
            && Threshold.isDoubleGreaterEqual(this.center.getX(), 50)
            && Threshold.isDoubleGreaterEqual(500, this.center.getX())
            && Threshold.isDoubleGreaterEqual(this.center.getY(), 50 - this.radius)
        );
        boolean isTouchingGreyBottom = (
            Threshold.isDoubleGreaterEqual(this.center.getY(), 500)
            && Threshold.isDoubleGreaterEqual(800, this.center.getY())
            && Threshold.isDoubleGreaterEqual(this.center.getX(), 50)
            && Threshold.isDoubleGreaterEqual(450, this.center.getX())
            && Threshold.isDoubleGreaterEqual(500 + this.radius, this.center.getY())
        );
        boolean isTouchingYellowTop = (
            Threshold.isDoubleGreaterEqual(this.center.getY(), 50)
            && Threshold.isDoubleGreaterEqual(450, this.center.getY())
            && Threshold.isDoubleGreaterEqual(this.center.getX(), 500)
            && Threshold.isDoubleGreaterEqual(650, this.center.getX())
            && Threshold.isDoubleGreaterEqual(this.center.getY(), 450 - this.radius)
        );
        boolean isTouchingYellowBottom = (
            Threshold.isDoubleGreaterEqual(this.center.getY(), 650)
            && Threshold.isDoubleGreaterEqual(800, this.center.getY())
            && Threshold.isDoubleGreaterEqual(this.center.getX(), 450)
            && Threshold.isDoubleGreaterEqual(650, this.center.getX())
            && Threshold.isDoubleGreaterEqual(450 + this.radius, this.center.getY())
        );

        return (
            isTouchingGreyBottom
            || isTouchingGreyTop
            || isTouchingYellowBottom
            || isTouchingYellowTop
            || isTouchingScreen
            || isTouchingVertex(new Point(50, 50))
            || isTouchingVertex(new Point(450, 450))
            || isTouchingVertex(new Point(500, 500))
            || isTouchingVertex(new Point(650, 650))
        );
    }
    /**
     * sets velocity.
     * @param velocity new velocity
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = new Velocity(velocity);
    }
    /**
     * draws the ball on DrawSurface.
     * @param surface surface where we draw the ball
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }
    /**
     * moves the center Point one step.
     */
    public void moveOneStep() {
        if (this.isTouchingVertically()) {
            this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
        }
        if (this.isTouchingHorizontally()) {
            this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
        }
        this.center = this.velocity.applyToPoint(this.center);
    }
    /**
     * returns radius of non trapped ball.
     * @return radius
     */
    public int getSize() {
        return this.radius;
    }
}