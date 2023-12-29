package com.example.demo.course.mapper;

import com.example.demo.course.dto.CourseDto;
import com.example.demo.course.entity.Course;

public class CourseMapper {
    public static CourseDto toCourseRequestToUpdate(Course course) {
        CourseDto requestToUpdate = new CourseDto();
        requestToUpdate.setAuthor(course.getAuthor());
        requestToUpdate.setTitle(course.getTitle());
        return requestToUpdate;
    }
}
