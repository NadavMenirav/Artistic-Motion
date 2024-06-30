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

    /**
     * Applies the binary operation to the bool.
     * @param bool The operand
     * @return Boolean result of the operation
     */
    public abstract Boolean operate(Boolean bool);

    @Override
    public Boolean equals(Expression other) {
        return this.toString().equals(other.toString());
    }
}
