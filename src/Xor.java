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
    public Xor(BaseExpression firstExpression, BaseExpression secondExpression) {
        super(firstExpression, secondExpression, "^");
    }

    @Override
    public Boolean operate(Boolean first, Boolean second) {
        return first ^ second;
    }

    @Override
    public BaseExpression assign(String var, Expression expression) {
        return new Xor(
            this.getFirstExpression().assign(var, expression),
             this.getSecondExpression().assign(var, expression)
        );
    }

    @Override
    public BaseExpression nandify() {
        return new Or(
            new And(
                new Not(
                    this.getFirstExpression().nandify()
                ).nandify(),
                this.getSecondExpression().nandify()
            ).nandify(),
            new And(
                this.getFirstExpression().nandify(),
                new Not(
                    this.getSecondExpression().nandify()
                ).nandify()
            ).nandify()
        ).nandify();
    }

    @Override
    public BaseExpression norify() {
        return this.nandify().norify();
    }
}
