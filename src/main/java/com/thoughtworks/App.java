package com.thoughtworks;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            getHomePage();
            String userChoice = scanner.next();
            switch (userChoice) {
                case "1":
                    System.out.println("请输入注册信息(格式：用户名,手机号,邮箱,密码)：");
                    accountManager.register();
                    break;
                case "2":
                    System.out.println("请输入用户名和密码(格式：用户名,密码)：");
                    accountManager.login();
                    break;
                case "3":
                    System.out.println("常来哦，客官");
                    System.exit(0);
            }
        }
    }

    public static void getHomePage() {
        System.out.println("1.注册");
        System.out.println("2.登录");
        System.out.println("3.退出");
        System.out.println("请输入你的选择(1~3):");
    }
}
