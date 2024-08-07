package com.sunsoor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sunsoor.entity.Courses;
import com.sunsoor.entity.Lecture;
import com.sunsoor.entity.Student;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LectureDTO {
    private Long lectureId;
    private String courses;
    private String lectureTitle;
    private String uploadLecture;
    private String description;
    private float price;

//    private List<Student> students;

    public LectureDTO(Lecture lecture) {
        this.lectureTitle=lecture.getLectureTitle();
        this.price=lecture.getPrice();
    }



    public LectureDTO(Long lectureId, Courses courses, String lectureTitle, String uploadLecture, String description, float price, List<Student> students) {
        this.lectureId = lectureId;
        this.courses = courses.getTitle();
        this.lectureTitle = lectureTitle;
        this.uploadLecture = uploadLecture;
        this.description = description;
        this.price = price;
//        this.students = students;
    }
    public LectureDTO(Long lectureId, Courses courses, String lectureTitle, String uploadLecture, String description, float price) {
        this.lectureId = lectureId;
        this.courses = courses.getTitle();
        this.lectureTitle = lectureTitle;
        this.uploadLecture = uploadLecture;
        this.description = description;
        this.price = price;
//        this.students = students;
    }

}
