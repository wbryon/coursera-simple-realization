package com.example.demo.course.repository;

import com.example.demo.course.entity.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.title LIKE :prefix%")
    List<Course> findByTitleWithPrefix(@Param("prefix") String prefix);

    /**
     * Ещё один корректный способ инициализации данных с ленивым типом -
     * использовать аннотацию @EntityGraph над методом в репозитории.
     * В аргументе attributePaths аннотации @EntityGraph указывается тот объект,
     * который необходимо инициализировать сразу, несмотря на установленный у него ленивый тип загрузки.
     */

    @EntityGraph(attributePaths = {"lessons"})
    @Query("SELECT c FROM Course c")
    List<Course> findAllUsingEntityGraph();

    /**
     * Чтобы в репозитории можно обращаться к EntityGraph'у по имени,
     * для этого их нужно описать в классе-сущности
     */
    @EntityGraph(value = "joinLessons")
    @Query("SELECT c FROM Course c")
    List<Course> findAllWithLessons();

    @EntityGraph(value = "noJoins")
    @Query("SELECT c FROM Course c")
    List<Course> findAll();
}