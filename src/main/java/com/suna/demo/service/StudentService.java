package com.suna.demo.service;

import java.util.List;
import java.util.Optional;

import com.suna.demo.entity.Student;

public interface StudentService {
    
    List<Student> findAll();

    Optional<Student> findById(Integer studentId);

    Student save(Student student);

    void delete(Student student);
}
