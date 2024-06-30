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
        return new Nand(this.getExpression().nandify(), this.getExpression().nandify());
    }

    @Override
    public Expression norify() {
        return this.nandify().norify();
    }
}
