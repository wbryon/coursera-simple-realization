package com.example.demo.course.service;

import com.example.demo.course.dto.CourseDto;
import com.example.demo.course.entity.Course;

import java.util.List;

public interface CourseService {
    Course createCourse(CourseDto request);

    void updateCourse(Long id, CourseDto request);

    void deleteCourse(Long id);

    Course getCourse(Long id);

    List<Course> getCoursesByTitlePrefix(String titlePrefix);

    List<Course> courseTable();
}
