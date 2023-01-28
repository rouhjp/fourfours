package jp.rouh.fourfours.component;

public class EvaluationException extends RuntimeException {

    EvaluationException(String message){
        super(message);
    }

    EvaluationException(Throwable cause){
        super(cause);
    }

}
