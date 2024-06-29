//Nadav Menirav 330845678

import java.util.List;

/**
 * Line class.
 */
public class Line {
    //Fields of the Line class
    private final Point start;
    private final Point  end;

    /**
     * Constructor of the Line class.
     * @param start Starting point of the line segment
     * @param end Ending point of the line segment
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
     * @return Length of line segment
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * The function returns the middle point of this line segment.
     * @return Middle point
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
     * @return Ending point
     */
    public Point end() {
        return new Point(this.end);
    }

    /**
     * It is Given that the point received as parameter is collinear with this line.
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

        //Checking if y value of the point is bigger than the lowest point on the line segment.
        boolean yBiggerThanMin = (Threshold.isDoubleGreaterEqual(other.getY(),
                Math.min(this.start.getY(), this.end.getY())));


        //Checking if y value of the point is smaller than the highest point on the line segment.
        boolean ySmallerThanMax = (Threshold.isDoubleGreaterEqual(Math.max(this.start.getY(), this.end.getY()),
                other.getY()));

        //We shall return true only if they are all true
        return xBiggerThanMin && xSmallerThanMax && yBiggerThanMin && ySmallerThanMax;
    }

    /**
     * This function checks and returns the orientation of the point received as a parameter in regard to this line.
     * The orientation of the point means the direction the line has to rotate to in order to "reach" the point.
     * The direction can either be clock wise, counter clock wise, or non.
     * Further explanation can be found in the following video:
     * https://youtu.be/5FkOO1Wwb8w?si=aP7kjNEjkXPdi0Sa
     * It is self-evident that the method below is my own and I did not copy any of it from the above video.
     * @param other the other point, we compare it to this line
     * @return 1 if the line need to be rotated clockwise, -1 if counter clock wise, and 0 if no rotation is needed
     */
    public int getPointOrientation(Point other) {
        double value = (this.end.getX() - this.start.getX()) * (other.getY() - this.end.getY())
                - (this.end.getY() - this.start.getY()) * (other.getX() - this.end.getX());
        if (Threshold.isDoublesEqual(value, 0)) { //Checking if value is 0 using threshold
            return 0; //Means they are collinear
        }
        return (value > 0) ? 1 : -1; //Do we need to move clockwise or counter clock wise
    }

    /**
     * The function checks if two line segments intersect.
     * It does that by doing the following checks:
     * 1. If o1 != o2 and o3 != o4, the line segment create an "X" shape which will result in an intersection
     * 2. If one of the orientations are 0, that means that the point represented in this orientation falls
     *      on the same line the other line segment creates. Then we can check if this point's x value falls
     *      between the other line segment's min and max X values to ensure there is in fact an intersection.
     * @param other the other line we check
     * @return true if they intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        int o1 = this.getPointOrientation(other.start());
        int o2 = this.getPointOrientation(other.end());
        int o3 = other.getPointOrientation(this.start);
        int o4 = other.getPointOrientation(this.end);

        //This is where the line segments create an X shape
        if (o1 != o2 && o3 != o4) {
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
     * The function checks if this line is actually a point, its start and end points are equal.
     * @return true if it is a point, false otherwise
     */
    public boolean isLineAPoint() {
        return (this.start.equals(this.end));
    }

    /**
     * This function calculates the slope of a line segment, if possible. If not, an error will occur.
     * The slope is calculated by the formula of deltaY / deltaX;
     * @return the slope if it has one
     */
    public double getSlope() {
        if (this.isLineAPoint()) {
            throw new Error("Not able to calculate slope for line segments that are a point");
        }
        if (this.isPerpendicularXAxis()) {
            throw new Error("Not able to calculate slope for lines that are perpendicular to the X axis");
        }
        double delX = this.start.getX() - this.end.getX();
        double delY = this.start.getY() - this.end.getY();
        return delY / delX;
    }

    /**
     * the function returns the 'b' in the line equation y = mx + b
     * if it cannot be calculated due to the line being perpendicular to X axis, an error will occur.
     * @return the constant b if it has one
     */
    public double getConstant() {
        return this.start().getY() - this.getSlope() * this.start().getX();
    }

    /**
     * The function returns the y value of the line at a given x value.
     * It assumes the line is not perpendicular to X axis and that the given x value is in range of the edge points
     * @param x the given x value we want to find the y value in
     * @return the y value of line at the given x
     */
    public double getYValueInX(double x) {
        if (this.isLineAPoint()) {
            return this.start.getY();
        }
        return this.getSlope() * x + this.getConstant();
    }

    /**
     * The function checks if there is situation in which the first point is on the other side of the middle point
     * than the second point.
     * @param x1 the first point
     * @param x2 the second point
     * @param xMid the middle point
     * @return true if they are in different sides, false otherwise
     */
    public boolean isDifferentSides(double xMid, double x1, double x2) {
        return (xMid - x1) * (xMid - x2) < 0;
    }

    /**
     * The function return the intersection point of two line segments, if they intersect.
     * @param other the other line
     * @return Intersection point if they intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        if (!isIntersecting(other)) {
            return null;
        }
        if (this.isLineAPoint()) {
            return new Point(this.start);
        }
        if (other.isLineAPoint()) {
            return new Point(other.start());
        }
        //The orientation of the points as described in the getPointOrientation method
        int o1 = this.getPointOrientation(other.start());
        int o2 = this.getPointOrientation(other.end());
        /*
         * We are now dealing with cases that the two line segments are on the same line, which is represented in the
         * fact that the orientations are both equal two 0
         */
        if (o1 == 0 && o2 == 0) {
            /*
             * The only way the two line segments are only tangent and do not have infinite intersections
             * is when one of their edge points are equal and the other edge points are in different sides
             * of the equal point.
             */
            if (this.isPerpendicularXAxis()) {
                /*
                 * We now deal with a case that this line is perpendicular to the main axis.
                 * because the line segments have an intersection point, and they lay on the same line, the other line
                 * must be also perpendicular to the X axis.
                 * Due to the fact that they are perpendicular to the X axis, we need to check if the other points
                 * are in different sides using their y values
                 */
                if (this.start.equals(other.start())) {
                    return (this.isDifferentSides(this.start.getY(), this.end.getY(), other.end().getY()))
                            ? new Point(this.start) : null;
                }
                if (this.start.equals(other.end())) {
                    return (this.isDifferentSides(this.start.getY(), this.end.getY(), other.start().getY()))
                            ? new Point(this.start) : null;
                }
                if (this.end.equals(other.start())) {
                    return (this.isDifferentSides(this.end.getY(), this.start.getY(), other.end().getY()))
                            ? new Point(this.end) : null;
                }
                if (this.end.equals(other.end())) {
                    return (this.isDifferentSides(this.end.getY(), this.start.getY(), other.start().getY()))
                            ? new Point(this.end) : null;
                }
                return null; //None of the cases applies
            }
            /*
             * Now we know that from the line segments that lie on the same line, neither is perpendicular to
             * the X axis, so we will use the same comparison as before but now we will use the X values
             */
            if (this.start.equals(other.start())) {
                return (this.isDifferentSides(this.start.getX(), this.end.getX(), other.end().getX()))
                        ? new Point(this.start) : null;
            }
            if (this.start.equals(other.end())) {
                return (this.isDifferentSides(this.start.getX(), this.end.getX(), other.start().getX()))
                        ? new Point(this.start) : null;
            }
            if (this.end.equals(other.start())) {
                return (this.isDifferentSides(this.end.getX(), this.start.getX(), other.end().getX()))
                        ? new Point(this.end) : null;
            }
            if (this.end.equals(other.end())) {
                return (this.isDifferentSides(this.end.getX(), this.start.getX(), other.start().getX()))
                        ? new Point(this.end) : null;
            }
            return null; //None of the cases applies
        }
        /*
         * Now we know that the line segments do not lie on the same line. As a result we can infer that
         * there is no case here that both of the line segments are perpendicular two the X axis because then they
         * would be lying on the same line or not intersecting at all, and we covered both cases earlier
         * We will not deal with the case that only one of the line segment is perpendicular to X axis
         */
        if (this.isPerpendicularXAxis()) {
            //we know the intersection is on this line, and this line only has one X value to all of its points.
            double xIntersection = this.start.getX();
            return new Point(xIntersection, other.getYValueInX(xIntersection));
        }
        if (other.isPerpendicularXAxis()) {
            /*
             * we know the intersection is on the other line, and the other line only has one X value to
             *  all of its points.
             */
            double xIntersection = other.start().getX();
            return new Point(xIntersection, this.getYValueInX(xIntersection));
        }
        /*
         * By now we have dealt with all situations in which the one or both of the lines are perpendicular
         * to the X axis, and cases that the line segments are on the same infinite line as each other.
         * Now we know that the line segments for sure have only one intersection, and that neither of them
         * is perpendicular to the X axis, so we will find the intersection point by solving the equations of the lines
         * equation 1: y = m1x + b1
         * equation 2: y = m2x + b2
         * with a little algebra we get
         * x = - (b1 - b2) / (m1 - m2)
         */
        double xVal = -(this.getConstant() - other.getConstant())
                / (this.getSlope() - other.getSlope());
        return new Point(xVal, this.getYValueInX(xVal));
    }

    /**
     * The function check if this line is the same visually as another line.
     * @param other the other line
     * @return true if they are the same visually, false otherwise
     */
    public boolean equals(Line other) {
        boolean sameOrder = (this.start.equals(other.start()) && this.end.equals(other.end())); //start=start
        boolean difOrder = (this.start.equals(other.end()) && this.end.equals(other.start())); //start=end
        return sameOrder || difOrder;
    }

    /**
     * The function returns the closest-to-start intersection point with the rectangle.
     * @param rect The rectangle we find intersection with
     * @return closest-to-start intersection point if there is one, null otherwise
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        boolean isFirstClosestToStart;
        Point firstIntersection, secondIntersection;
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        /*
         * Due to geometrical considerations, a line can only intersect with a rectangle in 2 Points max.
         * If there are infinite intersections, we still want to return the closest one to the start of the line so in
         * order to do that we will check (if intersectionPoint returns null) if the line is coincident with one of
         * the edges of the rectangle. If it is, there are 3 cases: we return the starting point, or one of the
         * vertexes of the edge of the rectangle, based on where the starting point of the line is located.
         */
        if (intersectionPoints == null) {
            return null;
        }
        if (intersectionPoints.isEmpty()) {
            return null;
        }

        firstIntersection = intersectionPoints.get(0);

        if (intersectionPoints.size() == 1) {
            return new Point(firstIntersection);
        }

        //Now we know there are two intersection points
        secondIntersection = intersectionPoints.get(1);
        isFirstClosestToStart = Threshold.isDoubleGreaterEqual(
                secondIntersection.distance(this.start),
                firstIntersection.distance(this.start));
        return isFirstClosestToStart ? new Point(firstIntersection) : new Point(secondIntersection);
    }

    /**
     * The function checks if two lines have infinite collision points.
     * @param other the other line
     * @return true if they have infinite collisions, false otherwise
     */
    public boolean isCoincident(Line other) {
        //We can check if they have infinite collision points if they intersect, but the intersectionWith returns null
        return other.isIntersecting(this) && other.intersectionWith(this) == null;
    }
}