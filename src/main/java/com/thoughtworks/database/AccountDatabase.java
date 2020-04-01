package com.thoughtworks.database;

import com.thoughtworks.Account;
import com.thoughtworks.exception.NameNotMatchPassword;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDatabase {
    public void save(Account account) throws SQLException {
        // 获取连接
        Connection connection = DatabaseUtil.getConnection();
        // sql
        String sql = "INSERT INTO userinfo(name, mobile, email, password)"
                + "VALUES (?, ?, ?, ?);";
        // 预编译
        try (PreparedStatement ptmt = connection.prepareStatement(sql)) {
            // 传参
            ptmt.setString(1, account.getName());
            ptmt.setString(2, account.getPhone());
            ptmt.setString(3, account.getEmail());
            ptmt.setString(4, account.getPassword());
            // 执行
            ptmt.executeUpdate();
        }
    }

    public Account query(String name, String password) throws SQLException {
        // 建立连接
        Connection connection = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM userinfo WHERE name=? AND password=?";
        // 预编译
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // 传参
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            // 执行
            resultSet = preparedStatement.executeQuery();
            return parseQueryToAccount(resultSet);
        }
    }

    private Account parseQueryToAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        if (resultSet.next()) {
            account.setName(resultSet.getString("name"));
            account.setPhone(resultSet.getString("mobile"));
            account.setEmail(resultSet.getString("email"));
            account.setPassword(resultSet.getString("password"));
        } else {
            throw new NameNotMatchPassword();
        }
        return account;
    }
}

