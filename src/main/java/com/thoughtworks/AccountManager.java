package com.thoughtworks;

import com.thoughtworks.database.AccountDatabase;
import com.thoughtworks.exception.InputNotLegalException;
import com.thoughtworks.exception.WrongFormatException;

import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {
    private final static int MAX_CHANCE = 3;
    private int chance = 0;
    private AccountDatabase accountDatabase = new AccountDatabase();

    public void register() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        Account account = new Account();

        try {
            account.parseToAccount(userInput, "register");
            account.checkRegisterInput();
            accountDatabase.save(account);
            System.out.println(String.format("%s,恭喜你注册成功！", account.getName()));
        } catch (WrongFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("请按正确格式输入注册信息：");
            register();
        } catch (InputNotLegalException e) {
            System.out.println(e.getMessage());
            System.out.println("请输入合法的注册信息:");
            register();
        } catch (SQLException e) {
            System.out.println("该用户名已经注册过啦，请直接登录");
        }

    }

    public void login() {
        if (chance == MAX_CHANCE) {
            System.out.println(String.format("您已%d次输错密码，账号被锁定", MAX_CHANCE));
            new Thread(() -> {
                try {
                    Thread.sleep(20000);
                    chance = 0;
                    System.out.println("账号锁定已解除，您可以再次登陆");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            //chance = 0;
            return;
        }
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        Account account = new Account();
        try {
            account.parseToAccount(userInput, "login");
            Account accountQuery = accountDatabase.query(account.getName(), account.getPassword());
            System.out.println(String.format("%s,欢迎回来！", accountQuery.getName()));
            System.out.println(String.format("您的手机号是%s，邮箱是%s", accountQuery.getPhone(), accountQuery.getEmail()));
        } catch (WrongFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("请按正确格式输入注册信息：");
            login();
        } catch (SQLException e) {
            System.out.println("密码或用户名错误");
            System.out.println("请重新输入用户名和密码:");
            chance++;
            login();
        }
    }
}
