//Nadav Menirav 330845678

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Var class.
 */
public final class Var implements Expression {
    private final String name;

    /**
     * Constructor.
     * @param name
     */
    public Var(String name) {
        this.name = new String(name);
    }

    /**
     * Constructor.
     * @param var
     */
    public Var(Var var) {
        this(var.getName());
    }

    /**
     * Getter of the name field.
     * @return The name of this Var
     */
    public String getName() {
        return new String(name);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        if (assignment.containsKey(this.name)) {
            return assignment.get(this.name);
        }
        throw new Exception("Var " + this.toString() + " is not assigned");
    }

    @Override
    public Boolean evaluate() throws Exception {
        throw new Exception("Var " + this.toString() + " is not assigned");
    }

    @Override
    public List<String> getVariables() {
        List<String> variables = new ArrayList<String>();
        variables.add(new String(name));
        return variables;
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

    @Override
    public Expression nandify() {
        return new Var(this.name);
    }

    @Override
    public Expression norify() {
        return this.nandify().norify();
    }
}
