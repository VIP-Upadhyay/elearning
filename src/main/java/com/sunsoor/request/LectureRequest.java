package com.sunsoor.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LectureRequest {

    private Long lectureId;
    private String courses;
    private String lectureTitle;
    private MultipartFile uploadLecture;
    private String description;
}
