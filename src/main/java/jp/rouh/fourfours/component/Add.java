package jp.rouh.fourfours.component;

public class Add implements Evaluable{
    private final Evaluable leftOperand;
    private final Evaluable rightOperand;
    public Add(Evaluable leftOperand, Evaluable rightOperand){
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public Constant evaluate() throws EvaluationException {
        var leftValue = leftOperand.evaluate().value();
        var rightValue = rightOperand.evaluate().value();
        return new Constant(leftValue.add(rightValue));
    }

    @Override
    public String toString() {
        return "ADD("+leftOperand+","+rightOperand+")";
    }
}
