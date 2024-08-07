package com.sunsoor.service;

import com.sunsoor.dto.TeacherDTO;
import com.sunsoor.entity.Teacher;
import com.sunsoor.request.TeacherRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TeacherService {
    String createTeacher(TeacherRequest teacher, MultipartFile profile);

    String createTeacher(TeacherRequest teacher,String teacherName);

    List<TeacherDTO> getAllTeacher();
    String editeTeacher(Long Id,Teacher teacher);
    String deleteTeacher(Long Id);
    TeacherDTO getTeacherById(Long tearcherId);

    Boolean teacherExistsByUserId(Long userId);

}
