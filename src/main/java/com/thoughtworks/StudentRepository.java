package com.thoughtworks;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {


  public void save(List<Student> students) throws SQLException{
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
    ptmt.setString(1,student.getId());
    ptmt.setString(2,student.getName());
    ptmt.setString(3,student.getGender());
    ptmt.setInt(4,student.getAdmissionYear());
    ptmt.setDate(5,new Date(student.getBirthday().getTime())); // 两个Date不一样
    ptmt.setString(6,student.getClassId());

    // 执行
    ptmt.execute();
  }

  public List<Student> query() {
    // TODO:
    return new ArrayList<>();
  }

  public List<Student> queryByClassId(String classId) {
    // TODO:
    return new ArrayList<>();
  }

  public void update(String id, Student student) {
    // TODO:
  }

  public void delete(String id) {
    // TODO:
  }
}
