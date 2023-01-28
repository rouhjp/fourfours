package jp.rouh.fourfours.component;

import java.math.BigInteger;

public record Constant(BigInteger value) implements Evaluable {

    @Override
    public Constant evaluate() throws EvaluationException {
        return this;
    }

    @Override
    public String toString() {
        return "CONSTANT("+value.toString()+")";
    }
}
