package com.thoughtworks;

import com.thoughtworks.exception.InputNotLegalException;
import com.thoughtworks.exception.WrongFormatException;

import java.util.Objects;

public class Account {
    private String name;
    private String phone;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public void parseToAccount(String userInput, String operation) throws WrongFormatException {
        String[] accountInfo = userInput.split(",");
        int infoNumber = accountInfo.length;

        switch (operation) {
            case "register":
                if (!Objects.equals(infoNumber, 4)) {
                    throw new WrongFormatException("格式错误");
                } else {
                    this.name = accountInfo[0];
                    this.phone = accountInfo[1];
                    this.email = accountInfo[2];
                    this.password = accountInfo[3];
                }
                break;
            case "login":
                if (!Objects.equals(infoNumber, 2)) {
                    throw new WrongFormatException("格式错误");
                } else {
                    this.name = accountInfo[0];
                    this.password = accountInfo[1];
                }
        }
    }

    public void checkRegisterInput() throws InputNotLegalException {
        checkName();
        checkPhone();
        checkEmail();
        checkPassword();
    }


    // TODO 可以用正则试试
    private void checkName() throws InputNotLegalException {
        if (name.length() < 2 || name.length() > 10) {
            throw new InputNotLegalException("用户名不合法");
        }
    }

    private void checkPhone() throws InputNotLegalException {
        if (phone.charAt(0) != '1' || phone.length() != 11) {
            throw new InputNotLegalException("手机号不合法");
        }
    }

    private void checkEmail() throws InputNotLegalException {
        if (!email.contains("@")) {
            throw new InputNotLegalException("邮箱不合法");
        }
    }

    private void checkPassword() throws InputNotLegalException {
        if (password.length() < 8 || password.length() > 16) {
            throw new InputNotLegalException("密码不合法");
        }
    }
}
