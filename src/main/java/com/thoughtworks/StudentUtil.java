package com.thoughtworks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentUtil {
    public static final String URL = "jdbc:mysql://localhost:3306/student_manage_sys?useUnicode=true&characterEncoding=utf-8&serverTimezone=Hongkong";
    public static final String USER = "root";  // MySQL里面Ctrl+U进行查看
    public static final String PASSWORD = "931205";
    private static Connection conn = null;

    static {
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 【deprecated】
            // Class.forName("com.mysql.jdbc.Driver");
            //2. 获得数据库连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //搞不懂什么用处 TODO
    public static Connection getConnection() {
        return conn;
    }

}
