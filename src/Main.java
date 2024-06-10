import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.util.Random;
import java.awt.Color;
/**
 * .
 */
public class Main {
    /**
     * .
     * @param args
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Test", 300, 300);
        GameEnvironment ge = new GameEnvironment();
        Rectangle rectangle = new Rectangle(new Point(20, 20), 30, 40);
        Block block = new Block(rectangle);
        Rectangle rectangle2 = new Rectangle(new Point(30, 200), 20, 40);
        Block block2 = new Block(rectangle2);

        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(new Point(80, 40), 3, Color.BLACK);
        ball.setVelocity(-1, 0.2);
        ball.addCollidable(block);
        ball.addCollidable(block2);
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            block.drawOn(d);
            block2.drawOn(d);
            ball.moveOneStep();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }
}
