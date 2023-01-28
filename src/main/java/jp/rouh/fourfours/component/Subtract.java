package jp.rouh.fourfours.component;

public class Subtract implements Evaluable{
    private final Evaluable leftOperand;
    private final Evaluable rightOperand;
    public Subtract(Evaluable leftOperand, Evaluable rightOperand){
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public Constant evaluate() throws EvaluationException {
        var leftValue = leftOperand.evaluate().value();
        var rightValue = rightOperand.evaluate().value();
        var subtractValue = leftValue.subtract(rightValue);
        return new Constant(subtractValue);
    }

    @Override
    public String toString() {
        return "SUBTRACT("+leftOperand+","+rightOperand+")";
    }
}
