package game.miscellaneous;

import java.awt.Color;

import biuoop.DrawSurface;
import game.objects.Sprite;
import geometry.Rectangle;

/**
 * ScoreIndicator class.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;
    private final Rectangle rectangle;

    /**
     * Constructor of the ScoreIndicator class.
     * @param score The score
     * @param rectangle The rectangle
     */
    public ScoreIndicator(Counter score, Rectangle rectangle) {
        this.score = score;
        this.rectangle = new Rectangle(rectangle);
    }

    @Override
    public void drawOn(DrawSurface d) {
        final int textXPosition = (int) (0.45 * (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth()));
        final int textYPosition = (int) (0.45 * (this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight()));
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(
            (int) this.rectangle.getUpperLeft().getX(),
            (int) this.rectangle.getUpperLeft().getY(),
            (int) this.rectangle.getWidth(),
            (int) this.rectangle.getHeight()
        );

        d.setColor(Color.BLACK);
        d.drawText(textXPosition, textYPosition, "Score: " + this.score.getValue(), 12);
    }

    @Override
    public void timePassed() {
    }

}
