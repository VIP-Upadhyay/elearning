package com.sunsoor.dto;

import com.sunsoor.entity.Lecture;
import com.sunsoor.entity.Tags;
import com.sunsoor.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseWithMDTO {
    private Long courseId;
    private Long teacher;
    private String title;
    private String category;
    private String language;
    private Float pricePerLecture;
    private String description;
    private List<Tags> tags;
    private MultipartFile uploadDemo;
    private MultipartFile thumbNail;
}
