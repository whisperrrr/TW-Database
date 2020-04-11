package com.thoughtworks;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class StudentManageSys {
    private List<Student> studentList = new ArrayList<>();

    @PostMapping("/add")
    public String add(@RequestBody Student student) {
        if (studentList.contains(student)) {
            return "姓名重复";
        } else {
            studentList.add(student);
            return "添加成功";
        }
    }

    @RequestMapping("/queryAll")
    public List<Student> queryAll() {
        return studentList;
    }

    @RequestMapping("/get/{studentName}")
    public Student queryByName(@PathVariable String studentName) {
        return getStudent(studentName);
    }

    @DeleteMapping("/delete/{studentName}")
    public String deleteByName(@PathVariable String studentName) {
        Student studentBeDeleted = getStudent(studentName);
        boolean removeFlag = studentList.remove(studentBeDeleted);
        return removeFlag ? "删除成功" : "该学生不存在";
    }

    private Student getStudent(String studentName) {
        return studentList.stream()
                .filter(ele -> Objects.equals(ele.getName(), studentName))
                .findAny()
                .orElse(null);
    }
}
