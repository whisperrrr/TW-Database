package com.thoughtworks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// 业务逻辑层
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student getStudent(String name) {
        return studentRepository.findById(name).orElse(null);
    }

    public Iterable<Student> getStudents() {
        return studentRepository.findAll();
    }

    public String deleteStudent(String name) {
        if (studentRepository.existsById(name)) {
            studentRepository.deleteById(name);
            return "删除成功";
        } else {

            return "该学生不存在";
        }
    }

    public String saveStudent(Student student) {
        if (studentRepository.existsById(student.getName())) {
            return "姓名重复";
        } else {
            studentRepository.addStudent(student.getName(), student.getGender(), student.getClassNum());
            return "添加成功";
        }
    }
}
