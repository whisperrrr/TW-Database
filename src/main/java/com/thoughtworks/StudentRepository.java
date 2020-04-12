package com.thoughtworks;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

// 数据访问层
public interface StudentRepository extends CrudRepository<Student, String> {
    @Modifying
    @Query("INSERT student_new(name,gender,class_num) VALUES(:name,:gender,:class_num)")
    void addStudent(@Param("name") String name,@Param("gender") String gender,@Param("class_num") String classNum);
}
