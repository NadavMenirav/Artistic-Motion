/**
 * BallRemover class.
 */
public class BallRemover implements HitListener {
    private final Game game;
    private Counter remainingBalls;

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
