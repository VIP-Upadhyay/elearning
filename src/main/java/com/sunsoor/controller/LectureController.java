package com.sunsoor.controller;

import com.sunsoor.dto.LectureDTO;
import com.sunsoor.entity.Courses;
import com.sunsoor.entity.Lecture;
import com.sunsoor.repository.CoursesRepository;
import com.sunsoor.request.LectureRequest;
import com.sunsoor.service.LectureService;
import com.sunsoor.serviceimpl.LectureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lecture")
public class LectureController {
    @Autowired
    private LectureServiceImpl service;
    @Autowired
    private CoursesRepository coursesRepo;

    @Transactional
    @PostMapping("/create/{courseId}")
    public ResponseEntity<Map<String,Object>> createLecture(@ModelAttribute LectureRequest lecture, @RequestParam MultipartFile video, @PathVariable Long courseId){
        Map<String,Object> returnMap=new HashMap<>();
        try{
            Lecture lecture1 = service.createLecture(lecture,video,courseId);
            if (lecture1!=null){
                returnMap.put("status","success");
                returnMap.put("message",lecture1);
                return ResponseEntity.ok(returnMap);
            }else {
                returnMap.put("status","failed");
                returnMap.put("message","course not found");
                return ResponseEntity.ok(returnMap);
            }
        }catch (Exception e){
            returnMap.put("status","failed");
            returnMap.put("message","failed create lecture"+e.toString());
            return ResponseEntity.ok(returnMap);
        }
    }
    @GetMapping("/getLectureById/{lectureId}")
    public ResponseEntity<?> getLectureById(@PathVariable("lectureId") Long lectureId){
        Map<String,Object> returnMap=new HashMap<>();
        Lecture lecture = service.getLectureById(lectureId);
        if (lecture!=null){
            returnMap.put("status","success");
            returnMap.put("lecture",lecture);
        }else {
            returnMap.put("status","failed");
            returnMap.put("message","lecture not found");
        }
        return ResponseEntity.ok(returnMap);
    }
    @GetMapping("/{courseId}/{pageNumber}/{pageSize}")
    public ResponseEntity<List<Lecture>> getAllLecture(@PathVariable("courseId") Long courserId,@PathVariable("pageNumber") int pageNumber,@PathVariable("pageSize") int pageSize){
        return new ResponseEntity<>(service.getAllLecture(courserId,pageNumber,pageSize),HttpStatus.OK);
    }
    @PutMapping("/edite/{id}")
    public ResponseEntity<String> editeLecture(@RequestBody Lecture lecture,@PathVariable Long id){
        return new ResponseEntity<>(service.editeLecture(id,lecture),HttpStatus.OK);
    }
    @PostMapping("/edit/{id}")
    public ResponseEntity<?> editLecture(@ModelAttribute LectureRequest lectureRequest, @PathVariable Long id){
        Map<String,Object> returnMap=new HashMap<>();
        Lecture lecture = service.editLecture(lectureRequest,id);
        if (lecture!=null){
            returnMap.put("status","success");
            returnMap.put("lecture",lecture);
        }else {
            returnMap.put("status","failed");
            returnMap.put("message","lecture not found");
        }
        return ResponseEntity.ok(returnMap);
    }
}
