import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;
/**
 * Forced to create this JDOC due to checkstyles.
 */
public class BallsTest1 {
    /**
     * Draws an animation.
     * @param start the starting point
     * @param dx the dx
     * @param dy the dy
     */
    public static void drawAnimation(Point start, double dx, double dy) {
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        GUI gui = new GUI("title", 200, 200);
        ball.setVelocity(Velocity.fromAngleAndSpeed(225, 5));
        Sleeper sleeper = new Sleeper();
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }
    /**
     * Bafoonery.
     * @param args Command Line Arguments
     */
    public static void main(String[] args) {
        drawAnimation(new Point(100, 100), 10, 15);
        return;
    }
}