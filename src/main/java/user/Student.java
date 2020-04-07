package user;

public class Student extends User {
    private String id;
    private String name;
    private int age;
    private String sex;
    private String sclass;
    private String account;
    private String password;

    public Student() {
    }

    public Student(String id, String name, int age, String sex, String sclass) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.sclass = sclass;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getSclass() {
        return sclass;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public void showFunction() {
        System.out.println("系统优化中...");
    }

    @Override
    public void executeFunction() {
        System.out.println("系统优化中...");
    }

    @Override
    public String toString() {
        return String.format("学号：%s,姓名：%s,年龄：%d,性别：%s", id, name, age, sex);
    }
}
