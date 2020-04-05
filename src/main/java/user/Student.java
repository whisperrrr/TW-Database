package user;

public class Student {
    private String id;
    private String name;
    private String sex;
    private String sclass;
    private String account;
    private String password;

    public Student(String id, String name, String sex, String sclass) {
        this.id = id;
        this.name = name;
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

    public String getSclass() {
        return sclass;
    }
}
