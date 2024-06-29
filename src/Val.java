// Nadav Menriav 330845678

import java.util.Map;
import java.util.List;

/**
 * Val class.
 */
public final class Val implements Expression {
    private Boolean value;

    /**
     * Constructor of the Val class.
     * @param value The value of this Val
     */
    public Val(Boolean value) {
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
        return expression;
    }
}
