//Nadav Menirav 330845678

/**
 * UnaryExpression class.
 */
public abstract class UnaryExpression extends BaseExpression {
    private final Expression expression;

    /**
     * Constructor.
     * @param expression
     * @param expressionSymbol
     */
    public UnaryExpression(Expression expression, String expressionSymbol) {
        super(expressionSymbol);
        this.expression = expression;
    }

    @Override
    public Boolean equals(Expression other) {
        return this.toString().equals(other.toString());
    }
}
