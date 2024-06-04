import java.util.Random;
import java.awt.Color;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;
/**
 * MultipleFramesBouncingBallsAnimation class.
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * create random NonTrappedBalls.
     * @param args array with sizes
     * @return NonTrappedBalls
     */
    public static NonTrappedBall[] createNonTrappedBalls(String[] args) {
        Random random = new Random();
        int length = args.length / 2;
        NonTrappedBall[] balls = new NonTrappedBall[length];
        for (int i = 0; i < length; i++) {
            NonTrappedBall ball = new NonTrappedBall(Integer.parseInt(args[i + length]) + 5, Color.BLUE);
            int speed = Math.max(120 / ball.getSize() / 8, 2);
            double angle = 360 * random.nextDouble();
            ball.setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
            balls[i] = ball;
        }
        return balls;
    }
    /**
     * create random TrappedBalls.
     * @param args array with sizes
     * @param trap trap of balls
     * @return TrappedBalls
     */
    public static TrappedBall[] createTrappedBalls(String[] args, Trap trap) {
        Random rand = new Random();
        int length = args.length / 2;
        TrappedBall[] balls = new TrappedBall[length];
        for (int i = 0; i < length; i++) {
            TrappedBall ball = new TrappedBall(Integer.parseInt(args[i]), Color.RED, trap);
            int speed = Math.max(11 - ball.getSize() / 5, 1);
            double angle = 360 * rand.nextDouble();
            ball.setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
            balls[i] = ball;
        }
        return balls;
    }
    /**
     * Creates an animation featuring multiple frames and balls.
     * @param args array of CLI arguments
     */
    public static void main(String[] args) {
        if (args.length == 0 || args.length % 2 == 1) {
            return;
        }
        GUI gui = new GUI("Multiple Frames Bouncing Balls Animation", 800, 800);
        Sleeper sleeper = new Sleeper();
        Trap grey = new Trap(new Point(50, 50), new Point(500, 500));
        Trap yellow = new Trap(new Point(450, 450), new Point(650, 650));
        TrappedBall[] trappedBalls = createTrappedBalls(args, grey);
        NonTrappedBall[] nonTrappedBalls = createNonTrappedBalls(args);

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.GRAY);
            d.fillRectangle((int) grey.getTopLeft().getX(), (int) grey.getTopLeft().getY(),
                grey.getWidth(), grey.getHeight());
            for (TrappedBall ball : trappedBalls) {
                ball.moveOneStep();
                ball.drawOn(d);
            }
            for (NonTrappedBall ball : nonTrappedBalls) {
                ball.moveOneStep();
                ball.drawOn(d);
            }
            d.setColor(Color.YELLOW);
            d.fillRectangle(
                (int) yellow.getTopLeft().getX(),
                (int) yellow.getTopLeft().getY(),
                yellow.getWidth(),
                yellow.getHeight()
            );
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }
}