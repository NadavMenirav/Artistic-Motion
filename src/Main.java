/**
 * .
 */
public class Main {
    /**
     * .
     * @param args
     */
    public static void main(String[] args) {
        Point myPoint = new Point(0.0, 0.0);
        Point myPoint2 = new Point(1.0, 1.0);
        Line line = new Line(myPoint, myPoint2);
        Point myPoint3 = new Point(-0.5, -0.5);
        Point myPoint4 = new Point(0.5, 0.5);
        Point myPoint5 = new Point(1.5, 1.5);

        System.out.println(line.getPointOrientation(myPoint3));
        System.out.println(line.getPointOrientation(myPoint4));
        System.out.println(line.getPointOrientation(myPoint5));
    }
}
