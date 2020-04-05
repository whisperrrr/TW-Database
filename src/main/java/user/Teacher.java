package user;

public class Teacher extends User{
    private String id;
    private String name;
    private String sex;
    private String subject;
    private String account;
    private String password;

    public Teacher() {
    }

    public Teacher(String id, String name, String sex, String subject) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.subject = subject;
    }


    @Override
    public void showFunction() {
        System.out.println("您好，老师，请选择你需要进行的操作：");
        System.out.println("\t1. 查询");
        System.out.println("\t\t1.1 查询学生信息以及成绩");
        System.out.println("\t\t\t1.1.1 查询所有学生信息");
        System.out.println("\t\t\t1.1.2 指定学生姓名的信息以及所有课程的成绩");
    }
}
