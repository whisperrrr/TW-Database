package com.thoughtworks;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 表现层
@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student/{name}")
    public Student getStudent(@PathVariable String name) {
        return studentService.getStudent(name);
    }

    @GetMapping("/students")
    public Iterable<Student> getStudents() {
        return studentService.getStudents();
    }

    @DeleteMapping("/student/delete/{name}")
    public String deleteStudent(@PathVariable String name) {
        return studentService.deleteStudent(name);
    }

    @PostMapping("/student/save")
    public String saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }
}
