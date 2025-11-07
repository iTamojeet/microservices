package com.studentservice.controller;

import com.studentservice.model.Student;
import com.studentservice.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService svc;

    public StudentController(StudentService svc) {
        this.svc = svc;
    }

    @GetMapping
    public List<Student> getAll() {
        return svc.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return
                svc.getById(id);
    }

    @PostMapping
    public ResponseEntity<Student> create(@Valid @RequestBody Student
                                                  student) {
        Student created = svc.createStudent(student);
        return ResponseEntity.created(URI.create("/api/students/" +
                created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @Valid @RequestBody
    Student student) {
        return svc.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}