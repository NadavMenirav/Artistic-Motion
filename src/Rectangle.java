//Nadav Menirav 330845678
import java.util.List;
import java.util.ArrayList;
/**
 * Rectangle class.
 */
public class Rectangle {
    //Fields of the Rectangle class
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft The upperLeft point of the rectangle
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Duplicates a rectangle.
     * @param rectangle The rectangle to be duplicated
     */
    public Rectangle(Rectangle rectangle) {
        this(rectangle.upperLeft, rectangle.width, rectangle.height);
    }

    /**
     * The function returns a list of the intersection points of a line with this rectangle.
     * @param line The line we intersect with this rectangle
     * @return List of intersection point if intersecting finite times, null otherwise
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<>();
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point bottomLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point bottomRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        Line topEdge = new Line(this.upperLeft, upperRight);
        Line bottomEdge = new Line(bottomLeft, bottomRight);
        Line leftEdge = new Line(this.upperLeft, bottomLeft);
        Line rightEdge = new Line(upperRight, bottomRight);
        Point p1, p2, p3, p4;
        p1 = line.intersectionWith(leftEdge);
        p2 = line.intersectionWith(rightEdge);
        p3 = line.intersectionWith(topEdge);
        p4 = line.intersectionWith(bottomEdge);

        //If they have infinite intersection points, we shall return null
        if (
                line.isCoincident(topEdge)
                || line.isCoincident(bottomEdge)
                || line.isCoincident(leftEdge)
                || line.isCoincident(rightEdge)
        ) {
            return null;
        }

        //Adding the points
        if (p1 != null) {
            list.add(p1);
        }
        if (p2 != null) {
            list.add(p2);
        }
        if (p3 != null) {
            if (!p3.equals(p1) && !p3.equals(p2)) {
                list.add(p3);
            }
        }
        if (p4 != null) {
            if (!p4.equals(p1) && !p4.equals(p2)) {
                list.add(line.intersectionWith(rightEdge));
            }
        }
        return list;
    }

    /**
     * Getter of the height field.
     * @return The height of this rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Getter of the width field.
     * @return The width of this rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Getter of the upperLeft field.
     * @return A copy of the upperLeft point of this rectangle
     */
    public Point getUpperLeft() {
        return new Point(this.upperLeft);
    }


}