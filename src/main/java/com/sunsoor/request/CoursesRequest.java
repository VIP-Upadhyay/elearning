package com.sunsoor.request;

import com.sunsoor.entity.Tags;
import com.sunsoor.entity.Teacher;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CoursesRequest {
    private Long courseId;
    private Teacher teacher;
    private String title;
    private String category;
    private String language;
    private Float pricePerLecture;
    private String description;
    private List<Tags> tags;
    private MultipartFile uploadDemo;
    private MultipartFile thumbNail;
    private LocalDateTime uploadDate;
}
