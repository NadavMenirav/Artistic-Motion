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
        this.upperLeft = new Point(upperLeft);
        this.width = width;
        this.height = height;
    }

    /**
     * Duplicates a rectangle.
     * @param rectangle The rectangle to be duplicated
     */
    public Rectangle(Rectangle rectangle) {
        this(rectangle.getUpperLeft(), rectangle.getWidth(), rectangle.getHeight());
    }

    /**
     * The function returns a list of the intersection points of a line with this rectangle.
     * @param line The line we intersect with this rectangle
     * @return List of intersection point if intersecting finite times, null otherwise
     */
    public List<Point> intersectionPoints(Line line) {
        Point p1, p2, p3, p4;
        p1 = line.intersectionWith(this.getLeftEdge());
        p2 = line.intersectionWith(this.getRightEdge());
        p3 = line.intersectionWith(this.getTopEdge());
        p4 = line.intersectionWith(this.getBottomEdge());
        List<Point> list = new ArrayList<>();

        //If they have infinite intersection points, we shall return null
        if (
                line.isCoincident(this.getTopEdge())
                        || line.isCoincident(this.getBottomEdge())
                        || line.isCoincident(this.getLeftEdge())
                        || line.isCoincident(this.getRightEdge())
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
                list.add(p4);
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

    /**
     * Returns the upper right point of the rectangle.
     * @return the upper right point
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
    }

    /**
     * Returns the bottom left point of the rectangle.
     * @return the bottom left point
     */
    public Point getBottomLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
    }

    /**
     * Returns the bottom right point of the rectangle.
     * @return the bottom right point
     */
    public Point getBottomRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }

    /**
     * Returns the top edge of this Rectangle.
     * @return Top edge
     */
    public Line getTopEdge() {
        return new Line(this.upperLeft, this.getUpperRight());
    }

    /**
     * Returns the bottom edge of this Rectangle.
     * @return bottom edge
     */
    public Line getBottomEdge() {
        return new Line(this.getBottomLeft(), this.getBottomRight());
    }

    /**
     * Returns the left edge of this Rectangle.
     * @return Left edge
     */
    public Line getLeftEdge() {
        return new Line(this.upperLeft, this.getBottomLeft());
    }

    /**
     * Returns the Right edge of this Rectangle.
     * @return Right edge
     */
    public Line getRightEdge() {
        return new Line(this.getUpperRight(), this.getBottomRight());
    }


}