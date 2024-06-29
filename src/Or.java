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
    public Or(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "|");
    }

    @Override
    public Boolean operate(Boolean first, Boolean second) {
        return first || second;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Or(
            this.getFirstExpression().assign(var, expression),
             this.getSecondExpression().assign(var, expression)
        );
    }

    @Override
    public Expression nandify() {
        return new Nand(
            new Nand(this.getFirstExpression(), this.getFirstExpression()),
            new Nand(this.getSecondExpression(), this.getSecondExpression())
        );
    }
}
