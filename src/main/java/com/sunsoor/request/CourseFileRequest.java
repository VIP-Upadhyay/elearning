package com.sunsoor.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CourseFileRequest {

    private MultipartFile uploadDemo;
    private MultipartFile thumbNail;
}