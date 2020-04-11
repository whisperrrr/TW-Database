package com.thoughtworks;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student/{name}")
    public Student getStudent(@PathVariable String name) {
        return studentRepository.findById(name).orElse(null);
    }

    @GetMapping("/students")
    public Iterable<Student> getStudents() {
        return studentRepository.findAll();
    }

    @DeleteMapping("/student/delete/{name}")
    public String deleteStudent(@PathVariable String name) {
        if (studentRepository.existsById(name)) {
            studentRepository.deleteById(name);
            return "删除成功";
        } else {

            return "该学生不存在";
        }
    }

    @PostMapping("/student/save")
    public String saveStudent(@RequestBody Student student) {
        if (studentRepository.existsById(student.getName())) {
            return "姓名重复";
        } else {
            studentRepository.addStudent(student.getName(), student.getGender(), student.getClassNum());
            return "添加成功";
        }
    }
}
