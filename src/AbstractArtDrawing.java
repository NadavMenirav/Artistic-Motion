import java.util.Random;
import java.awt.Color;
import biuoop.GUI;
import geometry.Line;
import biuoop.DrawSurface;
/**
 * AbstractArtDrawing class.
 */
public class AbstractArtDrawing {
    /**
     * creates random lines.
     * @param amount amount of lines to create
     * @return the lines generated
     */
    public Line[] generateRandomLines(int amount) {
        Random rand = new Random();
        Line[] lines = new Line[amount];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new Line(rand.nextInt(400 + 1), rand.nextInt(300 + 1),
                rand.nextInt(400 + 1), rand.nextInt(300 + 1));
        }
        return lines;
    }
    /**
     * creates random lines and draws them.
     * @param amount amount of lines to create
     */
    public void drawRandomLines(int amount) {
        GUI gui = new GUI("Abstract Art Drawing", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        Line[] lines = generateRandomLines(amount);

        for (int i = 0; i < lines.length; i++) {
            Line line = lines[i];
            Point middle = line.middle();
            d.setColor(Color.BLACK);
            d.drawLine((int) line.start().getX(), (int) line.start().getY(),
                (int) line.end().getX(), (int) line.end().getY());
            d.setColor(Color.BLUE);
            d.fillCircle((int) middle.getX(), (int) middle.getY(), 3);
        }
        for (int i = 0; i < lines.length - 1; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                Point commonPoint = lines[i].intersectionWith(lines[j]);
                if (commonPoint == null) {
                    continue;
                }
                d.setColor(Color.RED);
                d.fillCircle((int) commonPoint.getX(), (int) commonPoint.getY(), 3);
            }
        }

        for (int i = 0; i < lines.length - 2; i++) {
            for (int j = i + 1; j < lines.length - 1; j++) {
                for (int k = j + 1; k < lines.length; k++) {
                    Point firstVertex = lines[i].intersectionWith(lines[j]);
                    Point secondVertex = lines[i].intersectionWith(lines[k]);
                    Point thirdVertex = lines[j].intersectionWith(lines[k]);

                    if (firstVertex == null || secondVertex == null || thirdVertex == null) {
                        continue;
                    }

                    Line firstSide = new Line(firstVertex, secondVertex);
                    Line secondSide = new Line(firstVertex, thirdVertex);
                    Line thirdSide = new Line(secondVertex, thirdVertex);

                    d.setColor(Color.GREEN);
                    d.drawLine((int) firstSide.start().getX(), (int) firstSide.start().getY(),
                        (int) firstSide.end().getX(), (int) firstSide.end().getY());
                    d.setColor(Color.GREEN);
                    d.drawLine((int) secondSide.start().getX(), (int) secondSide.start().getY(),
                        (int) secondSide.end().getX(), (int) secondSide.end().getY());
                    d.setColor(Color.GREEN);
                    d.drawLine((int) thirdSide.start().getX(), (int) thirdSide.start().getY(),
                        (int) thirdSide.end().getX(), (int) thirdSide.end().getY());
                }
            }
        }
        gui.show(d);
    }
    /**
     * Runs drawRandomCircles.
     * @param args
     */
    public static void main(String[] args) {
        AbstractArtDrawing abstractArtDrawing = new AbstractArtDrawing();
        abstractArtDrawing.drawRandomLines(10);
    }
}