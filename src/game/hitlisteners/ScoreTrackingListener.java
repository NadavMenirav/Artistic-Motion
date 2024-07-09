package game.hitlisteners;

import game.miscellaneous.Counter;
import game.objects.Ball;
import game.objects.Block;

/**
 * ScoreTrackingListener class.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Constructor of the ScoreTrackingListener class.
     * @param scoreCounter TThe scoreCounter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.ballColorMatch(hitter)) {
            return;
        }
        this.currentScore.increase(5);
    }

}
