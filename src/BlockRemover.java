/**
 * A BlockRemover is in charge of removing blocks from the game, as well as keeping count.
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor of the BlockRemover class.
     * @param game The game of this blockRemover
     * @param remainingBlocks The remaining blocks in the blockRemover
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed.
     * Remember to remove this listener from the block that is being removed from the game.
     * @param beingHit The block being hit
     * @param hitter The Ball hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.ballColorMatch(hitter)) {
            return;
        }
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(game);
        this.remainingBlocks.decrease(1);
    }
}
