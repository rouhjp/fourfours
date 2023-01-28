package jp.rouh.fourfours.component;

import java.math.BigInteger;

public class Sum implements Evaluable{
    private final Evaluable operand;
    public Sum(Evaluable operand){
        this.operand = operand;
    }

    @Override
    public Constant evaluate() throws EvaluationException {
        var value = operand.evaluate().value();
        if (value.signum()==-1){
            throw new EvaluationException("sum operation cannot be applied to negative number.");
        }
        if (value.signum()==0){
            return new Constant(BigInteger.ZERO);
        }
        var sumValue = value.multiply(value.add(BigInteger.ONE)).divide(BigInteger.TWO);
        return new Constant(sumValue);
    }

    @Override
    public String toString() {
        return "SUM("+operand+")";
    }
}
