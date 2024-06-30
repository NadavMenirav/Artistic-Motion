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
}
