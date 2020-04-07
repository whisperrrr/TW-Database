package util;

import entity.Account;
import entity.Subject;
import exception.InputNotLegalException;
import user.Student;

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
    // 新增学生信息，输入变成Student
    public static Student parseToStudent(String input) throws InputNotLegalException {
        String[] inputInfo = input.split(",");
        Student student = new Student();
        if (inputInfo.length == 4) {
            student.setId(inputInfo[0].split(":")[1]);
            student.setName(inputInfo[1].split(":")[1]);
            student.setAge(Integer.parseInt(inputInfo[2].split(":")[1]));
            student.setSex(inputInfo[3].split(":")[1]);
        } else {
            throw new InputNotLegalException("您的输入不合法，请按规定格式输入");
        }
        return student;
    }
    // 新增课程信息，输入变成Subject
    public static Subject parseToSubject(String input) throws InputNotLegalException {
        String[] inputInfo = input.split(",");
        Subject subject = new Subject();
        if (inputInfo.length == 2) {
            subject.setId(inputInfo[0].split(":")[1]);
            subject.setName(inputInfo[1].split(":")[1]);
        } else {
            throw new InputNotLegalException("您的输入不合法，请按规定格式输入");
        }
        return subject;
    }

}
