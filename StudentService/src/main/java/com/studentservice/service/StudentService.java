package com.studentservice.service;

import com.studentservice.exception.BadRequestException;
import com.studentservice.exception.ResourceNotFoundException;
import com.studentservice.dto.CourseDto;
import com.studentservice.model.Student;
import com.studentservice.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repo;
    private final WebClient webClient;

    public StudentService(StudentRepository repo, WebClient
            courseWebClient) {
        this.repo = repo;
        this.webClient = courseWebClient;
    }

    public List<Student> getAll() {
        return repo.findAll();
    }

    public Student getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new
                ResourceNotFoundException("Student not found with id " + id));
    }

    @Transactional
    public Student createStudent(Student student) {
    // Validate course exists by calling Course Service
        Long courseId = student.getCourseId();
        if (courseId != null) {
            CourseDto courseDto = verifyCourseExists(courseId);
    // Optionally use courseDto to set a cached courseName or to validate credits etc.
        }
        if (repo.findByEmail(student.getEmail()).isPresent()) {
            throw new BadRequestException("Email already in use");
        }
        return repo.save(student);
    }

    @Transactional
    public Student updateStudent(Long id, Student updated) {
        Student s = repo.findById(id).orElseThrow(() -> new
                ResourceNotFoundException("Student not found with id " + id));
        if (updated.getCourseId() != null) {
            verifyCourseExists(updated.getCourseId());
            s.setCourseId(updated.getCourseId());
        }
        s.setFirstName(updated.getFirstName());
        s.setLastName(updated.getLastName());
        s.setEmail(updated.getEmail());
        s.setAge(updated.getAge());
        return repo.save(s);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    private CourseDto verifyCourseExists(Long courseId) {
// WebClient call to course service - blocking for simplicity (you can convert to reactive chain if desired)
        Mono<CourseDto> mono = webClient.get()
                .uri("/{id}", courseId)
                .retrieve()
                .onStatus(status -> status.value() == 404, response ->
                        Mono.error(new ResourceNotFoundException("Course not found with id "
                                + courseId)))
                .bodyToMono(CourseDto.class);
        return mono.block(); // blocking here to keep service layer simple; in a reactive design avoid blocking
    }
}