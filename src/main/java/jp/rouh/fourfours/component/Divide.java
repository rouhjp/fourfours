package jp.rouh.fourfours.component;

public class Divide implements Evaluable{
    private final Evaluable leftOperand;
    private final Evaluable rightOperand;
    public Divide(Evaluable leftOperand, Evaluable rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public Constant evaluate() throws EvaluationException {
        try {
            var leftValue = leftOperand.evaluate().value();
            var rightValue = rightOperand.evaluate().value();
            var divideAndRemainder = leftValue.divideAndRemainder(rightValue);
            var divideValue = divideAndRemainder[0];
            var reminder = divideAndRemainder[1];
            if (reminder.signum()!=0){
                throw new EvaluationException("divide indivisible value");
            }
            return new Constant(divideValue);
        }catch (ArithmeticException e){
            throw new EvaluationException(e);
        }
    }

    @Override
    public String toString() {
        return "DIVIDE("+leftOperand+","+rightOperand+")";
    }
}
