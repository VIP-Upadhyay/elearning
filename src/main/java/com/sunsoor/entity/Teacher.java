package com.sunsoor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_sequence")
    @SequenceGenerator(name = "teacher_sequence", sequenceName = "teacher_sequence", allocationSize = 1)
    private Long teacherId;
    @Column(unique = true)
    private Long userId;
    private String teachingDomain;
    private String teacherName;
    private Integer experience;
    @Column(columnDefinition="LONGTEXT",length = 2500)
    private String description;
    private String profilePic;
    //add profile
    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Courses> courses;
}
