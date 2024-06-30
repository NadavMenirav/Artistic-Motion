//Nadav Menirav 330845678

import java.util.List;
import java.util.Map;

/**
 * UnaryExpression class.
 */
public abstract class UnaryExpression extends BaseExpression {
    private final BaseExpression expression;

    /**
     * Constructor.
     * @param expression
     * @param expressionSymbol
     */
    public UnaryExpression(BaseExpression expression, String expressionSymbol) {
        super(expressionSymbol);
        this.expression = expression;
    }

    /**
     * Applies the binary operation to the bool.
     * @param bool The operand
     * @return Boolean result of the operation
     */
    public abstract Boolean operate(Boolean bool);

    /**
     * Getter of the expression field.
     * @return The expression
     */
    public BaseExpression getExpression() {
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
    public abstract BaseExpression assign(String var, Expression expression);

}
