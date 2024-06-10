import biuoop.GUI;
import biuoop.DrawSurface;
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
        DrawSurface d = gui.getDrawSurface();
        GameEnvironment ge = new GameEnvironment();
        Rectangle rectangle = new Rectangle(new Point(20, 20), 30, 40);
        Block block = new Block(rectangle);
        Rectangle rectangle2 = new Rectangle(new Point(30, 30), 20, 40);
        Block block2 = new Block(rectangle2);
        d.

    }
}
