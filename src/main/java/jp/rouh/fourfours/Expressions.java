package jp.rouh.fourfours;

import jp.rouh.fourfours.component.*;

import java.math.BigInteger;
import java.util.function.Function;
import java.util.regex.Pattern;

public final class Expressions {
    private Expressions(){
        throw new AssertionError("instantiate utility class");
    }
    private static final Pattern PERFECT_PATTERN = Pattern.compile("[-+/*^SR4!()]+");
    private static final Pattern ACCEPTABLE_PATTERN = Pattern.compile("[\\d-+/*^SR4!()]*");

    public static boolean isPerfect(String expression){
        return PERFECT_PATTERN.matcher(expression.toUpperCase().replaceAll("\\s", "")).matches()
                && expression.replaceAll("[^4]", "").length()==4;
    }

    public static String evaluate(String expression, Function<Exception, String> exceptionHandler){
        try{
            return parse(expression).evaluate().value().toString();
        }catch (Exception e){
            return exceptionHandler.apply(e);
        }
    }

    public static BigInteger evaluate(String expression){
        return parse(expression).evaluate().value();
    }

    public static Evaluable parse(String expression){
        var e = expression.toUpperCase().replaceAll("\\s", "");
        if (e.isEmpty()){
            throw new IllegalArgumentException("expression is empty");
        }
        if (!ACCEPTABLE_PATTERN.matcher(e).matches()){
            throw new IllegalArgumentException("expression contains invalid character");
        }
        int node = 0;
        for (int nest = 0, i = 0; i<e.length(); i++){
            char c = e.charAt(i);
            if (c=='('){
                nest++;
                if (nest==0){
                    node++;
                }
            }
            if (c==')'){
                nest--;
                if (nest<0){
                    throw new IllegalArgumentException("unexpected end of bracket");
                }
            }
            if (nest>0) continue;
            var left = e.substring(0, i);
            var right = e.substring(i + 1);
            if (c=='+'){
                if (left.isEmpty() || right.isEmpty()){
                    return parse(e.substring(1));
                }
                return new Add(parse(left), parse(right));
            }
            if (c=='-'){
                if (left.isEmpty() || right.isEmpty()){
                    return parse(e.substring(1));
                }
                return new Subtract(parse(left), parse(right));
            }
        }
        if (node>=2){
            throw new IllegalArgumentException("missing operator");
        }
        for (int nest = 0, i = 0; i<e.length(); i++){
            char c = e.charAt(i);
            if (c=='('){
                nest++;
            }
            if (c==')'){
                nest--;
            }
            if (nest>0) continue;
            var left = e.substring(0, i);
            var right = e.substring(i + 1);
            if (c=='*'){
                if (left.isEmpty() || right.isEmpty()){
                    throw new IllegalArgumentException("missing operand of multiply operator");
                }
                return new Multiply(parse(left), parse(right));
            }
            if (c=='/'){
                if (left.isEmpty() || right.isEmpty()){
                    throw new IllegalArgumentException("missing operand of divide operator");
                }
                return new Divide(parse(left), parse(right));
            }
            if (c=='^'){
                if (left.isEmpty() || right.isEmpty()){
                    throw new IllegalArgumentException("missing operand of power operator");
                }
                return new Power(parse(left), parse(right));
            }
        }
        char first = e.charAt(0);
        var after = e.substring(1);
        if (first=='S'){
            if (after.isEmpty()){
                throw new IllegalArgumentException("missing operand of sum operator");
            }
            return new Sum(parse(after));
        }
        if (first=='R'){
            if (after.isEmpty()){
                throw new IllegalArgumentException("missing operand of root operator");
            }
            return new Root(parse(after));
        }
        char last = e.charAt(e.length() - 1);
        var before = e.substring(0, e.length() - 1);
        if (last=='!'){
            if (before.isEmpty()){
                throw new IllegalArgumentException("missing operand of factorial operator");
            }
            return new Factorial(parse(before));
        }
        if (first=='(' && last==')'){
            return parse(e.substring(1, e.length() - 1));
        }
        try{
            return new Constant(new BigInteger(e));
        }catch (NumberFormatException ex){
            throw new IllegalArgumentException(ex);
        }
    }
}
