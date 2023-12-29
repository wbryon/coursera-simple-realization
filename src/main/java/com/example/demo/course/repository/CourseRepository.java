package com.example.demo.course.repository;

import com.example.demo.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.title LIKE :prefix%")
    List<Course> findByTitleWithPrefix(@Param("prefix") String prefix);
}