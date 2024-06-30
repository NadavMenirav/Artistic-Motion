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
    protected Boolean operate(Boolean first, Boolean second) {
        return !(first && second);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Nand(
            this.getFirstExpression().assign(var, expression),
            this.getSecondExpression().assign(var, expression)
        );
    }

    @Override
    public Expression nandify() {
        //We just need to nandify the inner expressions.
        return new Nand(this.getFirstExpression().nandify(), this.getSecondExpression().nandify());
    }

    @Override
    public Expression norify() {
        //A Nand B = [ ( A NOR A ) NOR ( B NOR B ) ] NOR [ ( A NOR A ) NOR ( B NOR B ) ]

        Expression firstNorified = this.getFirstExpression().norify();
        Expression secondNorified = this.getSecondExpression().norify();
        Expression norFirst = new Nor(firstNorified, firstNorified);
        Expression norSecond = new Nor(secondNorified, secondNorified);
        return new Nor(new Nor(norFirst, norSecond), new Nor(norFirst, norSecond));
    }

    @Override
    public Expression simplify() {
        Expression simpleFirst = this.getFirstExpression().simplify();
        Expression simpleSecond = this.getSecondExpression().simplify();

        // Handles a case where both expressions are true
        if (simpleFirst.toString().equals("T") && simpleSecond.toString().equals("T")) {
            return new Val(false);
        }

        // One of the expressions are false
        if (simpleFirst.toString().equals("F") || simpleSecond.toString().equals("F")) {
            return new Val(true);
        }

        // First is true
        if (simpleFirst.toString().equals("T")) {
            return new Not(simpleSecond);
        }

        // Second is true
        if (simpleSecond.toString().equals("T")) {
            return new Not(simpleFirst);
        }

        // Both expressions are the same
        if (simpleFirst.toString().equals(simpleSecond.toString())) {
            return new Not(simpleFirst);
        }

        // No further simplification was found
        return new Nand(simpleFirst, simpleSecond);
    }
}
