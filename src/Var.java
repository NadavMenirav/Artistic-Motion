//Nadav Menirav 330845678

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Var class.
 */
public final class Var implements Expression {
    private final String name;
    private final Expression expression;

    /**
     * Constructor.
     * @param name
     * @param expression
     */
    public Var(String name, Expression expression) {
        this.name = new String(name);
        this.expression = expression;
    }

    /**
     * Constructor.
     * @param var
     */
    public Var(Var var) {
        this(var.getName(), var.getExpression());
    }

    /**
     * Getter of the name field.
     * @return The name of this Var
     */
    public String getName() {
        return new String(name);
    }

    /**
     * Getter of the Expression field.
     * @return the Expression of this Var.
     */
    public Expression getExpression() {
        return expression;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        final Boolean newValue = this.expression.evaluate();
        for (Map.Entry<String, Boolean> entry : assignment.entrySet()) {
            final String key = entry.getKey();
            if (!this.name.equals(key)) {
                continue;
            }
            entry.setValue(newValue);
        }
        return newValue;
    }

    @Override
    public Boolean evaluate() throws Exception {
        return this.expression.evaluate();
    }

    @Override
    public List<String> getVariables() {
        List<String> variables = new ArrayList<String>();
        variables.add(new String(name));
    }

    @Override
    public String toString() {
        return new String(name);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.name.equals(var)) {
            return expression;
        }
        return new Var(this);
    }

}
