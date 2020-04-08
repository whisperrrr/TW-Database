package entity;

public class Examination {
    private int examinationId;
    private String testPaperId;
    private String studentId;
    private String teacherId;
    private String score;

    public Examination() {
    }

    public void setTestPaperId(String testPaperId) {
        this.testPaperId = testPaperId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTestPaperId() {
        return testPaperId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getScore() {
        return score;
    }
}
