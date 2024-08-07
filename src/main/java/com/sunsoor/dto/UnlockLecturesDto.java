package com.sunsoor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// UnlockLecturesDto.java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnlockLecturesDto {
    private Long courseId;
    private List<Long> lectureIds;
}
