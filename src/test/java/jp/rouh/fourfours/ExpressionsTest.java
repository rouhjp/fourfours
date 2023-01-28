package jp.rouh.fourfours;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static jp.rouh.fourfours.Expressions.evaluate;

public class ExpressionsTest {

    @Test
    public void testSum(){
        var expression = "S4";
        Assertions.assertEquals(10, evaluate(expression).intValue());
    }

    @Test
    public void testFactorial(){
        var expression = "4!";
        Assertions.assertEquals(24, evaluate(expression).intValue());
    }

    @Test
    public void testRoot(){
        var expression ="R4";
        Assertions.assertEquals(2, evaluate(expression).intValue());
    }

    @Test
    public void testAdd(){
        var expression = "4+4";
        Assertions.assertEquals(8, evaluate(expression).intValue());
    }

    @Test
    public void testSubtract(){
        var expression = "4-4";
        Assertions.assertEquals(0, evaluate(expression).intValue());
    }

    @Test
    public void testMultiply(){
        var expression = "4*4";
        Assertions.assertEquals(16, evaluate(expression).intValue());
    }

    @Test
    public void testDivide(){
        var expression = "4/4";
        Assertions.assertEquals(1, evaluate(expression).intValue());
    }

    @Test
    public void testPower(){
        var expression = "4^4";
        Assertions.assertEquals(256, evaluate(expression).intValue());
    }

    @Test
    public void testUnwrapBracket(){
        var expression = "(4)";
        Assertions.assertEquals(4, evaluate(expression).intValue());
    }

    @Test
    public void testDoubleUnaryOperators(){
        var expression = "SSR4";
        Assertions.assertEquals(6, evaluate(expression).intValue());
    }

    @Test
    public void testDoubleSideUnaryOperators(){
        var expression = "S4!";
        Assertions.assertEquals(300, evaluate(expression).intValue());
    }

    @Test
    public void testBracket(){
        var expression = "(S4)!";
        Assertions.assertEquals(3628800, evaluate(expression).intValue());
    }

    @Test
    public void testNest(){
        var expression = "4*(4-4)+4";
        Assertions.assertEquals(4, evaluate(expression).intValue());
    }

    @Test
    public void testMultipleNest(){
        var expression = "R((SR4+R4)^R4)";
        Assertions.assertEquals(5, evaluate(expression).intValue());
    }

    @Test
    public void testMultipleDigitConstant(){
        var expression = "444/4";
        Assertions.assertEquals(111, evaluate(expression).intValue());
    }

}
