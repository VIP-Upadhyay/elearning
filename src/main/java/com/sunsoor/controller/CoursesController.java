package com.sunsoor.controller;

import com.sunsoor.dto.CourseDetailDTO;
import com.sunsoor.dto.CourseInfinitDTO;
import com.sunsoor.dto.CourseWithMDTO;
import com.sunsoor.dto.CoursesDTO;
import com.sunsoor.entity.*;
import com.sunsoor.repository.*;
import com.sunsoor.request.CourseFileRequest;
import com.sunsoor.request.CoursesRequest;
import com.sunsoor.service.TagService;
import com.sunsoor.serviceimpl.CoursesServiceImpl;
import com.sunsoor.serviceimpl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/course")
public class CoursesController {
    @Autowired
    private CoursesServiceImpl coursesService;
    @Autowired
    private TeacherRepository teacherRepo;
    @Autowired
    private TagsRepository tagsRepo;
    @Autowired
    private LectureRepository lectureRepo;

    @PostMapping("/create/{teacherId}")
    public ResponseEntity<Map<String,Object>> createCourses(@ModelAttribute CourseWithMDTO courses, @PathVariable Long teacherId){
        Map<String,Object> returnMap= new HashMap<>();
        try{
            Courses courses1=coursesService.createCourse(courses,teacherId);
            if (courses1!=null){
                returnMap.put("status","success");
                returnMap.put("message","Course Created successfully");
                returnMap.put("courseId",courses1.getCourseId());
            }else {
                returnMap.put("status","failed");
                returnMap.put("message","Teacher not found");
            }
            return ResponseEntity.ok(returnMap);
        }catch (Exception e){
            returnMap.put("status","failed");
            returnMap.put("message",e.toString());
            return ResponseEntity.ok(returnMap);
        }
    }


    @GetMapping("/")
    public ResponseEntity<List<CoursesDTO>> getAllCourses( @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                        @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){
        return new ResponseEntity<>(coursesService.getAllCourses(pageNumber,pageSize),HttpStatus.OK);
    }

    @GetMapping("/getAllCourses")
    public  ResponseEntity<?> getAllC(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                      @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){
        return ResponseEntity.ok(coursesService.getAllC(pageNumber,pageSize));
    }

    @PostMapping("/edite/{courseId}")
    public ResponseEntity<Map<String,Object>> editCourseDetails(@RequestBody Courses courses,@PathVariable Long courseId){
        Map<String,Object> returnMap= new HashMap<>();
        try{
            returnMap.put("status","success");
            returnMap.put("message",coursesService.editeCourseDetails(courseId,courses));
            return ResponseEntity.ok(returnMap);
        }catch (Exception e){
            returnMap.put("status","failed");
            returnMap.put("message",e.toString());
            return ResponseEntity.ok(returnMap);
        }

    }
    @PostMapping("/editCourseDetails/{courseId}")
    public ResponseEntity<Map<String,Object>> editeCourses(@RequestBody Courses courses,@PathVariable Long courseId){
        Map<String,Object> returnMap= new HashMap<>();
        try{
            returnMap.put("status","success");
            returnMap.put("message",coursesService.editeCourseDetails(courseId,courses));
            return ResponseEntity.ok(returnMap);
        }catch (Exception e){
            returnMap.put("status","failed");
            returnMap.put("message",e.toString());
            return ResponseEntity.ok(returnMap);
        }

    }
    @PostMapping("/editCourseFiles/{courseId}")
    public ResponseEntity<Map<String,Object>> editeCourseFiles(@ModelAttribute CourseFileRequest courseFileRequest, @PathVariable Long courseId){
        Map<String,Object> returnMap= new HashMap<>();
        try{
            returnMap.put("status","success");
            returnMap.put("message",coursesService.updateThumbNaiAndDemo(courseId,courseFileRequest));
            return ResponseEntity.ok(returnMap);
        }catch (Exception e){
            returnMap.put("status","failed");
            returnMap.put("message",e.toString());
            return ResponseEntity.ok(returnMap);
        }

    }
    @PostMapping("/uploadThunail-Demo/{courseId}")
    public ResponseEntity<Map<String,Object>> uploadThumbNailDemo(@PathVariable Long courseId,@RequestParam MultipartFile thumbnail,@RequestParam MultipartFile uploadDemo){
        Map<String,Object> returnMap= new HashMap<>();
        try{
            returnMap.put("status","success");
            returnMap.put("message",coursesService.uploadThumbNaiAndDemo(courseId,thumbnail,uploadDemo));
            return ResponseEntity.ok(returnMap);
        }catch (Exception e){
            returnMap.put("status","failed");
            returnMap.put("message",e.toString());
            return ResponseEntity.ok(returnMap);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCourses(@PathVariable Long id){
        return new ResponseEntity<>(coursesService.deleteCourses(id),HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseInfinitDTO>> searchCourses(@RequestParam(value = "searchKey", required = false) String searchKey,
                                                       @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                       @RequestParam(value = "pageSize", defaultValue = "0", required = false) Integer pageSize){
        List<CourseInfinitDTO> courses= coursesService.searchCourses(searchKey,pageNumber,pageSize);
        return new ResponseEntity<>(courses,HttpStatus.OK);
    }
    @GetMapping("get-course-byId/{courseId}")
    public ResponseEntity<CourseDetailDTO> getCourseById(@PathVariable("courseId") Long courseId){
        CourseDetailDTO course=coursesService.getCourseById(courseId);
        return new ResponseEntity<>(course,HttpStatus.OK);
    }

}
