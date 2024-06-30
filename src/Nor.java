//Nadav Menirav 330845678

/**
 * Nor class.
 */
public class Nor extends BinaryExpression {
    /**
     * Constructor.
     * @param firstExpression First Expression
     * @param secondExpression Second Expression
     */
    public Nor(BaseExpression firstExpression, BaseExpression secondExpression) {
        super(firstExpression, secondExpression, "V");
    }

    @Override
    public Boolean operate(Boolean first, Boolean second) {
        return !(first || second);
    }

    @Override
    public BaseExpression assign(String var, Expression expression) {
        return new Nor(
            this.getFirstExpression().assign(var, expression),
             this.getSecondExpression().assign(var, expression)
        );
    }

    @Override
    public BaseExpression nandify() {
        return new Not(
            new Or(
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
