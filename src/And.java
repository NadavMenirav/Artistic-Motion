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
    protected Boolean operate(Boolean first, Boolean second) {
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
        return new Nand(
            new Nand(this.getFirstExpression().nandify(), this.getSecondExpression().nandify()),
            new Nand(this.getFirstExpression().nandify(), this.getSecondExpression().nandify())
        );
    }

    @Override
    public Expression norify() {
        Expression firstNorified = this.getFirstExpression().norify();
        Expression secondNorified = this.getSecondExpression().norify();
        return new Nor(new Nor(firstNorified, firstNorified), new Nor(secondNorified, secondNorified));
    }

    @Override
    public Expression simplify() {
        Expression simpleFirst = this.getFirstExpression().simplify();
        Expression simpleSecond = this.getSecondExpression().simplify();

        // Handles the case where and is used on two identical expressions.
        if (simpleFirst.toString().equals(simpleSecond.toString())) {
            return simpleFirst;
        }

        // One of the expression is false
        if (simpleFirst.toString().equals("F") || simpleSecond.toString().equals("F")) {
            return new Val(false);
        }

        // First expression is true
        if (simpleFirst.toString().equals("T")) {
            return simpleSecond;
        }

        // Second expression is false
        if (simpleSecond.toString().equals("T")) {
            return simpleFirst;
        }

        // No further simplification was found
        return new And(simpleFirst, simpleSecond);
    }
}