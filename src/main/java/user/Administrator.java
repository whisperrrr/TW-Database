package user;

import database.StudentRepository;
import database.SubjectRepository;
import database.TeacherRepository;
import entity.Subject;
import exception.InputNotLegalException;
import util.ParseUtil;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Administrator extends User {
    private String id;
    private String name;
    private String account;
    private String password;
    private StudentRepository studentRepository = new StudentRepository();
    private TeacherRepository teacherRepository = new TeacherRepository();
    private SubjectRepository subjectRepository = new SubjectRepository();

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
        System.out.println("\t\t\t1.3.2 指定信息");
        System.out.println("\t2. 新增");
        System.out.println("\t\t2.1 新增学生信息");
        System.out.println("\t\t2.2 新增课程信息");
        System.out.println("\t3. 修改");
        System.out.println("\t\t3.1 修改指定学生的成绩");
        System.out.println("\t4. 删除");
        System.out.println("\t\t4.1 删除指定学生");
        System.out.println("\t\t4.2 删除指定课程");
        System.out.println("\t\t4.3 删除指定老师");
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
                studentRepository.queryAll().forEach(ele-> System.out.println(ele));
                break;
            case "1.1.2":
                System.out.println("指定学生姓名的信息以及所有课程的成绩...");
                break;
            case "1.1.3":
                System.out.println("指定老师的所有学生及其成绩...");
                break;
            case "1.2.1":
                subjectRepository.queryAll().forEach(ele-> System.out.println(ele));
                break;
            case "1.2.2":
                System.out.println("请输入您想要查询的课程名称");
                String subject = scanner.next();
                subjectRepository.queryByName(subject).forEach(ele-> System.out.println(ele));
                break;
            case "1.2.3":
                System.out.println("请输入您想要查询的老师名字");
                String teacher = scanner.next();
                subjectRepository.querySubjectByTeacher(teacher).forEach(ele-> System.out.println(ele));
                break;
            case "1.3.1":
                teacherRepository.queryAll().forEach(ele-> System.out.println(ele));
                break;
            case "1.3.2":
                System.out.println("请输入您想要查询的老师名字");
                String teacherQuery = scanner.next();
                teacherRepository.queryByName(teacherQuery).forEach(ele-> System.out.println(ele));
                break;
            case "2.1":
                System.out.println("请输入学生信息(例如：学号：1001，姓名： 小明, 年龄： 18, 性别： 男)：");
                String studentStr = scanner.next();
                try {
                    Student student = ParseUtil.parseToStudent(studentStr);
                    studentRepository.save(student);
                    System.out.println(String.format("添加学生[%s %s]成功",student.getName(),student.getId()));
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
                    System.out.println(String.format("添加课程[%s %s]成功",newSubject.getName(),newSubject.getId()));
                } catch (InputNotLegalException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "3.1":
                System.out.println("请输入您想要修改的学生以及成绩(例如：学生:小明，科目:数学，分数:99)...");

            case "4.1":
                System.out.println("请输入您想要删除的学生名字");
                String deleteStudent = scanner.next();
                System.out.println("删除学生之后，该学生信息将不能恢复，是否要继续删除？");
                System.out.println("i.是");
                System.out.println("ii.否");
                String deleteFlag = scanner.next();
                if (Objects.equals(deleteFlag,1)) {
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
        }
    }
}
