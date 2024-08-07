package com.sunsoor.request;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TeacherRequest {
    private Long teacherId;
    private Long userId;
    private String teachingDomain;
    private Integer experience;
    private String description;
    private MultipartFile profilePic;
}
