package com.sunsoor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lecture_sequence")
    @SequenceGenerator(name = "lecture_sequence", sequenceName = "lecture_sequence", allocationSize = 1)
    private Long lectureId;
    //@JsonIgnoreProperties("lecture")
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses courses;

    private String lectureTitle;
    private String uploadLecture;
    @Column(columnDefinition="LONGTEXT",length = 2500)
    private String description;
    private float price;

    @Column(nullable = false)
    private LocalDateTime lectureDate;

    @JsonIgnore
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentLectureLock> studentLectureLock;
}

