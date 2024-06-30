//Nadav Menirav 330845678

/**
 * Xor class.
 */
public class Xor extends BinaryExpression {
    /**
     * Constructor.
     * @param firstExpression First Expression
     * @param secondExpression Second Expression
     */
    protected Xor(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "^");
    }

    @Override
    protected Boolean operate(Boolean first, Boolean second) {
        return first ^ second;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Xor(
            this.getFirstExpression().assign(var, expression),
            this.getSecondExpression().assign(var, expression)
        );
    }

    @Override
    public Expression nandify() {
        // A XOR B = [ A NAND ( A NAND B ) ] NAND [ B NAND ( A NAND B ) ]
        Nand nandOfFirstAndSecond = new Nand(this.getFirstExpression(), this.getSecondExpression());
        return new Nand(
            new Nand(this.getFirstExpression(), nandOfFirstAndSecond),
            new Nand(this.getSecondExpression(), nandOfFirstAndSecond)
        );
    }

    @Override
    public Expression norify() {
        // A XOR B =  [ ( A NOR A ) NOR ( B NOR B ) ] NOR ( A NOR B )
        Expression firstNorified = this.getFirstExpression().norify();
        Expression secondNorified = this.getSecondExpression().norify();
        Expression norOfFirsts = new Nor(firstNorified, firstNorified);
        Expression norOfSeconds = new Nor(secondNorified, secondNorified);
        Expression norFirstSecond = new Nor(firstNorified, secondNorified);

        return new Nor(new Nor(norOfFirsts, norOfSeconds), norFirstSecond);
    }

    @Override
    public Expression simplify() {
      Expression simpleFirst = this.getFirstExpression().simplify();
      Expression simpleSecond = this.getSecondExpression().simplify();

      // Deals with the case in which one of the expressions is true and the other is false
      if ((simpleFirst.toString().equals("T") && simpleSecond.toString().equals("F"))
      || (simpleFirst.toString().equals("F") && simpleSecond.toString().equals("T"))) {
        return new Val(true);
      }

      // Both of the expressions are the same
      if (simpleFirst.toString().equals(simpleSecond.toString())) {
        return new Val(false);
      }

      // First is true (We know second is not a Boolean value)
      if (simpleFirst.toString().equals("T")) {
        return new Not(simpleSecond);
      }

      // Second is true (We know first is not a Boolean value)
      if (simpleSecond.toString().equals("T")) {
        return new Not(simpleFirst);
      }

      // First is false (We know second is not a Boolean value)
      if (simpleFirst.toString().equals("F")) {
        return simpleSecond;
      }

      // Second is false (We know first is not a Boolean value)
      if (simpleSecond.toString().equals("F")) {
        return simpleFirst;
      }

      // No further simplification was found
      return new Xor(simpleFirst, simpleSecond);
  }
}