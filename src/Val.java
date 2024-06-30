// Nadav Menriav 330845678

import java.util.Map;
import java.util.List;

/**
 * Val class.
 */
public final class Val implements Expression {
    private final Boolean value;

    /**
     * Constructor of the Val class.
     * @param value The value of this Val
     */
    protected Val(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return value;
    }

    @Override
    public Boolean evaluate() throws Exception {
        return value;
    }

    @Override
    public List<String> getVariables() {
        return null;
    }

    @Override
    public String toString() {
        return this.value ? "T" : "F";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Val(this.value);
    }

    @Override
    public Expression nandify() {
        return new Val(this.value);
    }

    @Override
    public Expression norify() {
        return new Val(this.value);
    }

    @Override
    public Expression simplify() {
        return new Val(this.value);
    }
}
