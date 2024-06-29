//Nadav Menirav 330845678

/**
 * And class.
 */
public class And extends BinaryExpression {
    /**
     * Constructor.
     * @param firstExpression First Expression
     * @param secondExpression Second Expression
     */
    public And(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "&");
    }

    @Override
    public Boolean operate(Boolean first, Boolean second) {
        return first && second;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new And(
            this.getFirstExpression().assign(var, expression),
             this.getSecondExpression().assign(var, expression)
        );
    }

    @Override
    public Expression nandify() {
        return new Not(new Nand(this.getFirstExpression().nandify(), this.getSecondExpression().nandify())).nandify();
    }

    @Override
    public Expression norify() {
        return this.nandify().norify();
    }
}