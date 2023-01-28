package jp.rouh.fourfours.component;

public class Multiply implements Evaluable{
    private final Evaluable leftOperand;
    private final Evaluable rightOperand;
    public Multiply(Evaluable leftOperand, Evaluable rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public Constant evaluate() throws EvaluationException {
        var leftValue = leftOperand.evaluate().value();
        var rightValue = rightOperand.evaluate().value();
        var multiplyValue = leftValue.multiply(rightValue);
        return new Constant(multiplyValue);
    }

    @Override
    public String toString() {
        return "MULTIPLY("+leftOperand+","+rightOperand+")";
    }
}
