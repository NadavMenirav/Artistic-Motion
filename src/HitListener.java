//Nadav Menirav 330845678

/**
 * HitListener interface.
 */
public interface HitListener {

    /**
     * Add hl as a listener to hit events.
     * @param hl The listener we add
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl The listener we remove
     */
    void removeHitListener(HitListener hl);
}
