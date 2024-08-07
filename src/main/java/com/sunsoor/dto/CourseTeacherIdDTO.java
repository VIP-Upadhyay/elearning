package com.sunsoor.dto;

import com.sunsoor.entity.Courses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseTeacherIdDTO {
    long teacherId;
    Courses courses;
}
