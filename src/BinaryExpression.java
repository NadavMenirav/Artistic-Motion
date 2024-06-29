//Nadav Menriav 330845678

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * BinaryExpression class.
 */
public abstract class BinaryExpression implements Expression {
    private final Expression firstExpression;
    private final Expression secondExpression;
    private String expressionSymbol;

    /**
     * Constructor.
     * @param firstExpression The first Expression
     * @param secondExpression The second Expression
     */
    public BinaryExpression(Expression firstExpression, Expression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    /**
     * Make the logical operation.
     * @return The Boolean result of the operation
     * @param first The first Boolean we operate on
     * @param second The second Boolean we operate on
     */
    public abstract Boolean operate(Boolean first, Boolean second);

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.operate(this.firstExpression.evaluate(assignment), this.secondExpression.evaluate(assignment));
    }

    @Override
    public Boolean evaluate() throws Exception {
        return this.operate(this.firstExpression.evaluate(), this.secondExpression.evaluate());
    }

    @Override
    public List<String> getVariables() {
        Set<String> mySet = new LinkedHashSet<>(this.firstExpression.getVariables());
        mySet.addAll(this.secondExpression.getVariables());

        return new ArrayList<String>(mySet);
    }

    @Override
    public String toString() {
        return this.firstExpression.toString() + this.expressionSymbol + this.secondExpression;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new And(this.firstExpression.assign(var, expression), this.secondExpression.assign(var, expression));
    }


}
