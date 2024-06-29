// Nadav Menriav 330845678

import java.util.Map;
import java.util.List;

/**
 * Val class.
 */
public class Val implements Expression {
    private Boolean value;

    /**
     * Constructor of the Val class.
     * @param value The value of this Val
     */
    public Val(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) {
        return value;
    }

    @Override
    public Boolean evaluate() {
        return value;
    }

    @Override
    public List<String> getVariables() {
        return null;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return expression;
    }
}
