package com.sunsoor.controller;

import com.sunsoor.dto.CourseTeacherIdDTO;
import com.sunsoor.dto.LectureStatusDto;
import com.sunsoor.dto.UnlockLecturesDto;
import com.sunsoor.entity.Courses;
import com.sunsoor.entity.Lecture;
import com.sunsoor.feignclient.SubscriptionClient;
import com.sunsoor.request.LectureUnlockRequest;
import com.sunsoor.serviceimpl.CourseService;
import com.sunsoor.serviceimpl.LectureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student/")
public class LockedUnLockedController {

    @Autowired
    CourseService courseService;

    @Autowired
    LectureServiceImpl lectureService;
    @Autowired
    SubscriptionClient subscriptionClient;

    @Transactional
    @PostMapping("/lectures/unlock/{studentId}")
    public ResponseEntity<?> unlockLectureForStudent(@PathVariable Long studentId, @RequestBody LectureUnlockRequest lectureUnlockRequest, @RequestHeader("Authorization") String token) {
        Map<String,Object> returnMap = new HashMap<>();
        CourseTeacherIdDTO courseTeacherIdDTO = lectureService.getTeacherIdWithCourse(lectureUnlockRequest.getLectureId().get(0));
        Map<String,Object> request = subscriptionClient.createSubscription(courseTeacherIdDTO.getTeacherId(), lectureUnlockRequest.getSubscriptionRequest(),token);
        if (request.get("status").equals("success")){
            courseService.unlockLectureForStudent(studentId, lectureUnlockRequest.getLectureId(),courseTeacherIdDTO.getCourses());
            returnMap.put("status","success");
            returnMap.put("message","lectures unlocked success");
            return ResponseEntity.ok(returnMap);
        }else {
            return ResponseEntity.ok(request);
        }
    }

    @GetMapping("/{userId}/{courseId}/lectures")
    public ResponseEntity<?> getLecturesForStudentWithPagination(@PathVariable Long userId,@PathVariable Long courseId, Pageable pageable) {
        Map<String,Object> returnMap = new HashMap<>();
        List<LectureStatusDto> lectureDTOs = courseService.getLecturesForStudentWithPagination(userId,courseId, pageable);
        return ResponseEntity.ok(lectureDTOs);
    }

}
