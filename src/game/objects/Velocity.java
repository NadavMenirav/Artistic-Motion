//Nadav Menirav 330845678

package game.objects;

import geometry.Point;

/**
 * Velocity class.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor of the Velocity class.
     * @param dx Delta of x
     * @param dy Delta of y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Empty constructor of Velocity class.
     */
    public Velocity() {
        this(0, 0);
    }

    /**
     * Copy constructor of Velocity.
     * @param velocity The Velocity object to be copied
     */
    public Velocity(Velocity velocity) {
        this(velocity.getDx(), velocity.getDy());
    }

    /**
     * Returns the Velocity object's dy value.
     * @return The Velocity object's dy value
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Returns the Velocity object's dx value.
     * @return The Velocity object's dx value
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Sets the dy value of the Velocity.
     * @param dy The new value
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Sets the dx value of the Velocity.
     * @param dx The new value
     */

    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Creates a new Velocity object from angle and speed.
     * @param angle the angle of the Velocity instance
     * @param speed the speed of the Velocity instance
     * @return the new Velocity instance
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Parses the angle provided to an angle which can be used in regular trigonometric calculations
        // (Up == 90 degrees)
        double normalAngle = angle + 90;
        double angleInRadians = (normalAngle * 2 * Math.PI) / 360;
        double dx = speed * Math.cos(angleInRadians);
        // Normal cartesian axis' consider upward motion to be represented by positive
        // change of coordinates.
        // The computer screen's representation of the axis' uses a Matrix
        // in which the positive change of coordinates represents downward motion
        double dy = -speed * Math.sin(angleInRadians);
        return new Velocity(dx, dy);
    }

    /**
     * Slightly tweaks coordinates of the Point.
     * @param p A Point to which we must apply the effect
     * @return A new Point with the tweaked values
     */
    public Point applyToPoint(Point p) {
        return new Point(
            p.getX() + this.dx,
            p.getY() + this.dy
        );
    }

    /**
     * Slightly tweaks coordinates of the point, so it will reach very closely to the point it was supposed to hit.
     * It is used when we want to bring the ball to almost reach the collision point
     * @param p A point to which we must apply the effect
     * @param intersection The intersection Point
     * @param changeParam How close we want to move the Point to the intersection Point
     * @return A new Point with the tweaked values
     */
    public Point moveCloseToIntersection(Point p, Point intersection, double changeParam) {
        double newX = p.getX() + (intersection.getX() - p.getX()) * changeParam;
        double newY = p.getY() + (intersection.getY() - p.getY()) * changeParam;
        return new Point(newX, newY);
    }
}