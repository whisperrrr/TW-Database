import exception.InputNotLegalException;
import user.User;

import java.sql.SQLException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        StudentTestSystem studentTestSystem = new StudentTestSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.next();
            try {
                User user = studentTestSystem.login(input);
                user.showFunction();
            } catch (InputNotLegalException e) {
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                System.out.println("系统中没有该用户，请检查您的用户名或密码");
            }
        }

    }

}
