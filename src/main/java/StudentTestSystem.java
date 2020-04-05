import database.AccountRepository;
import entity.Account;
import exception.InputNotLegalException;
import exception.NoRecordInDatabase;
import user.User;
import util.ParseUtil;

import java.sql.SQLException;

public class StudentTestSystem {
    // 登陆，链接数据库，返回一个用户
    // 没有该用户，抛出NoRecordInDatabase异常
    public User login(String input) throws InputNotLegalException, SQLException {
        Account account = ParseUtil.parseToAccount(input);
        User user = AccountRepository.identifyUser(account);
        return user;
    }

}
