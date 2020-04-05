package exception;

public class NoRecordInDatabase extends Exception {
    public NoRecordInDatabase() {
    }

    public NoRecordInDatabase(String message) {
        super(message);
    }
}
