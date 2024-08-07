//Nadav Menirav 330845678
package game.miscellaneous;

import biuoop.DrawSurface;
import game.objects.Sprite;

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
        this.sprites.add(s);
    }

    /**
     * This method removes a Sprite from this Collection.
     * @param s The sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * This method calls timePassed() on all Sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> newSprites = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : newSprites) {
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