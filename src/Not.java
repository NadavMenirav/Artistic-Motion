//Nadav Menirav 330845678

import java.util.Map;

/**
 * Not class.
 */
public final class Not {
    private final Expression expression;

    /**
     * Constructor.
     * @param expression The expression
     */
    public Not(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(this.expression.evaluate(assignment));
    }

    @Override
    public Boolean evaluate() throws Exception{
        return !(this.expression.evaluate());
    }
}
