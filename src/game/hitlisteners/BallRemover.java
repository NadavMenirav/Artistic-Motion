package game.hitlisteners;

import game.Game;
import game.miscellaneous.Counter;
import game.objects.Ball;
import game.objects.Block;

/**
 * BallRemover class.
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;

    /**
     * Constructor of the BallRemover class.
     * @param game The game of this BallRemover
     * @param remainingBalls The remaining balls in the blockRemover
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        remainingBalls.decrease(1);
    }

}
