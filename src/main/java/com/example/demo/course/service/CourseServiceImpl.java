package com.example.demo.course.service;

import com.example.demo.course.dto.CourseRequestToCreate;
import com.example.demo.course.dto.CourseRequestToUpdate;
import com.example.demo.course.model.Course;
import com.example.demo.course.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.requireNonNullElse;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(CourseRequestToCreate request) {
        Course course = new Course(request.getAuthor(), request.getTitle());
        return courseRepository.save(course);
    }

    @Override
    public void updateCourse(Long id, CourseRequestToUpdate request) {
        Course course = courseRepository.findById(id).orElseThrow();
        course.setAuthor(request.getAuthor());
        course.setTitle(request.getTitle());
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
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
