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
    public And(BaseExpression firstExpression, BaseExpression secondExpression) {
        super(firstExpression, secondExpression, "&");
    }

    @Override
    public Boolean operate(Boolean first, Boolean second) {
        return first && second;
    }

    @Override
    public BaseExpression assign(String var, Expression expression) {
        return new And(
            this.getFirstExpression().assign(var, expression),
             this.getSecondExpression().assign(var, expression)
        );
    }

    @Override
    public BaseExpression nandify() {
        return new Not(new Nand(this.getFirstExpression().nandify(), this.getSecondExpression().nandify())).nandify();
    }

    @Override
    public BaseExpression norify() {
        return this.nandify().norify();
    }

    @Override
    public BaseExpression simplify() {
        if (this.getFirstExpression().equals())
    }
}