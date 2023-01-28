package jp.rouh.fourfours.component;

import java.math.BigInteger;

public class Negate implements Evaluable{
    private final Evaluable operand;
    public Negate(Evaluable operand){
        this.operand = operand;
    }

    @Override
    public Constant evaluate() throws EvaluationException {
        var value = operand.evaluate().value();
        return new Constant(value.multiply(BigInteger.valueOf(-1)));
    }

    @Override
    public String toString() {
        return "NEGATE("+operand+")";
    }
}
