package com.example.demo.course.service;

import com.example.demo.course.dto.CourseRequestToCreate;
import com.example.demo.course.dto.CourseRequestToUpdate;
import com.example.demo.course.model.Course;

import java.util.List;

public interface CourseService {
    Course createCourse(CourseRequestToCreate request);

    void updateCourse(Long id, CourseRequestToUpdate request);

    void deleteCourse(Long id);

    Course getCourse(Long id);

    List<Course> getCoursesByTitlePrefix(String titlePrefix);

    List<Course> courseTable();
}
