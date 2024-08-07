package com.sunsoor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunsoor.dto.TeacherDTO;
import com.sunsoor.entity.Teacher;
import com.sunsoor.feignclient.UserClient;
import com.sunsoor.repository.CoursesRepository;
import com.sunsoor.request.TeacherRequest;
import com.sunsoor.request.UserRequest;
import com.sunsoor.serviceimpl.TeacherServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherServiceImpl teacherService;
    @Autowired
    private CoursesRepository coursesRepo;
    @Autowired
    private UserClient userUtils;
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/checkTeacher/{userId}")
    public ResponseEntity<Map<String,Object>> checkTeacherIsAvailable(@PathVariable Long userId){
        Map<String,Object> returnMap = new HashMap<>();
       returnMap.put("status","success");
       returnMap.put("IsAvailable",teacherService.teacherExistsByUserId(userId));
       return ResponseEntity.ok(returnMap);
    }


    @PostMapping("/create/{userId}")
    public ResponseEntity<Map<String,Object>> createTeacher(@ModelAttribute TeacherRequest teacher,
                                                            @PathVariable("userId") Long userId, @RequestHeader("Authorization") String token) {
           Map<String,Object> returnMap = new HashMap<>();
        Map<String,Object> request = userUtils.getCurrentUser(userId,token);

        if (request.get("status").equals("success")){

            UserRequest userRequest = objectMapper.convertValue(request.get("User"),UserRequest.class);
            try{
                teacher.setUserId(userRequest.getId());
                returnMap.put("status","success");
                returnMap.put("message",teacherService.createTeacher(teacher,userRequest.getFullName()));
                return ResponseEntity.status(HttpStatus.OK).body(returnMap);
            }catch (Exception e){
                returnMap.put("status","failed");
                returnMap.put("message","User's Teacher already available");
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.OK).body(returnMap);
            }}


            else{
            return ResponseEntity.status(HttpStatus.OK).body(request);
        }
    }


    @GetMapping("/")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers(){
        return new ResponseEntity<>(teacherService.getAllTeacher(),HttpStatus.OK);
    }
    @GetMapping("/getTeacherByTeacherId/{teacherId}")
    public ResponseEntity<TeacherDTO    > getTeacherByTeacherId(@PathVariable("teacherId") Long teacherId){
        return new ResponseEntity<>(teacherService.getTeacherById(teacherId),HttpStatus.OK);
    }

    @PutMapping("edit-teacher-details/{teacherId}")
    public ResponseEntity<Map<String,Object>> editTeacherDetailsByTeacherId(@RequestBody Teacher teacher,@PathVariable("teacherId") Long teacherId){
        Map<String,Object> returnMap=new HashMap<>();
        try{
            returnMap.put("status","success");
            returnMap.put("message",teacherService.editeTeacher(teacherId,teacher));
            return ResponseEntity.ok(returnMap);
        }catch (Exception e){
            returnMap.put("status","failed");
            returnMap.put("message",e.toString());
            return ResponseEntity.ok(returnMap);
        }
    }
    @DeleteMapping("/delete-teacherById/{teacherId}")
    public ResponseEntity<Map<String,Object>> deleteTeacherById(@PathVariable("teacherId") Long teacherId){
        Map<String,Object> returnMap=new HashMap<>();
        try {
            returnMap.put("status","success");
            returnMap.put("message",teacherService.deleteTeacher(teacherId));
            return ResponseEntity.ok(returnMap);
        }catch (Exception e){
            returnMap.put("status","failed");
            returnMap.put("message",e.toString());
            return ResponseEntity.ok(returnMap);
        }
    }

    @GetMapping("/getAllCourses/{teacherId}")
    public  ResponseEntity<?> getAllC(@PathVariable long teacherId,
                                      @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                      @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){
        return ResponseEntity.ok(teacherService.getAllC(pageNumber,pageSize,teacherId));
    }

    @PostMapping("/edit/{userId}")
    public ResponseEntity<?> editTeacher(@ModelAttribute TeacherRequest teacher,@PathVariable long userId, @RequestHeader("Authorization") String token){
        Map<String,Object> returnMap = new HashMap<>();
        Map<String,Object> request = userUtils.getCurrentUser(userId,token);

        if (request.get("status").equals("success")){

            UserRequest userRequest = objectMapper.convertValue(request.get("User"),UserRequest.class);
            try{
                teacher.setUserId(userRequest.getId());
                returnMap.put("status","success");
                returnMap.put("message",teacherService.updateTeacher(teacher,userRequest.getFullName()));
                return ResponseEntity.status(HttpStatus.OK).body(returnMap);
            }catch (Exception e){
                returnMap.put("status","failed");
                returnMap.put("message","User's Teacher already available");
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.OK).body(returnMap);
            }}


        else{
            return ResponseEntity.status(HttpStatus.OK).body(request);
        }
    }
}
