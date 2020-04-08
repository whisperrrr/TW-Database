package database;

import entity.Examination;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExaminationRepository {
    // 根据学生查分
    public List<String> QueryByStudent(String studentName) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT " +
                "s.sname, " +
                "e.testpaper_id, " +
                "e.escore " +
                "FROM examination e " +
                "JOIN student s ON e.student_id = s.student_id " +
                "WHERE s.sname = ?" +
                "AND e.escore IS NOT NULL";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, studentName);

            ResultSet resultSet = preparedStatement.executeQuery();
            return getScoreList(resultSet);
        }
    }

    // 根据老师查下面的学生和成绩
    public List<String> QueryByTeacher(String teacherName) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT " +
                "s.sname, " +
                "e.testpaper_id, " +
                "e.escore " +
                "FROM examination e " +
                "JOIN student s ON e.student_id = s.student_id " +
                "JOIN teacher t ON e.teacher_id = t.teacher_id " +
                "WHERE t.tname = ? " +
                "AND e.escore IS NOT NULL;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, teacherName);

            ResultSet resultSet = preparedStatement.executeQuery();
            return getScoreList(resultSet);
        }
    }

    // 修改学生的成绩
    public void UpdateByStudent(Examination examination) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "UPDATE examination " +
                "SET escore = ? " +
                "WHERE student_id = ? " +
                "AND testpaper_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, examination.getScore());
            preparedStatement.setString(2, examination.getStudentId());
            preparedStatement.setString(3, examination.getTestPaperId());

            preparedStatement.executeUpdate();
        }
    }

    private List<String> getScoreList(ResultSet resultSet) throws SQLException {
        ArrayList<String> scoreList = new ArrayList<>();
        while (resultSet.next()) {
            String sname = resultSet.getString("sname");
            String testpaper_id = resultSet.getString("testpaper_id");
            String escore = resultSet.getString("escore");

            scoreList.add(String.format("姓名:%s,试卷号:%s,成绩:%s", sname, testpaper_id, escore));
        }
        return scoreList;
    }


}
