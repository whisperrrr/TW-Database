package com.thoughtworks;

import java.util.Objects;

public class Student {
    private String name;
    private String gender;
    private String classNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(gender, student.gender) &&
                Objects.equals(classNum, student.classNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, classNum);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", classNum='" + classNum + '\'' +
                '}';
    }
}
