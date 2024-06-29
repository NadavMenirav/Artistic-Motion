/**
 * Class with main to test the Expression classes.
 */
public class ExpressionOutput {

    /**
     * Main which runs code.
     * @param args relevant
     */
    public static void main(String[] args) throws Exception {
    Expression e = new Not(
        new Xor(new And(new Val(true),
        new Or(
            new Var("x", new Val(false)),
            new Var("y", new Val(true))
        )
    ),
        new Var("x", new Val(true))
    )
    );

        System.out.println(e.toString());
        System.out.println(e.evaluate());
    }
}