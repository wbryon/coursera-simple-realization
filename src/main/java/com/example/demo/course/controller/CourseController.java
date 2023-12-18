package com.example.demo.course.controller;

import com.example.demo.course.dto.CourseRequestToCreate;
import com.example.demo.course.dto.CourseRequestToUpdate;
import com.example.demo.course.exception.ApiError;
import com.example.demo.course.model.Course;
import com.example.demo.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNullElse;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("")
    public List<Course> courseTable() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable("id") Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    @GetMapping("/filter")
    public List<Course> getCoursesByTitlePrefix(@RequestParam(name = "titlePrefix", required = false) String titlePrefix) {
        return courseRepository.findByTitleWithPrefix(requireNonNullElse(titlePrefix, ""));
    }

    @PutMapping("/{id}")
    public void updateCourse(@PathVariable("id") Long id,
                             @Valid @RequestBody CourseRequestToUpdate request) {
        Course course = courseRepository.findById(id).orElseThrow();
        course.setAuthor(request.getAuthor());
        course.setTitle(request.getTitle());
        courseRepository.save(course);
    }

    @PostMapping
    public Course createCourse(@RequestBody CourseRequestToCreate request) {
        Course course = new Course(request.getAuthor(), request.getTitle());
        return courseRepository.save(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable("id") Long id) {
        courseRepository.deleteById(id);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> noSuchElementExceptionHandler(NoSuchElementException ex) {
        return new ResponseEntity<>(
                new ApiError(ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
