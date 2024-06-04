/**
 * BouncingBallAnimation class.
 */
public class BouncingBallAnimation {
    /**
     * Calls the drawAnimation method using command line arguments.
     * @param args array of CLI arguments
     */
    public static void main(String[] args) {
        if (args.length < 4) {
            return;
        }
        Point center = new Point(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
        double dx = Double.parseDouble(args[2]);
        double dy = Double.parseDouble(args[3]);
        BallsTest1.drawAnimation(center, dx, dy);
    }
}
