package com.example.demo.course.entity;

import com.example.demo.lesson.Lesson;
import com.example.demo.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String author;

    @Column
    private String title;

    @OneToMany(mappedBy = "course", orphanRemoval = true,  cascade = CascadeType.ALL)
    private List<Lesson> lessons;  // атрибут mappedBy делает связь двусторонней, всегда указывается на стороне-владельце

//    @ManyToMany
//    private Set<User> users;

    public Course(String author, String title) {
        this.author = author;
        this.title = title;
    }

}
