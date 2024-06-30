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
    protected Xnor(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "#");
    }

    @Override
    protected Boolean operate(Boolean first, Boolean second) {
        return !(first ^ second);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Xnor(
            this.getFirstExpression().assign(var, expression),
            this.getSecondExpression().assign(var, expression)
        );
    }

    @Override
    public Expression nandify() {
        // A XNOR B = [ ( A NAND A ) NAND ( B NAND B ) ] NAND ( A NAND B )
        return new Nand(
            new Nand(
                new Not(this.getFirstExpression().nandify()).nandify(),
                new Not(this.getSecondExpression().nandify()).nandify()
            ),
            new Nand(
                this.getFirstExpression().nandify(),
                this.getSecondExpression().nandify()
            )
        );
    }

    @Override
    public Expression norify() {
        // A XNOR B = [ A NOR ( A NOR B ) ] NOR [ B NOR ( A NOR B ) ]
        Expression first = this.getFirstExpression().norify();
        Expression second = this.getSecondExpression().norify();
        return new Nor(new Nor(first, new Nor(first, second)), new Nor(second, new Nor(first, second)));
    }

    @Override
    public Expression simplify() {
        Expression simpleFirst = this.getFirstExpression().simplify();
        Expression simpleSecond = this.getSecondExpression().simplify();

        // Deals with the Case of which one of the expressions is true and the other is false
        if ((simpleFirst.toString().equals("T") && simpleSecond.toString().equals("F"))
        || (simpleFirst.toString().equals("F") && simpleSecond.toString().equals("T"))) {
            return new Val(false);
        }

        // Both of the expressions are the same
        if (simpleFirst.toString().equals(simpleSecond.toString())) {
            return new Val(true);
        }
        return new Xnor(simpleFirst, simpleSecond);
    }
}
