package com.suna.demo.service.impl;

import java.util.List;
import java.util.Optional;

import com.suna.demo.entity.Student;
import com.suna.demo.repository.StudentRepository;
import com.suna.demo.service.StudentService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
   
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findById(Integer studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    @Transactional
    public Student save(Student student) {
        // TODO Business logic
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public void delete(Student student) {
        studentRepository.delete(student);
    }
    
}
