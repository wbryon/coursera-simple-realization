package com.example.demo.course.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String author;
    private String title;

    public Course(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Course() {
    }
}
