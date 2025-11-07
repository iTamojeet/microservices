package com.studentservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "firstName required")
    private String firstName;
    @NotBlank(message = "lastName required")
    private String lastName;
    @Email(message = "invalid email")
    @Column(nullable = false, unique = true)
    private String email;
    @PositiveOrZero
    private Integer age;
    // store courseId locally; details come from course service
    private Long courseId;
}