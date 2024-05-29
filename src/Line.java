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
     * It is Given that the point recieved as parameter is collinear with this line.
     * The function checks if the point is on the line segment
     * @param other the point we check
     * @return true if it is on the line segment, false otherwise.
     */
    public boolean isPointOnLineSegment(Point other) {
        //Checking if x value of the point is bigger than the leftmost point on the line segment.
        boolean xBiggerThanMin = (Threshold.isDoubleGreaterEqual(other.getX(),
                                Math.min(this.start.getX(), this.end.getX())));

        //Checking if x value of the point is smaller than the rightmost point on the line segment.
        boolean xSmallerThanMax = (Threshold.isDoubleGreaterEqual(Math.max(this.start.getX(), this.end.getX()),
                                other.getX()));

        //Checking if y value of the point is bigger than the downmost point on the line segment.
        boolean yBiggerThanMin = (Threshold.isDoubleGreaterEqual(other.getY(),
                                Math.min(this.start.getY(), this.end.getY())));


        //Checking if y value of the point is smaller than the upmost point on the line segment.
        boolean ySmallerThanMax = (Threshold.isDoubleGreaterEqual(Math.max(this.start.getY(), this.end.getY()),
                                other.getY()));

        //We shall return true only if they are all true
        return xBiggerThanMin && xSmallerThanMax && yBiggerThanMin && ySmallerThanMax;
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
    public int getPointOrientation(Point other) {
        double value = (this.end.getX() - this.start.getX()) * (other.getY() - this.end.getY())
                        - (this.end.getY() - this.start.getY()) * (other.getX() - this.end.getX());
        if (Threshold.isDoublesEqual(value, 0)) { //Checking if value is 0 using threshold
            return 0; //Means they are collinear
        }
        return (value > 0) ? 1 : -1; //Do we need to move clockwise or counterclock wise
    }
    /**
     * The function checks if two line segments intersect.
     * It does that by doing the following checks:
     * 1. If o1 != o2 and o3 != o4, the line segment create an "X" shape which will resuly in an intersection
     * 2. If one of the orientations are 0, that means that the point represented in this orientation falls
     *      on the same line the other line segment creates. Then we can check if this point's x value falls
     *      between the other line segment's min and max X values to ensure there is in fact an intersecion.
     * @param other the other line we check
     * @return true if they intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        int o1 = this.getPointOrientation(other.start());
        int o2 = this.getPointOrientation(other.end());
        int o3 = other.getPointOrientation(this.start);
        int o4 = other.getPointOrientation(this.end);

        //This is where the line segments create an X shape
        if (o1 != o2 && o2 != o3) {
            return true;
        }

        /*
        Now we know that one of the edge points in on the infinite line of the other line segment,
        so we need to check if this point falls on the other line segment
        */
        return (
            (o1 == 0 && this.isPointOnLineSegment(other.start()))
            || (o2 == 0 && this.isPointOnLineSegment(other.end()))
            || (o3 == 0 && other.isPointOnLineSegment(this.start))
            || (o4 == 0 && other.isPointOnLineSegment(this.end))
        );
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
     * This function checks using threshold if this line segment is perpendicular to the X axis.
     * @return true if perpendicular, false otherwise
     */
    public boolean isPerpendicularXAxis() {
        return Threshold.isDoublesEqual(this.start.getX(), this.end.getX());
    }
    /**
     * The function returns the y value of the line at a given x value.
     * It assumes the line is not perpendicular to X axis and that the given x value is in range of the edge points
     * @param x the given x value we want to find the y value in
     * @return intersection point if intersecting, and null otherwise
     */
    public int getYValueInX(int x) {
        return x;
    }
    /**
     * The function return the intersection point of two line segments, if they intersect.
     * @param other the other line
     * @return Intersection point if they intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        return null;
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
