//Nadav Menirav 330845678

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.LinkedHashSet;

/**
 * And class.
 */
public class And implements Expression {
    private final Expression firstExpression;
    private final Expression secondExpression;

    /**
     * Constructor.
     * @param firstExpression First Expression
     * @param secondExpression Second Expression
     */
    public And(Expression firstExpression, Expression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.firstExpression.evaluate(assignment) && this.secondExpression.evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        return this.firstExpression.evaluate() && this.secondExpression.evaluate();
    }

    @Override
    public List<String> getVariables() {
        Set<String> mySet = new LinkedHashSet<>(this.firstExpression.getVariables());
        mySet.addAll(this.secondExpression.getVariables());

        return new ArrayList<String>(mySet);
    }
}