package com.sunsoor.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long studentId;
    private Long userId;
    private List<LectureDTO> boughtLectures;
}
