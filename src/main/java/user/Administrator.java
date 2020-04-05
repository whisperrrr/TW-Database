package user;

public class Administrator extends User {
    private String id;
    private String name;
    private String account;
    private String password;

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
}
