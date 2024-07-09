package game.miscellaneous;

/**
 * Counter class.
 */
public class Counter {
    private int count;

    /**
     * Constructor of the Counter class.
     * @param number The number
     */
    public Counter(int number) {
        count = number;
    }

    /**
     * Empty Constructor.
     */
    public Counter() {
        count = 0;
    }

    /**
     * Add number to current count.
     * @param number The number
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Subtract number from current count.
     * @param number The number
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Get current count.
     * @return Current amount
     */
    public int getValue() {
        return count;
    }
}