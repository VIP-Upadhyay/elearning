package com.sunsoor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentLectureLock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sl_sequence")
    @SequenceGenerator(name = "sl_sequence", sequenceName = "sl_sequence", allocationSize = 1)

    private Long id;

    private boolean locked;

    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "studentId")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lecture_id",referencedColumnName = "lectureId")
    private Lecture lecture;
}
