package com.sunsoor.service;

import com.sunsoor.dto.LectureDTO;
import com.sunsoor.entity.Lecture;
import com.sunsoor.request.LectureRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LectureService {
    Lecture createLecture(LectureRequest lecture, MultipartFile video, Long courseId);
    public LectureDTO getLectureById(Long lectureId);

    public List<Lecture> getAllLecture(Long courseId,Integer pageNumber,Integer pageSize);
    String editeLecture(Long id, Lecture lecture);



}