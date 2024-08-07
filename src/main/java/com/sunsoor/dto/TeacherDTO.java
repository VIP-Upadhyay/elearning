package com.sunsoor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sunsoor.entity.Courses;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {

    private Long teacherId;
    private Long userId;
    private String teachingDomain;
    private Integer experience;
    private String description;
    private String profilePic;
    private String teacherName;



}
