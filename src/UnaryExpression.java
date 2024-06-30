//Nadav Menirav 330845678

import java.util.List;
import java.util.Map;

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
    protected UnaryExpression(Expression expression, String expressionSymbol) {
        super(expressionSymbol);
        this.expression = expression;
    }

    /**
     * Applies the binary operation to the bool.
     * @param bool The operand
     * @return Boolean result of the operation
     */
    protected abstract Boolean operate(Boolean bool);

    /**
     * Getter of the expression field.
     * @return The expression
     */
    protected Expression getExpression() {
        return this.expression;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return operate(this.expression.evaluate(assignment));
    }

    @Override
    public Boolean evaluate() throws Exception {
        return operate(this.expression.evaluate());
    }

    @Override
    public List<String> getVariables() {
        return this.expression.getVariables();
    }

    @Override
    public String toString() {
        return this.getExpressionSymbol() + "(" + this.expression.toString() + ")";
    }
    @Override
    public abstract Expression assign(String var, Expression expression);

}
