package jp.rouh.fourfours.component;

public class Power implements Evaluable{
    private final Evaluable leftOperand;
    private final Evaluable rightOperand;
    public Power(Evaluable leftOperand, Evaluable rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public Constant evaluate() throws EvaluationException {
        var leftValue = leftOperand.evaluate().value();
        var rightValue = rightOperand.evaluate().value();
        try {
            var powerValue = leftValue.pow(rightValue.intValueExact());
            return new Constant(powerValue);
        }catch (ArithmeticException e){
            throw new EvaluationException("power exponent too much large.");
        }
    }

    @Override
    public String toString() {
        return "POWER("+leftOperand+","+rightOperand+")";
    }
}
