package user;

import database.ExaminationRepository;
import database.StudentRepository;
import database.SubjectRepository;
import database.TeacherRepository;
import entity.Examination;
import entity.Subject;
import exception.InputNotLegalException;
import util.ParseUtil;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Administrator extends User {
    private StudentRepository studentRepository = new StudentRepository();
    private TeacherRepository teacherRepository = new TeacherRepository();
    private SubjectRepository subjectRepository = new SubjectRepository();
    private ExaminationRepository examinationRepository = new ExaminationRepository();

    @Override
    public void showFunction() {
        System.out.println("您好，超级管理员，请选择你需要进行的操作：");
        System.out.println("\t1. 查询");
        System.out.println("\t\t1.1 查询学生信息以及成绩");
        System.out.println("\t\t\t1.1.1 查询所有学生信息");
        System.out.println("\t\t\t1.1.2 指定学生姓名的信息以及所有课程的成绩");
        System.out.println("\t\t\t1.1.3 指定老师的所有学生及其成绩");
        System.out.println("\t\t1.2 查询课程信息");
        System.out.println("\t\t\t1.2.1 所有课程信息");
        System.out.println("\t\t\t1.2.2 指定课程名称的信息");
        System.out.println("\t\t\t1.2.3 指定老师的所有课程信息");
        System.out.println("\t\t1.3 查询老师信息");
        System.out.println("\t\t\t1.3.1 所有老师信息");
        System.out.println("\t\t\t1.3.2 指定老师信息");
        System.out.println("\t2. 新增");
        System.out.println("\t\t2.1 新增学生信息");
        System.out.println("\t\t2.2 新增课程信息");
        System.out.println("\t3. 修改");
        System.out.println("\t\t3.1 修改指定学生的成绩");
        System.out.println("\t4. 删除");
        System.out.println("\t\t4.1 删除指定学生");
        System.out.println("\t\t4.2 删除指定课程");
        System.out.println("\t\t4.3 删除指定老师");
        System.out.println("如您想退出本系统，按下数字0即可");
    }

    @Override
    // 这里的函数写的太不优雅了 TODO
    public void executeFunction() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.next();

        switch (userChoice) {
            case "0":
                System.out.println("退出");
                System.exit(0);
            case "1.1.1":
                studentRepository.queryAll().forEach(System.out::println);
                break;
            case "1.1.2":
                System.out.println("请输入您想要查询分数的学生姓名");
                String studentQueryScore = scanner.next();
                examinationRepository.QueryByStudent(studentQueryScore).forEach(System.out::println);
                break;
            case "1.1.3":
                System.out.println("请输入您想要查询相应学生分数的老师姓名");
                String teacherQueryScore = scanner.next();
                examinationRepository.QueryByTeacher(teacherQueryScore).forEach(System.out::println);
                break;
            case "1.2.1":
                subjectRepository.queryAll().forEach(System.out::println);
                break;
            case "1.2.2":
                System.out.println("请输入您想要查询的课程名称");
                String subject = scanner.next();
                subjectRepository.queryByName(subject).forEach(System.out::println);
                break;
            case "1.2.3":
                System.out.println("请输入您想要查询的老师名字");
                String teacher = scanner.next();
                subjectRepository.querySubjectByTeacher(teacher).forEach(System.out::println);
                break;
            case "1.3.1":
                teacherRepository.queryAll().forEach(System.out::println);
                break;
            case "1.3.2":
                System.out.println("请输入您想要查询的老师名字");
                String teacherQuery = scanner.next();
                teacherRepository.queryByName(teacherQuery).forEach(System.out::println);
                break;
            case "2.1":
                System.out.println("请输入学生信息(例如：学号：1001，姓名： 小明, 年龄： 18, 性别： 男)：");
                String studentStr = scanner.next();
                try {
                    Student student = ParseUtil.parseToStudent(studentStr);
                    studentRepository.save(student);
                    System.out.println(String.format("添加学生[%s %s]成功", student.getName(), student.getId()));
                } catch (InputNotLegalException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "2.2":
                System.out.println("请输入课程信息(例如：课程号：001，课程名称：数学)");
                String subjectStr = scanner.next();
                try {
                    Subject newSubject = ParseUtil.parseToSubject(subjectStr);
                    subjectRepository.save(newSubject);
                    System.out.println(String.format("添加课程[%s %s]成功", newSubject.getName(), newSubject.getId()));
                } catch (InputNotLegalException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "3.1":
                // 觉得以前的输入范式不太严谨，因为科目为数学的卷子可能有很多张，所以应该是改对应试卷的分数
                // 而且名字有可能有重复的，学号不可能重复，所以这里用学生号来代替学生名字
                System.out.println("请输入您想要修改的学生以及成绩(例如：学生号:1001，试卷号:0011，分数:99)");
                String updateInput = scanner.next();
                try {
                    Examination examination = ParseUtil.parseToExamination(updateInput);
                    examinationRepository.UpdateByStudent(examination);
                    System.out.println(String.format("修改学生号%s试卷号为%s的试卷成绩为%s",
                            examination.getStudentId(),
                            examination.getTestPaperId(),
                            examination.getScore()));

                } catch (InputNotLegalException e) {
                    e.printStackTrace();
                }
                break;
            case "4.1":
                System.out.println("请输入您想要删除的学生名字");
                String deleteStudent = scanner.next();
                System.out.println("删除学生之后，该学生信息将不能恢复，是否要继续删除？");
                System.out.println("i.是");
                System.out.println("ii.否");
                String deleteFlag = scanner.next();
                if (Objects.equals(deleteFlag, "1")) {
                    studentRepository.delete(deleteStudent);
                    System.out.println("删除学生成功");
                }
                break;
            case "4.2":
                System.out.println("请输入您想要删除的课程名字");
                String deleteSubject = scanner.next();
                subjectRepository.delete(deleteSubject);
                break;
            case "4.3":
                System.out.println("请输入您想要删除的老师名字");
                String deleteTeacher = scanner.next();
                teacherRepository.delete(deleteTeacher);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + userChoice);
        }
    }
}
