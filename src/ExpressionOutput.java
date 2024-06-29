/**
 * Class with main to test the Expression classes.
 */
public class ExpressionOutput {

    /**
     * Main which runs code.
     * @param args relevant
     */
    public static void main(String[] args) throws Exception {
        Expression ex = new Not(new And(new Val(false), new Val(true)));

        System.out.println(ex.toString());
    }
}