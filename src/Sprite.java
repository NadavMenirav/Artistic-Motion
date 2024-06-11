//Nadav Menirav 330845678

import biuoop.DrawSurface;

/**
 * Sprite interface. Represents the objects that can be drawn on the screen
 */
public interface Sprite {
    /**
     * This function draws the sprite on the screen.
     * @param d the DrawSurface we draw on
     */
    void drawOn(DrawSurface d);

    /**
     * This function notifies the spite that a certain period of time has passed,
     * so he will be able to do its necessary changes.
     */
    void timePassed();

}