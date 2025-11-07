package com.studentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {
    private Long id;
    private String name;
    private String description;
    private int credits;
}

// Course DTO (used by WebClient)