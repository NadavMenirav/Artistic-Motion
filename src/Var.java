//330845678

/**
 * Var class.
 */
public class Var implements Expression {
    private final String name;
    private final Expression expression;

    /**
     * Constructor.
     * @param name
     * @param expression
     */
    public Var(String name, Expression expression) {
        this.name = new String(name);
        this.expression = expression;
    }
}
