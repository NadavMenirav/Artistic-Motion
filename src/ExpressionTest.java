//Nadav Menirav
import java.util.HashMap;

/**
 * Class with main to test the Expression classes.
 */
public class ExpressionTest {
    /**
     * Main which runs code.
     * @param args relevant
     */
    public static void main(String[] args) throws Exception {
        // Creates an Expression:

        Expression ex = new Not(
            new And(
                new Var("x"),
                new Xnor(
                    new Var("y"),
                    new Nand(
                        new And(
                            new Val(true),
                            new Var("z")
                        ),
                    new Val(false))
                )
            )
        );
        // Prints the Expression
        System.out.println(ex);

        // Populates the Expression
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("x", true);
        map.put("y", false);
        map.put("z", false);

        // Prints the value of the Expression after the population
        System.out.println(ex.evaluate(map));

        // Nandifies and prints the Expression
        System.out.println(ex.nandify());
        // Norifies and prints the Expression
        System.out.println(ex.norify());
        // Simplifies and prints the Expression
        System.out.println(ex.simplify());
    }
}