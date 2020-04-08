package database;

import entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepository {
    public ArrayList<Subject> queryAll() throws SQLException {
        Connection connection = DbUtil.getConnection();
        ResultSet resultSet;
        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery("SELECT * FROM subject");
            return getSubjectList(resultSet);
        }
    }

    // 查询指定课程名称的信息
    public ArrayList<Subject> queryByName(String name) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM subject WHERE subject_name = ?";

        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            return getSubjectList(resultSet);
        }
    }

    // 查询指定老师的所有课程信息
    public List<Subject> querySubjectByTeacher(String name) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT " +
                "teacher.subject_id, " +
                "subject.subject_name " +
                "FROM teacher " +
                "JOIN subject ON teacher.subject_id = subject.subject_id " +
                "WHERE teacher.tname = ?";
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            return getSubjectList(resultSet);
        }
    }
    // 新增课程信息
    public void save(Subject subject) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO subject(subject_id, subject_name)"
                + "VALUES (?, ?);";
        try (PreparedStatement ptmt = connection.prepareStatement(sql)) {
            ptmt.setString(1, subject.getId());
            ptmt.setString(2, subject.getName());

            ptmt.executeUpdate();
        }
    }
    // 删除课程信息
    public void delete(String name) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "DELETE FROM subject WHERE subject_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
    }

    private ArrayList<Subject> getSubjectList(ResultSet resultSet) throws SQLException {
        ArrayList<Subject> subjectList = new ArrayList<>();
        while (resultSet.next()) {
            Subject subject = new Subject(
                    resultSet.getString("subject_id"),
                    resultSet.getString("subject_name")
            );
            subjectList.add(subject);
        }
        return subjectList;
    }
}
