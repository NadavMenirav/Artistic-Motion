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
     * The function returns a list of the intersection points of a line with this rectangle.
     * @param line The line we intersect with this rectangle
     * @return List of intersection point if intersecting, null otherwise
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

        //Adding the points
        if (line.intersectionWith(topEdge) != null) {
            list.add(line.intersectionWith(topEdge));
        }
        if (line.intersectionWith(bottomEdge) != null) {
            list.add(line.intersectionWith(bottomEdge));
        }
        if (line.intersectionWith(leftEdge) != null) {
            list.add(line.intersectionWith(leftEdge));
        }
        if (line.intersectionWith(rightEdge) != null) {
            list.add(line.intersectionWith(rightEdge));
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