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
        Game game = new Game();
        Ball ball = new Ball(new Point(500, 100), 30, Color.BLACK);
        ball.setVelocity(1, 3);
        
    }
}
