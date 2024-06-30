//Nadav Menirav 330845678

import java.util.List;
import java.util.Map;

/**
 * BaseExpression class.
 */
public abstract class BaseExpression implements Expression {
    @Override
    public abstract Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    @Override
    public abstract Boolean evaluate() throws Exception;

    @Override
    public abstract List<String> getVariables();

    @Override
    public abstract String toString();

    @Override
    public abstract Expression assign(String var, Expression expression);

    @Override
    public abstract Expression nandify();

    @Override
    public abstract Expression norify();

    @Override
    public abstract Expression simplify();

    /**
     * The method checks if two Base expression are the same visually.
     * @param other The other expression
     * @return True if they are the same visually, false otherwise
     */
    public abstract Boolean equals(Expression other);
}
