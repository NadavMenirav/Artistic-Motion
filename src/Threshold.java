package src;
/**
 * @author Nadav Menirav
 */
public class Threshold {
    /**
     * This threshold based function checks if two real numbers are equal.
     * @param x1 first number
     * @param x2 second number
     * @return boolean value based on answer
     */
    public static boolean isDoublesEqual(double x1, double x2) {
        double epsilon = 0.000001d;
        return (Math.abs(x1 - x2) < epsilon);
    }
    /**
     * This threshold based function check if a real number is bigger than another real number.
     * @param x1 the first number, we check if it is bigger
     * @param x2 second number
     * @return boolean balue based on answer
     */
    public static boolean isDoubleGreaterEqual(double x1, double x2) {
        return ((isDoublesEqual(x1, x2)) || (x1 > x2));
    }
}
