package com.sunsoor.dto;

import com.sunsoor.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailDTO {
    private Long courseId;
    private Teacher teacher;
    private String title;
    private String category;
    private String language;
    private Float pricePerLecture;
    private String description;
    private String uploadDemo;
    private String thumbNail;
    private LocalDateTime uploadDate;
    private Long studentCount;
}
