//330845678 Nadav Menirav
/**
 * @author Nadav Meniav
 */
public class Point {
    private double x;
    private double y;
    /**
     * constructor of the point.
     * @param x x value of the point
     * @param y y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * The function calculates the distance between this point and an other point recieved as a parameter.
     * @param other
     * @return the distance
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
    /**
     * The function checks if this point has the same values as the other point, recieved as a parameter.
     * @param other
     * @return boolean value representing if they do in fact have the same values
     */
    public boolean equals(Point other) {
        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        return false;
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
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * The function changes the y value of this point.
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }
}