package database;

import entity.Subject;
import user.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentRepository {
    // 查询所有
    public ArrayList<Student> queryAll() throws SQLException {
        Connection connection = DbUtil.getConnection();
        ResultSet resultSet;
        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery("SELECT * FROM student");
            return getStudentList(resultSet);
        }
    }

    // 新增学生信息
    public void save(Student student) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO student(student_id, sname, sage, ssex)"
                + "VALUES (?, ?, ?, ?);";
        try (PreparedStatement ptmt = connection.prepareStatement(sql)) {
            ptmt.setString(1, student.getId());
            ptmt.setString(2, student.getName());
            ptmt.setInt(3, student.getAge());
            ptmt.setString(4, student.getSex());

            ptmt.executeUpdate();
        }
    }

    // 删除学生信息
    public void delete(String name) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "DELETE FROM student WHERE sname=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
    }

    private ArrayList<Student> getStudentList(ResultSet resultSet) throws SQLException {
        ArrayList<Student> studentList = new ArrayList<>();
        while (resultSet.next()) {
            Student stu = new Student(
                    resultSet.getString("student_id"),
                    resultSet.getString("sname"),
                    resultSet.getInt("sage"),
                    resultSet.getString("ssex"),
                    resultSet.getString("sclass")
            );
            studentList.add(stu);
        }
        return studentList;
    }


}
