package com.example.demo.course.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CourseRequestToCreate {
    @NotBlank(message = "Course author has to be filled")
    private String author;
    @NotBlank(message = "Course title has to be filled")
    private String title;
}
