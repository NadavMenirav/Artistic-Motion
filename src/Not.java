//Nadav Menirav 330845678

/**
 * Not class.
 */
public final class Not extends UnaryExpression {
    /**
     * Constructor.
     * @param expression The expression
     */
    protected Not(Expression expression) {
        super(expression, "~");
    }

    @Override
    protected Boolean operate(Boolean bool) {
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
        Expression simpleExpression = this.getExpression().simplify();
        // Expression is false
        if (simpleExpression.toString().equals("F")) {
            return new Val(true);
        }
        // Expression is true
        if (simpleExpression.toString().equals("T")) {
            return new Val(false);
        }
        return new Not(simpleExpression);
    }
}
