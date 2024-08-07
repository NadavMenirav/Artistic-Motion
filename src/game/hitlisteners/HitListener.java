//Nadav Menirav 330845678

package game.hitlisteners;

import game.objects.Ball;
import game.objects.Block;

/**
 * HitListener interface.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting
     * @param beingHit The block being hit
     * @param hitter The block that hits
     */
    void hitEvent(Block beingHit, Ball hitter);
}
