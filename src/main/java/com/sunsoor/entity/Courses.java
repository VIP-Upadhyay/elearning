    package com.sunsoor.entity;

    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import jakarta.persistence.*;
    import lombok.*;

    import java.time.LocalDateTime;
    import java.util.Date;
    import java.util.List;

    @Entity
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Courses {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courses_sequence")
        @SequenceGenerator(name = "courses_sequence", sequenceName = "courses_sequence", allocationSize = 1)
        private Long courseId;
        @ManyToOne
        @JoinColumn(name = "teacher_id")
        private Teacher teacher;
        private String title;
        private String category;
        private String language;
        private Float pricePerLecture;
        @Column(columnDefinition="LONGTEXT",length = 2500)
        private String description;
        @JsonIgnoreProperties("courses")
        @ManyToMany
        @JoinTable(
                name = "course_tags",
                joinColumns = @JoinColumn(name = "course_id"),
                inverseJoinColumns = @JoinColumn(name = "tag_id")
        )
        private List<Tags> tags;
        @JsonIgnoreProperties("courses")
        @OneToMany(mappedBy = "courses")
        private List<Lecture> lectures;
        private String uploadDemo;
        private String thumbNail;
        private LocalDateTime uploadDate;
        @ManyToMany
        @JoinTable(
                name = "course_student",
                joinColumns = @JoinColumn(name = "course_id"),
                inverseJoinColumns = @JoinColumn(name = "student_id")
        )
        private List<Student> students;

    }
