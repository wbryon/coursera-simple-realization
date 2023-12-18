package com.example.demo.course.controller;

import com.example.demo.course.dto.CourseRequestToCreate;
import com.example.demo.course.dto.CourseRequestToUpdate;
import com.example.demo.course.exception.ApiError;
import com.example.demo.course.model.Course;
import com.example.demo.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

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
    public List<Course> getCoursesByTitlePrefix(@RequestParam(name = "titlePrefix", required = false) String titlePrefix) {
        return courseService.getCoursesByTitlePrefix(requireNonNullElse(titlePrefix, ""));
    }

    @PutMapping("/{id}")
    public void updateCourse(@PathVariable("id") Long id,
                             @Valid @RequestBody CourseRequestToUpdate request) {
        courseService.updateCourse(id, request);
    }

    @PostMapping
    public Course createCourse(@RequestBody CourseRequestToCreate request) {
        return courseService.createCourse(request);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> noSuchElementExceptionHandler(NoSuchElementException ex) {
        return new ResponseEntity<>(new ApiError(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
