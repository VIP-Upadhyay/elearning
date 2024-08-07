package com.sunsoor.service;

import com.sunsoor.dto.CourseDetailDTO;
import com.sunsoor.dto.CourseInfinitDTO;
import com.sunsoor.dto.CourseWithMDTO;
import com.sunsoor.dto.CoursesDTO;
import com.sunsoor.entity.Courses;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CoursesService {

    Courses createCourse(CourseWithMDTO courses, Long teacherId);

    String uploadThumbNaiAndDemo(Long courseId, MultipartFile thumbNail, MultipartFile demo);
    public CourseDetailDTO getCourseById(Long courseId);
    List<CoursesDTO> getAllCourses(Integer pangeNumber,Integer pageSize);
    String editeCourseDetails(Long id,Courses courses);
    String deleteCourses(Long id);
    List<CourseInfinitDTO> searchCourses(String searchKey, Integer pageNumber, Integer pageSize);

}
