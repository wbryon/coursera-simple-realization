package com.example.demo.course.entity;

import com.example.demo.lesson.Lesson;
import com.example.demo.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "courses")
@NamedEntityGraph(name = "joinLessons", attributeNodes = {@NamedAttributeNode("lessons")})
@NamedEntityGraph(name = "noJoins")
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

    @ManyToMany
    @JoinTable(name = "courses_users",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    public Course(String author, String title) {
        this.author = author;
        this.title = title;
    }

}
