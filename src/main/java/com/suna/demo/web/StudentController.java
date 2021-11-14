package com.suna.demo.web;

import java.time.LocalDateTime;
import java.util.Optional;

import com.suna.demo.entity.Student;
import com.suna.demo.service.StudentService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentController {
    
    @Value("${suna.developer}")
    private String developer;

    private StudentService studentService;
   
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/students")
    public String students(Model model) {

        var students = studentService.findAll();

        model.addAttribute("students", students);

        return "students";
    }

    @GetMapping("/students/new")
    public String newstudent(Model model) {

        model.addAttribute("student", new Student());

        return "student-form";
    }

    @PostMapping("/students/save")
    public String newStudent(Student student) {

        if(student.getCreationDate() == null)
            student.setCreationDate(LocalDateTime.now());
        
        studentService.save(student);

        return "redirect:/students";
    }

    @GetMapping("/students/edit/{studentId}")
    public String editStudent(@PathVariable("studentId") Integer studentId, Model model) {
        
        Optional<Student> student = studentService.findById(studentId);
        if (student.isPresent()) {
            model.addAttribute("student", student);
        }
        return "student-form";
    }

    @GetMapping("/students/delete/{studentId}")
    public String deleteStudent(Student student) {
        studentService.delete(student);
        return "redirect:/students";
    }
}
