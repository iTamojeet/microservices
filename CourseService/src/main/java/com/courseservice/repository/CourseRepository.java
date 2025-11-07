package com.courseservice.repository;

import com.courseservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long>
{
    Optional<Course> findByNameIgnoreCase(String name); // custom method example
}