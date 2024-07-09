//Nadav Menirav 330845678

package utils;

/**
 * Threshold class.
 */
public class Threshold {
    /**
     * This threshold based method checks if two real numbers are equal.
     * @param x1 First number
     * @param x2 Second number
     * @return True if they are equal, false otherwise
     */
    public static boolean isDoublesEqual(double x1, double x2) {
        double epsilon = 0.000001d;
        return (Math.abs(x1 - x2) < epsilon);
    }
    /**
     * This threshold based method check if a real number is bigger than another real number.
     * @param x1 The first number, we check if it is bigger
     * @param x2 Second number
     * @return True if x1 is bigger or equals to x2, false otherwise.
     */
    public static boolean isDoubleGreaterEqual(double x1, double x2) {
        return ((isDoublesEqual(x1, x2)) || (x1 > x2));
    }
}