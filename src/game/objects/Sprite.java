//Nadav Menirav 330845678

package game.objects;

import biuoop.DrawSurface;

/**
 * Sprite interface. Represents the objects that can be drawn on the screen
 */
public interface Sprite {
    /**
     * This method draws the sprite on the screen.
     * @param d the DrawSurface we draw on
     */
    void drawOn(DrawSurface d);

    /**
     * This method notifies the sprite that a certain period of time has passed,
     * so he will be able to do its necessary changes.
     */
    void timePassed();

}