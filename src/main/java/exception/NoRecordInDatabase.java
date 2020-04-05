package exception;

import java.sql.SQLException;

public class NoRecordInDatabase extends SQLException {
    public NoRecordInDatabase() {
    }

    public NoRecordInDatabase(String message) {
        super(message);
    }
}
