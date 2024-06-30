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

    @Override
    public Expression simplify() {
        try {
            //1&x case
            if (this.getFirstExpression().evaluate()) {
                return this.getSecondExpression().simplify();
            }
            //0&x case
            return new Val(false);
        } catch (Exception error) {
            System.err.println(error);
        }
        try {
            //x&1 case
            if (this.getSecondExpression().evaluate()) {
                return this.getFirstExpression().simplify();
            }
            //x&0 case
            return new Val(false);
        } catch (Exception error) {
            System.err.println(error);
        }
    }
}