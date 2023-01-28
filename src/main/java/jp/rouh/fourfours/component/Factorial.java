package jp.rouh.fourfours.component;

import java.math.BigInteger;

public class Factorial implements Evaluable{
    private final Evaluable operand;
    public Factorial(Evaluable operand){
        this.operand = operand;
    }

    @Override
    public Constant evaluate() throws EvaluationException {
        var value = operand.evaluate().value();
        if (value.signum()==-1){
            throw new EvaluationException("factorial operation cannot be applied to negative number.");
        }
        if (value.compareTo(BigInteger.valueOf(Integer.MAX_VALUE))>0){
            throw new EvaluationException("factorial operation with too much large operand.");
        }
        var factorialValue = factorial(value);
        return new Constant(factorialValue);
    }

    private static BigInteger factorial(BigInteger n){
        if (n.equals(BigInteger.ZERO)){
            return BigInteger.ZERO;
        }
        if (n.equals(BigInteger.ONE)){
            return BigInteger.ONE;
        }
        return n.multiply(factorial(n.subtract(BigInteger.ONE)));
    }

    @Override
    public String toString() {
        return "FACTORIAL("+operand+")";
    }
}
