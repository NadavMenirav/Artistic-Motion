/**
 * Point class.
 */
public class Point {
    private double x;
    private double y;
    /**
     * Constructor of the point class.
     * @param x x value of the point
     * @param y y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Constructor of the point class.
     * @param a the point we want to duplicate
     */
    public Point(Point a) {
        this.x = a.getX();
        this.y = a.getY();
    }
    /**
     * The function calculates the distance between this point and an other point recieved as a parameter.
     * @param other another point object
     * @return the distance
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }
    /**
     * The function checks if this point has the same values as the other point, recieved as a parameter.
     * @param other another point object
     * @return true if they have the same value, false otherwise
     */
    public boolean equals(Point other) {
        return (Threshold.isDoublesEqual(this.x, other.getX()) && Threshold.isDoublesEqual(this.y, other.getY()));
    }
    /**
     * The function returns the x value of this point.
     * @return x value
     */
    public double getX() {
        return this.x;
    }
    /**
     * The function returns the y value of this point.
     * @return y value
     */
    public double getY() {
        return this.y;
    }
    /**
     * The function changes the x value of this point.
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