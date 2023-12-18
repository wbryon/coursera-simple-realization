package com.example.demo.course.mapper;

import com.example.demo.course.dto.CourseRequestToUpdate;
import com.example.demo.course.model.Course;

public class CourseMapper {
    public static CourseRequestToUpdate toCourseRequestToUpdate(Course course) {
        CourseRequestToUpdate requestToUpdate = new CourseRequestToUpdate();
        requestToUpdate.setAuthor(course.getAuthor());
        requestToUpdate.setTitle(course.getTitle());
        return requestToUpdate;
    }
}
