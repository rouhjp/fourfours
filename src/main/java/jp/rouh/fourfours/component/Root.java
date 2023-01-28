package jp.rouh.fourfours.component;

import java.math.BigInteger;

public class Root implements Evaluable{
    private final Evaluable operand;
    public Root(Evaluable operand){
        this.operand = operand;
    }

    @Override
    public Constant evaluate() throws EvaluationException {
        try {
            var value = operand.evaluate().value();
            var rootValueAndReminder = value.sqrtAndRemainder();
            var rootValue = rootValueAndReminder[0];
            var reminder = rootValueAndReminder[1];
            if (!reminder.equals(BigInteger.ZERO)) {
                throw new EvaluationException("evaluated root value has reminder");
            }
            return new Constant(rootValue);
        }catch (ArithmeticException e){
            throw new EvaluationException(e);
        }
    }

    @Override
    public String toString() {
        return "ROOT("+operand+")";
    }
}
