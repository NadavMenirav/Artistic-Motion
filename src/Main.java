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
        GUI gui = new GUI("Test", 800, 600);
        GameEnvironment ge = new GameEnvironment();
        Rectangle rectangle = new Rectangle(new Point(0, 0), 800, 50);
        Rectangle rectangle2 = new Rectangle(new Point(0, 550), 800, 50);
        Rectangle rectangle3 = new Rectangle(new Point(0, 0), 50, 600);
        Rectangle rectangle4 = new Rectangle(new Point(750, 0), 50, 600);
        Rectangle rectangle5 = new Rectangle(new Point(100, 100), 200, 150);
        Rectangle rectangle6 = new Rectangle(new Point(350, 200), 200, 150);
        Rectangle rectangle7 = new Rectangle(new Point(600, 300), 150, 200);
        Block block = new Block(rectangle);
        Block block2 = new Block(rectangle2);
        Block block3 = new Block(rectangle3);
        Block block4 = new Block(rectangle4);
        Block block5 = new Block(rectangle5);
        Block block6 = new Block(rectangle6);
        Block block7 = new Block(rectangle7);
        ge.addCollidable(block);
        ge.addCollidable(block2);
        ge.addCollidable(block3);
        ge.addCollidable(block4);
        ge.addCollidable(block5);
        ge.addCollidable(block6);
        ge.addCollidable(block7);
        Ball ball = new Ball(new Point(500, 100), 10, ge);


        Sleeper sleeper = new Sleeper();
        ball.setVelocity(-3, 1);
        /*ball.addCollidable(block);
        ball.addCollidable(block2);*/
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            block.drawOn(d);
            block2.drawOn(d);
            block3.drawOn(d);
            block4.drawOn(d);
            block5.drawOn(d);
            block6.drawOn(d);
            block7.drawOn(d);
            ball.moveOneStep();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }
}
