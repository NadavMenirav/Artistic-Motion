//Nadav Menriav 330845678

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * BinaryExpression class.
 */
public abstract class BinaryExpression extends BaseExpression {
    private final Expression firstExpression;
    private final Expression secondExpression;

    /**
     * Constructor.
     * @param firstExpression The first Expression
     * @param secondExpression The second Expression
     * @param expressionSymbol The symbol of the operation
     */
    public BinaryExpression(Expression firstExpression, Expression secondExpression, String expressionSymbol) {
        super(expressionSymbol);
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    /**
     * Getter of the firstExpression field.
     * @return The first Expression
     */
    public Expression getFirstExpression() {
        return this.firstExpression;
    }

    /**
     * Getter of the secondExpression field.
     * @return The second Expression
     */
    public Expression getSecondExpression() {
        return this.secondExpression;
    }

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
        return "("
         + this.firstExpression.toString()
         + " "
         + this.getExpressionSymbol()
         + " "
         + this.secondExpression
         + ")";
    }

    @Override
    public abstract Expression assign(String var, Expression expression);

    /**
     * Make the logical operation.
     * @return The Boolean result of the operation
     * @param first The first Boolean we operate on
     * @param second The second Boolean we operate on
     */
    public abstract Boolean operate(Boolean first, Boolean second);

}
