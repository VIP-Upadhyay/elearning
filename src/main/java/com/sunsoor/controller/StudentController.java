package com.sunsoor.controller;

import com.sunsoor.dto.StudentDTO;
import com.sunsoor.entity.Lecture;
import com.sunsoor.entity.Student;
import com.sunsoor.repository.LectureRepository;
import com.sunsoor.repository.StudentRepository;
import com.sunsoor.serviceimpl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl service;
    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private StudentRepository studentRepository;

//    @PostMapping("/create/{studentId}/{lectureId}")
//    public ResponseEntity<String> createStudent(@PathVariable("studentId") Long studentId, @PathVariable("lectureId") Long lectureId) {
//        // Find the student by ID
//
//        Optional<Student> studentOptional = studentRepository.findById(studentId);
//        if (studentOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found with id: " + studentId);
//        }else {
//
//        }
//
//        Student student = studentOptional.get();
//
//        // Find the lecture by ID
//        Optional<Lecture> lectureOptional = lectureRepository.findById(lectureId);
//        if (lectureOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lecture not found with id: " + lectureId);
//        }
//
//        Lecture lecture = lectureOptional.get();
//
//        // Check if the lecture is already present in the student's list of bought lectures
//        if (student.getBuyLecture().contains(lecture)) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lecture with id " + lectureId + " is already bought by the student.");
//        }
//
//        // Add the lecture to the student's list of bought lectures
//        student.getBuyLecture().add(lecture);
//
//        // Update the student
//        studentRepository.save(student);
//
//        return ResponseEntity.ok("Lecture added to student successfully.");
//    }


//    @GetMapping("/{studentId}")
//    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long studentId){
//        return new ResponseEntity<>(service.getStudent(studentId),HttpStatus.OK);
//    }
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("{\"message\": \"hello im umesh\"}", HttpStatus.OK);
    }

    @GetMapping("/getAllCourses/{userId}")
    public  ResponseEntity<?> getAllC(@PathVariable long userId,
                                      @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                      @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){
        return ResponseEntity.ok(service.getAllC(pageNumber,pageSize,userId));
    }
}
