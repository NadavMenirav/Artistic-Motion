package src;
//330845678 Nadav Menirav
/**
 * @author Nadav Menirav
 */
public class Line {
    //Fields of the Line class
    private Point start;
    private Point  end;
    /**
     * Constructor of the Line class.
     * @param start starting point of the line segment
     * @param end ending point of the line segment
     */
    public Line(Point start, Point end) {
        this.start = new Point(start);
        this.end = new Point(end);
    }
    /**
     * Constructor of the Line class.
     * @param x1 The x value of the starting point
     * @param y1 The y value of the starting point
     * @param x2 The x value of the ending point
     * @param y2 The y value of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    /**
     * The function returns the length of this line segment.
     * @return length of line segment
     */
    public double length() {
        return start.distance(end);
    }
    /**
     * The function returns the middle point of this line segment.
     * @return middle point
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }
    /**
     * The function returns the starting point of this line segment.
     * @return starting point
     */
    public Point start() {
        return new Point(this.start);
    }
    /**
     * The function return the ending point of this line segment.
     * @return ending point
     */
    public Point end() {
        return new Point(this.end);
    }
    /**
     * This function checks and returns the orientation of the point recieved as a parameter in regards to this line.
     * The orientation of the point means the direction the line has to rotate to in order to "reach" the point.
     * The direction can either be clock wise, counterclock wise, or non.
     * Further explanation can be found in the following video:
     * https://youtu.be/5FkOO1Wwb8w?si=aP7kjNEjkXPdi0Sa
     * It is self evident that the method below is my own and i did not copy any of it from the above video.
     * @param other the other point, we compare it to this line
     * @return 1 if the the line need to be rotated clockwise, -1 if counterclock wise, and 0 if no rotation is needed
     */
    public int getOrientation(Point other) {
        double value = (this.end.getX() - this.start.getX()) * (other.getY() - this.end.getY())
                        - (this.end.getY() - this.start.getY()) * (other.getX() - this.end.getX());
        if (Threshold.isDoublesEqual(value, 0)) { //Checking if value is 0 using threshold
            return 0; //Means they are collinear
        }
        return (value > 0) ? 1 : -1; //Do we need to move clockwish or counter clockwise
    }
    public boolean isIntersecting(Line other) {
        if (this.equals(other)) { //If they are the same we shall return true
            return true;
        }
        if (this.getSlope() == other.getSlope()) { //Same slope but not the same - parallel
            return false;
        }
        double m1 = this.getSlope(), m2 = other.getSlope(), b1 = this.getConstant(), b2 = other.getConstant();
        //y = m1x + b1, y = m2x + b2
        //m1x-m2x = -(b1-b2)
        double xIntersection = -(b1 - b2) / (m1 - m2); //solving the two equations
        double maxXValue = Math.max(this.start.getX(), this.end.getX());
        double minXValue = Math.min(this.start.getX(), this.end.getY());
        //Is the intersection point on the line segment
        if (Threshold.isDoubleGreaterEqual(xIntersection, minXValue)
            && Threshold.isDoubleGreaterEqual(maxXValue, xIntersection)) {
                return true;
            }
        return false;
    }
    /**
     * The function checks if the two line segments intersect with this line.
     * @param other1 first line segment
     * @param other2 second line segment
     * @return true if they both intersect with this line, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return (this.isIntersecting(other1) && this.isIntersecting(other2));
    }
    /**
     * The function returns the intersection point of two line segments.
     * @param other
     * @return intersection point if intersecting, and null otherwise
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        //Calculation from isIntersecting
        double m1 = this.getSlope(), m2 = other.getSlope(), b1 = this.getConstant(), b2 = other.getConstant();
        double xIntersection = -(b1 - b2) / (m1 - m2);
        double yIntersection = m1 * xIntersection + b1; //substituing xIntersection in the line segment equation
        return new Point(xIntersection, yIntersection);
    }
    /**
     * The function check if this line is the same visualy as another line.
     * @param other the other line
     * @return true if they are the same visualy, false otherwise
     */
    public boolean equals(Line other) {
        boolean sameOrder = (this.start.equals(other.start()) && this.end.equals(other.end())); //start=start
        boolean difOrder = (this.start.equals(other.end()) && this.end.equals(other.start())); //start=end
        return sameOrder || difOrder;
    }
}
