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
    protected Boolean operate(Boolean first, Boolean second) {
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
        // A OR B = ( A NAND A ) NAND ( B NAND B )
        return new Nand(
            new Not(this.getFirstExpression().nandify()).nandify(),
            new Not(this.getSecondExpression().nandify()).nandify()
        );
    }

    @Override
    public Expression norify() {
        // A OR B = ( A NOR B ) NOR ( A NOR B )
        Expression expression = new Nor(this.getFirstExpression().norify(), this.getSecondExpression().norify());
        return new Nor(expression, expression);
    }

    @Override
    public Expression simplify() {
        Expression simpleFirst = this.getFirstExpression().simplify();
        Expression simpleSecond = this.getSecondExpression().simplify();

        // Deals with the case in which the two expressions are the same
        if (simpleFirst.toString().equals(simpleSecond.toString())) {
            return simpleFirst;
        }

        // One of the expressions is true
        if (simpleFirst.toString().equals("T") || simpleSecond.toString().equals("T")) {
            return new Val(true);
        }

        // First is false
        if (simpleFirst.toString().equals("F")) {
            return simpleSecond;
        }

        // Second is false
        if (simpleSecond.toString().equals("F")) {
            return simpleFirst;
        }

        // No further simplification was found
        return new Or(simpleFirst, simpleSecond);
    }
}
