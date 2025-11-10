package com.Student.controller;

import com.Student.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @GetMapping
    public List<Student> getAllStudents() {
        return List.of(
                new Student(1,"Madhu","Java"),
                new Student(2,"Trisha","Python"),
                new Student(3,"Koyel","SQL")
        );
    }
}