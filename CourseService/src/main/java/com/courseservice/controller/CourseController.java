package com.courseservice.controller;

import com.courseservice.model.Course;
import com.courseservice.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService svc;

    public CourseController(CourseService svc) {
        this.svc = svc;
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
        Course created = svc.createCourse(course);
        return ResponseEntity.created(URI.create("/api/courses/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Long id) {
        return svc.getById(id);
    }

    @GetMapping
    public List<Course> getAll() {
        return svc.getAll();
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @Valid
    @RequestBody Course course) {
        return svc.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}