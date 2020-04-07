package user;

import java.sql.SQLException;

public abstract class User {
    public abstract void showFunction();

    public abstract void executeFunction() throws SQLException;

    public void run() throws SQLException {
        while (true) {
            showFunction();
            executeFunction();
        }
    }
}
