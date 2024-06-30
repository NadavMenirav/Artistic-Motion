//Nadav Menirav 330845678

/**
 * Xnor class.
 */
public class Xnor extends BinaryExpression {
    /**
     * Constructor.
     * @param firstExpression First Expression
     * @param secondExpression Second Expression
     */
    public Xnor(BaseExpression firstExpression, BaseExpression secondExpression) {
        super(firstExpression, secondExpression, "#");
    }

    @Override
    public Boolean operate(Boolean first, Boolean second) {
        return !(first ^ second);
    }

    @Override
    public BaseExpression assign(String var, Expression expression) {
        return new Xnor(
            this.getFirstExpression().assign(var, expression),
             this.getSecondExpression().assign(var, expression)
        );
    }

    @Override
    public BaseExpression nandify() {
        return new Not(
            new Xor(
                this.getFirstExpression().nandify(),
                this.getSecondExpression().nandify()
            ).nandify()
        ).nandify();
    }

    @Override
    public BaseExpression norify() {
        return this.nandify().norify();
    }
}
