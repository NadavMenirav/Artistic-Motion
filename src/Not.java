//Nadav Menirav 330845678

/**
 * Not class.
 */
public final class Not extends UnaryExpression {
    /**
     * Constructor.
     * @param expression The expression
     */
    public Not(Expression expression) {
        super(expression, "~");
    }

    @Override
    public Boolean operate(Boolean bool) {
        return !bool;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Not(this.getExpression().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        // NOT( A ) = A Nand A
        return new Nand(this.getExpression().nandify(), this.getExpression().nandify());
    }

    @Override
    public Expression norify() {
        // NOT( A ) = A Nor A
        Expression expression = this.getExpression().norify(); // Norifies inner expression
        return new Nor(expression, expression);
    }

    @Override
    public Expression simplify() {
        // No simplification was provided to the Not operandor
        return new Not(this);
    }
}
