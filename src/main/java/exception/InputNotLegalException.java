package exception;

public class InputNotLegalException extends Exception {
    public InputNotLegalException() {
    }

    public InputNotLegalException(String message) {
        super(message);
    }
}
