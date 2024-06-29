//Nadav Menirav 330845678

import java.util.Map;
import java.util.List;

/**
 * Expression interface.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided in the assignment, and returns the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     * @param assignment The Map
     * @return Boolean object contains the evaluation of the variable if its valid, an exception is thrown if not
     * @throws Exception Indicates that the method could throw an exception
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * A convenience method. uses empty assignment.
     * @return Boolean object contains the evaluation of the variable if its valid, an exception is thrown otherwise
     * @throws Exception Indicates that the method could throw an exception
     */
    Boolean evaluate() throws Exception;

    /**
     * Returns a List of the variables in the expression.
     * @return A list of the variables.
     */
    List<String> getVariables();

    /**
     * Returns a nice String representation of the expression.
     * @return String representation
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable.
     * Var is replaced with the provided expression (Does not modify the current expression).
     * @param var The variable to replace
     * @param expression The expression to put
     * @return The new expression
     */
    Expression assign(String var, Expression expression);

    /**
     * Converts all the operations to the logical Nand operation.
     * @return The new Expression
     */
    Expression nandify();

    /**
     * Converts all the operations to the logical Nor operation.
     * @return The new Expression
     */
    Expression norify();
}