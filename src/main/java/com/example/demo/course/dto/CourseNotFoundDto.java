package com.example.demo.course.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
public class CourseNotFoundDto {
    private OffsetDateTime dateOccurred;
    private final String message;
    public CourseNotFoundDto(OffsetDateTime dateOccurred, String message) {
        this.dateOccurred = dateOccurred;
        this.message = message;
    }
}
