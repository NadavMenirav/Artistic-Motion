//Nadav Menirav 330845678

/**
 * Xor class.
 */
public class Xor extends BinaryExpression {
    /**
     * Constructor.
     * @param firstExpression First Expression
     * @param secondExpression Second Expression
     */
    public Xor(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "^");
    }

    @Override
    public Boolean operate(Boolean first, Boolean second) {
        return first ^ second;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Xor(
            this.getFirstExpression().assign(var, expression),
             this.getSecondExpression().assign(var, expression)
        );
    }
}