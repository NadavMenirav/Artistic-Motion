//Nadav Menirav

/**
 * Class with main to test the Expression classes.
 */
public class ExpressionTest {

    /**
     * Main method.
     * @param args Command-Line-Arguments
     */
    public static void main(String[] args) throws Exception {
        // Creates an expression with at least three variables
        Expression x = new Var("x");
        Expression y = new Var("y");
        Expression z = new Var("z");

        Expression ex = new And(
            new Or(
                x,
                new And(
                    y,
                    new Val(true)
                )
            ),
            new And(
                z,
                z
            )
        );
        // Prints the expression
        System.out.println(ex);


        // Assigns the three variables: x, y, z with boolean values
        Expression ex2 = ex.assign("x", new Val(true));
        Expression ex3 = ex2.assign("y", new Val(false));
        Expression ex4 = ex3.assign("z", new Val(false));

        // Prints the expression after assignment
        System.out.println(ex4);

        // Prints the nandified version of the expression:
        System.out.println(ex.nandify());

        // Prints the norified version of the expression:
        System.out.println(ex.norify());

        // Prints the simplified version of the expression:
        System.out.println(ex.simplify());
    }
}