//Nadav Menirav 330845678

import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * SpriteCollection class, holds a collection of the Sprites in the game.
 */
public class SpriteCollection {
    //Fields of the SpriteCollection class
    private final ArrayList<Sprite> sprites;

    /**
     * Constructor of the SpriteCollection class.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * This method adds a Sprite to the collection.
     * @param s The sprite we add
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s); //Encapsulation?
    }

    /**
     * This method calls timePassed() on all Sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite s : this.sprites) {
            s.timePassed();
        }
    }

    /**
     * This method calls drawOn() on all Sprites.
     * @param d The DrawSurface we draw the Sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }
}