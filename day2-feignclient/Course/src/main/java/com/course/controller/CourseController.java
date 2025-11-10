package com.course.controller;

import com.course.service.StudentClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final StudentClient studentClient;

    public CourseController(StudentClient studentClient) {
        this.studentClient = studentClient;
    }

    @GetMapping("/students")
    public List<Map<String, Object>> getStudentsFromStudentService() {
        return studentClient.getAllStudents();
    }

    @GetMapping
    public String getCourseInfo() {
        return "Course Service is working!";
    }
}