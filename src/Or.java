//Nadav Menirav 330845678

/**
 * Or class.
 */
public class Or extends BinaryExpression {
    /**
     * Constructor.
     * @param firstExpression First Expression
     * @param secondExpression Second Expression
     */
    public Or(BaseExpression firstExpression, BaseExpression secondExpression) {
        super(firstExpression, secondExpression, "|");
    }

    @Override
    public Boolean operate(Boolean first, Boolean second) {
        return first || second;
    }

    @Override
    public BaseExpression assign(String var, Expression expression) {
        return new Or(
            this.getFirstExpression().assign(var, expression),
             this.getSecondExpression().assign(var, expression)
        );
    }

    @Override
    public BaseExpression nandify() {
        return new Nand(
            new Not(this.getFirstExpression()).nandify(),
            new Not(this.getSecondExpression()).nandify()
        );
    }

    @Override
    public BaseExpression norify() {
        return this.nandify().norify();
    }
}
