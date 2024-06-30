//Nadav Menirav 330845678

/**
 * UnaryExpression class.
 */
public abstract class UnaryExpression extends BaseExpression {
    private final Expression firstExpression;
    private final Expression expressionSymbol;

    public UnaryExpression(Expression expression, String expressionSymbol) {
        this.firstExpression = firstExpression
        this.expressionSymbol = expressionSymbol;
    }

    @Override
    public Boolean equals(Expression other) {
        return this.toString().equals(other.toString());
    }
}
