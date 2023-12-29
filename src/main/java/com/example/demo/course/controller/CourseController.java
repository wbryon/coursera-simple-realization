package com.example.demo.course.controller;

import com.example.demo.course.dto.CourseDto;
import com.example.demo.course.dto.CourseNotFoundDto;
import com.example.demo.course.exception.NotFoundException;
import com.example.demo.course.entity.Course;
import com.example.demo.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;

import static java.util.Objects.requireNonNullElse;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("")
    public List<Course> courseTable() {
        return courseService.courseTable();
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable("id") Long id) {
        return courseService.getCourse(id);
    }

    @GetMapping("/filter")
    public List<Course> getCoursesByTitlePrefix(@RequestParam(name = "prefix", required = false) String prefix) {
        return courseService.getCoursesByTitlePrefix(requireNonNullElse(prefix, ""));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseNotFoundDto> updateCourse(@PathVariable("id") Long id,
                             @Valid @RequestBody CourseDto request) {
        try {
            courseService.updateCourse(id, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            OffsetDateTime dateOccurred = OffsetDateTime.now();
            String errorMessage = e.getMessage();
            CourseNotFoundDto errorResponse = new CourseNotFoundDto(dateOccurred, errorMessage);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Course createCourse(@Valid @RequestBody CourseDto request) {
        return courseService.createCourse(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseNotFoundDto> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            OffsetDateTime dateOccurred = OffsetDateTime.now();
            String errorMessage = e.getMessage();
            CourseNotFoundDto errorResponse = new CourseNotFoundDto(dateOccurred, errorMessage);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
