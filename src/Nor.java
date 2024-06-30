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
    public Nor(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "V");
    }

    @Override
    public Boolean operate(Boolean first, Boolean second) {
        return !(first || second);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Nor(
            this.getFirstExpression().assign(var, expression),
            this.getSecondExpression().assign(var, expression)
        );
    }

    @Override
    public Expression nandify() {
        // A NOR B = [ ( A NAND A ) NAND ( B NAND B ) ] NAND [ ( A NAND A ) NAND ( B NAND B ) ]
        return new Not(
            new Or(
                this.getFirstExpression().nandify(),
                this.getSecondExpression().nandify()
            ).nandify()
        ).nandify();
    }

    @Override
    public Expression norify() {
        // We just need to norify the inner expressions
        return new Nor(this.getFirstExpression().norify(), this.getSecondExpression().norify());
    }

    @Override
    public Expression simplify() {

        Expression simpleFirst = this.getFirstExpression().simplify();
        Expression simpleSecond = this.getSecondExpression().simplify();

        // Handles the case where one of the expressions is true
        if (simpleFirst.toString().equals("T") || simpleSecond.toString().equals("T")) {
            return new Val(false);
        }

        // First expression is false
        if (simpleFirst.toString().equals("F")) {
            return new Not(simpleSecond);
        }

        // Second is false
        if (simpleSecond.toString().equals("F")) {
            return new Not(simpleFirst);
        }

        // Both expressions are the same
        if (simpleFirst.toString().equals(simpleSecond.toString())) {
            return new Not(simpleFirst);
        }
        return new Nor(simpleFirst, simpleSecond);
    }
}
