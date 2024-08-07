package com.sunsoor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherCourseDTO {
    private Long teacherId;
    private Long userId;
    private String teachingDomain;
    private Integer experience;
    private String description;
    private String profilePic;
    private String teacherName;
    private long noOfCourses;
}
