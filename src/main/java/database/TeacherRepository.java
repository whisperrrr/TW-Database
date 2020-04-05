package database;

import user.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TeacherRepository {
    // 查询所有
    public ArrayList<Teacher> queryAll() throws SQLException {
        Connection connection = DbUtil.getConnection();
        ResultSet resultSet;
        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery("SELECT * FROM teacher");
            return getTeacherList(resultSet);
        }
    }

    // 通过名字查询
    public ArrayList<Teacher> queryByName(String name) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM teacher WHERE t_name = ?";

        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            return getTeacherList(resultSet);
        }
    }

    // 删除老师信息
    public void delete(String name) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "DELETE FROM studentjdbc WHERE tname = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
    }

    private ArrayList<Teacher> getTeacherList(ResultSet resultSet) throws SQLException {
        ArrayList<Teacher> teacherList = new ArrayList<>();
        while (resultSet.next()) {
            Teacher teacher = new Teacher(
                    resultSet.getString("teacher_id"),
                    resultSet.getString("tname"),
                    resultSet.getString("tsex"),
                    resultSet.getString("subject_id")
            );
            teacherList.add(teacher);
        }
        return teacherList;
    }
}
