//Nadav Menirav 330845678

package geometry;

import utils.Threshold;

/**
 * Point class.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor of the point class.
     * @param x X value of the point
     * @param y Y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor of the point class.
     * @param a The point we want to duplicate
     */
    public Point(Point a) {
        this.x = a.getX();
        this.y = a.getY();
    }

    /**
     * The method calculates the distance between this point and another point received as a parameter.
     * @param other Another point object
     * @return The distance
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }

    /**
     * The method checks if this point has the same values as the other point, received as a parameter.
     * @param other Another point object
     * @return True if they have the same value, false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return (Threshold.isDoublesEqual(this.x, other.getX()) && Threshold.isDoublesEqual(this.y, other.getY()));
    }

    /**
     * The method returns the x value of this point.
     * @return X value
     */
    public double getX() {
        return this.x;
    }

    /**
     * The method returns the y value of this point.
     * @return Y value
     */
    public double getY() {
        return this.y;
    }

    /**
     * The method changes the x value of this point.
     * @param x x value of this point
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * The function changes the y value of this point.
     * @param y y value if this point
     */
    public void setY(double y) {
        this.y = y;
    }
}