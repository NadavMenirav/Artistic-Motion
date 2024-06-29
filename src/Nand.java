//Nadav Menirav 330845678

/**
 * Nand class.
 */
public class Nand extends BinaryExpression {
    /**
     * Constructor.
     * @param firstExpression First Expression
     * @param secondExpression Second Expression
     */
    public Nand(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "A");
    }

    @Override
    public Boolean operate(Boolean first, Boolean second) {
        return !(first && second);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Nand(
            this.getFirstExpression().assign(var, expression),
             this.getSecondExpression().assign(var, expression)
        );
    }
}