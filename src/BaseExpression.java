//Nadav Menirav 330845678

/**
 * BaseExpression class.
 */
public abstract class BaseExpression implements Expression {
    private final String expressionSymbol;

    /**
     * Constructor.
     * @param expressionSymbol The symbol of the expression
     */
    public BaseExpression(String expressionSymbol) {
        this.expressionSymbol = new String(expressionSymbol);
    }

    /**
     * Getter of the expressionSymbol field.
     * @return The expression symbol of this expression
     */
    public String getExpressionSymbol() {
        return new String(expressionSymbol);
    }

}
