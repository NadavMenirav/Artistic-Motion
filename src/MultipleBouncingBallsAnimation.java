import java.util.Random;
import java.awt.Color;
import biuoop.GUI;
import biuoop.Sleeper;
import game.objects.Ball;
import game.objects.Velocity;
import biuoop.DrawSurface;
/**
 * MultipleBouncingBallsAnimation class.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * create random ball.
     * @param args array with the sizes of the balls
     * @return an array of Balls
     */
    public static Ball[] createBalls(String[] args) {
        Random rand = new Random();
        Ball[] balls = new Ball[args.length];
        for (int i = 0; i < args.length; i++) {
            Ball ball = new Ball(Integer.parseInt(args[i]), Color.BLACK);
            int speed = Math.max(11 - ball.getSize() / 5, 1) / 2;
            double angle = 360 * rand.nextDouble();
            ball.setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
            balls[i] = ball;
        }
        return balls;
    }
    /**
     * animation of MultipleBouncinigBallsAnimation.
     * @param args array with the sizes of the balls
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        GUI gui = new GUI("Multiple Bouncing Balls Animation", 200, 200);
        Ball[] balls = createBalls(args);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (Ball ball : balls) {
                ball.moveOneStep();
                ball.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }
}