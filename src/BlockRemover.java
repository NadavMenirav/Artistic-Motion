import java.awt.Color;

/**
 * A BlockRemover is in charge of removing blocks from the game, as well as keeping count.
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;

    /**
     * Constructor of the BlockRemover class.
     * @param game The game of this blockRemover
     * @param remainingBlocks The remaining blocks in the blockRemover
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.ballColorMatch(hitter)) {
            return;
        }
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(game);
        hitter.setColor(new Color(beingHit.getColor().getRGB()));
        this.remainingBlocks.decrease(1);
    }
}
