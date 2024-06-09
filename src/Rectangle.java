//Nadav Menirav 330845678
import java.util.List;
import java.util.ArrayList;
/**
 * Rectangle class.
 */
public class Rectangle {
    //Fields of the Rectangle class
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft the upperLeft point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * The function returns a list of the intersection points of a line with this rectangle.
     * @param line the line we intersect with this rectangle
     * @return list of intersection point if intersecting, null otherwise
     */
    public List<Point> inersectionPoints(Line line) {
        List<Point> list = new ArrayList<>();
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point bottomLeft = new Point(this.upperLeft.getX(), this.getY() + this.height);
        Point bottomRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        Line topEdge = new Line(this.upperLeft, upperRight);
        Line bottomEdge = new Line(bottomLeft, bottomRight)l;
        Line leftEdge = new Line(this.upperLeft, bottomLeft);
        Line rightEdge = new Line(upperRight, bottomRight);
        //Adding the points
        if (!line.intersectionWith(topEdge)) {
            list.add(line.intersectionWith(topEdge));
        }
        if (!line.intersectionWith(bottomEdge)) {
            list.add(line.intersectionWith(bottomEdge));
        }
        if (!line.intersectionWith(leftEdge)) {
            list.add(line.intersectionWith(leftEdge));
        }
        if (!line.intersectionWith(rightEdge)) {
            list.add(line.intersectionWith(rightEdge));
        }
        return list;
    }

}   