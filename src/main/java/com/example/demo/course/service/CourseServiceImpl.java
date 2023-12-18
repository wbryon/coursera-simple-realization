package com.example.demo.course.service;

import com.example.demo.course.dto.CourseDto;
import com.example.demo.course.exception.NotFoundException;
import com.example.demo.course.model.Course;
import com.example.demo.course.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

import static java.util.Objects.requireNonNullElse;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(CourseDto request) {
        Course course = new Course(request.getAuthor(), request.getTitle());
        return courseRepository.save(course);
    }

    @Override
    public void updateCourse(Long id, CourseDto request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(OffsetDateTime.now(), "Course with id=" + id + " not found"));
        course.setAuthor(request.getAuthor());
        course.setTitle(request.getTitle());
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(OffsetDateTime.now(), "Course with id=" + id + " not found"));
        courseRepository.deleteById(id);
    }

    @Override
    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Course> getCoursesByTitlePrefix(String titlePrefix) {
        return courseRepository.findByTitleWithPrefix(requireNonNullElse(titlePrefix, ""));
    }

    @Override
    public List<Course> courseTable() {
        return courseRepository.findAll();
    }
}
