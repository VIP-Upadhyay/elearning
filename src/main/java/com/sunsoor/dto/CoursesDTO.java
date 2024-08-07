package com.sunsoor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sunsoor.entity.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoursesDTO {
     private Long courseId;
     private Long teacher;
     private String title;
     private String category;
     private String language;
     private Float pricePerLecture;
     private String description;
     private List<TagsDTO> tags;
     private List<LectureDTO> lectures;
     private String uploadDemo;
     private String thumbNail;
     private LocalDateTime uploadDate;

     public CoursesDTO(Long courseId, Teacher teacher, String title, String category, String language, Float pricePerLecture, String description, List<Tags> tags, List<Lecture> lectures, String uploadDemo, String thumbNail, LocalDateTime uploadDate) {
          this.courseId = courseId;
          this.teacher = teacher.getTeacherId();
          this.title = title;
          this.category = category;
          this.language = language;
          this.pricePerLecture = pricePerLecture;
          this.description = description;
          this.tags = tags.stream().map(TagsDTO::new).collect(Collectors.toList());
          this.lectures = lectures.stream().map(LectureDTO::new).collect(Collectors.toList());
          this.uploadDemo = uploadDemo;
          this.thumbNail = thumbNail;
          this.uploadDate = uploadDate;
     }

}
