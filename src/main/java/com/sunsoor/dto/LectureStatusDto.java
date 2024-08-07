package com.sunsoor.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LectureStatusDto {
    private Long lectureId;
    private String lectureTitle;
    private boolean locked;
}
