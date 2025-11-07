package com.courseservice.service;

import com.courseservice.exception.ResourceNotFoundException;
import com.courseservice.model.Course;
import com.courseservice.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    public Course createCourse(Course course) {
        return repo.save(course);
    }

    public Course updateCourse(Long id, Course updated) {
        return repo.findById(id).map(c -> {
            c.setName(updated.getName());
            c.setDescription(updated.getDescription());
            c.setCredits(updated.getCredits());
            return repo.save(c);
        }).orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
    }

    public Course getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
    }

    public List<Course> getAll() {
        return repo.findAll();
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}