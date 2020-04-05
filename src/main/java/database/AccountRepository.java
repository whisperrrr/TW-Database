package database;

import entity.Account;
import exception.NoRecordInDatabase;
import user.Administrator;
import user.Student;
import user.Teacher;
import user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepository {
    public static User identifyUser(Account account) throws SQLException, NoRecordInDatabase {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT identity FROM account WHERE account = ? AND password = ?";

        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, account.getAccount());
            preparedStatement.setString(2, account.getPassword());

            resultSet = preparedStatement.executeQuery();
            return parseToUser(resultSet);
        }
    }

    private static User parseToUser(ResultSet resultSet) throws SQLException, NoRecordInDatabase {
        User user = null;
        if (resultSet.first()) {
            String identity = resultSet.getString("identity");
            switch (identity) {
                case "administrator":
                    user = new Administrator();
                    break;
                case "teacher":
                    user = new Teacher();
                    break;
                case "student":
                    user = new Student();
            }
        } else {
            throw new NoRecordInDatabase();
        }
        return user;
    }
}
