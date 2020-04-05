package util;

import entity.Account;
import exception.InputNotLegalException;

public class ParseUtil {
    // 登录的时候，输入变成Account
    public static Account parseToAccount(String input) throws InputNotLegalException {
        String[] inputInfo = input.split(":");
        Account account = new Account();
        if (inputInfo.length == 2) {
            account.setAccount(inputInfo[0]);
            account.setPassword(inputInfo[1]);
        } else {
            throw new InputNotLegalException("您的输入不合法，请按规定格式输入");
        }
        return account;
    }
    // 新增课程信息，输入变成Subject TODO
    // 新增老师信息，输入变成Teacher TODO
}
