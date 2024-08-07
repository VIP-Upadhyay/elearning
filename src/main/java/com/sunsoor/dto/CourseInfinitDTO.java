package com.sunsoor.dto;

import com.sunsoor.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseInfinitDTO {
    private Long courseId;
    private String teacherName;
    private String title;
    private Float pricePerLecture;
    private String thumbNail;
    private Long studentCount;
}
