package com.thoughtworks;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentRepository {


    public void save(List<Student> students) throws SQLException {
        for (Student student : students) {
            save(student);
        }
    }

    public void save(Student student) throws SQLException {
        // 获取连接
        Connection connection = StudentUtil.getConnection();
        // sql
        String sql = "INSERT INTO studentjdbc(id, name, sex, school_time, birthday, class)"
                + "VALUES (?, ?, ?, ?, ?, ?);";
        // 预编译
        PreparedStatement ptmt = connection.prepareStatement(sql); //预编译SQL，减少sql执行
        // 传参
        ptmt.setString(1, student.getId());
        ptmt.setString(2, student.getName());
        ptmt.setString(3, student.getGender());
        ptmt.setInt(4, student.getAdmissionYear());
        ptmt.setDate(5, new Date(student.getBirthday().getTime())); // 两个Date不一样
        ptmt.setString(6, student.getClassId());

        // 执行
        ptmt.execute();
    }

    public List<Student> query() throws SQLException {
        Connection connection = StudentUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM studentjdbc");

        List<Student> students = new ArrayList<>();
        Student stu;
        while (resultSet.next()) {
            stu = new Student(resultSet.getString("id")
                    , resultSet.getString("name")
                    , resultSet.getString("sex")
                    , resultSet.getInt("school_time")
                    , resultSet.getDate("birthday").toString()
                    , resultSet.getString("class"));

            students.add(stu);
        }
        return students;
    }

    public List<Student> queryByClassId(String classId) throws SQLException {
        // 建立连接
        Connection connection = StudentUtil.getConnection();
        String sql = "SELECT * FROM studentjdbc WHERE class=?";
        // 预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 传参
        preparedStatement.setString(1, classId);
        // 执行
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Student> students = new ArrayList<>();
        Student stu;
        while (resultSet.next()) {
            stu = new Student(resultSet.getString("id")
                    , resultSet.getString("name")
                    , resultSet.getString("sex")
                    , resultSet.getInt("school_time")
                    , resultSet.getDate("birthday").toString()
                    , resultSet.getString("class"));

            students.add(stu);
        }
        return students;
    }

    public void update(String id, Student student) throws SQLException {
        if (!Objects.equals(id, student.getId())) {
            throw new notSameIdException();
        }
        // 获取连接
        Connection connection = StudentUtil.getConnection();
        // 构建sql
        String sql = "UPDATE studentjdbc"
                + " set name=?, sex=?, school_time=?, birthday=?, class=?"
                + " WHERE id=?";
        // 预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 传参
        preparedStatement.setString(1, student.getName());
        preparedStatement.setString(2, student.getGender());
        preparedStatement.setInt(3, student.getAdmissionYear());
        preparedStatement.setDate(4, new Date(student.getBirthday().getTime())); // 两个Date不一样
        preparedStatement.setString(5, student.getClassId());
        preparedStatement.setString(6, id);

        // 执行
        preparedStatement.execute();
    }

    public void delete(String id) throws SQLException {
        // 获取连接
        Connection connection = StudentUtil.getConnection();
        // 构建sql
        String sql = "DELETE FROM studentjdbc WHERE id=?";
        // 预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 传参
        preparedStatement.setString(1, id);
        // 执行
        preparedStatement.execute();

    }
}
