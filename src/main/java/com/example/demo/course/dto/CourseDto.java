package com.example.demo.course.dto;

import com.example.demo.course.annotation.TitleCase;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CourseDto {
    @NotBlank(message = "Course author has to be filled")
    private String author;
    @NotBlank(message = "Course title has to be filled")
    @TitleCase(TitleCase.Language.RU)
    private String title;
}
