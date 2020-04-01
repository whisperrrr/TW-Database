package com.thoughtworks;

import com.thoughtworks.exception.InputNotLegalException;
import com.thoughtworks.exception.WrongFormatException;

import java.util.Scanner;

public class AccountManager {
    private final static int MAX_CHANCE = 3;
    private int chance = 0;

    public void register() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        Account account = new Account();

        try {
            account.parseToAccount(userInput,"register");
            account.checkRegisterInput();
        } catch (WrongFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("请按正确格式输入注册信息：");
            register();
        } catch (InputNotLegalException e) {
            System.out.println(e.getMessage());
            System.out.println("请输入合法的注册信息:");
            register();
        }

        // TODO 链接数据库，进行注册
        System.out.println(String.format("%s,恭喜你注册成功！", account.getName()));
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        Account account = new Account();
        try {
            account.parseToAccount(userInput,"login");
        } catch (WrongFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("请按正确格式输入注册信息：");
            login();
        }
        // TODO 链接数据库，进行登录
        // 登录成功，获得数据
        // Account userAccount = getDataFromDatabase(account);
        // sout(String.format("%s,欢迎回来！", userAcount.getName()))
        // sout(String.format("您的手机号是%s，邮箱是%s"),userAcount.getPhone(),userAcount.getEmail())
        System.out.println(account.getName());
        System.out.println("注册成功啦");
    }
}
