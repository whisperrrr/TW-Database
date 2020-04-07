import exception.InputNotLegalException;
import user.User;

import java.sql.SQLException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        StudentTestSystem studentTestSystem = new StudentTestSystem();
        welcome();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.next();
            try {
                User user = studentTestSystem.login(input);
                user.run();
                break;
            } catch (InputNotLegalException e) {
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                System.out.println("系统中没有该用户，请检查您的用户名或密码");
            }
        }
    }

    private static void welcome() {
        System.out.println("您好，欢迎登陆学生考试系统，请输入您的用户名和密码(用户名:密码)：  ");
        System.out.println("例如:张三:123");
    }

}
