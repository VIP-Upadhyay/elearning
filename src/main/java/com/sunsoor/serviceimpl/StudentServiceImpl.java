package com.sunsoor.serviceimpl;

import com.sunsoor.dto.CourseInfinitDTO;
import com.sunsoor.dto.LectureDTO;
import com.sunsoor.dto.StudentDTO;
import com.sunsoor.entity.Lecture;
import com.sunsoor.entity.Student;
import com.sunsoor.repository.StudentRepository;
import com.sunsoor.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repo;

    public String create (Student student){
        System.out.println(" here iam" +student.toString());
        this.repo.save(student);
        return "successful";
    }
//    public StudentDTO getStudent(Long id) {
//        Student student = repo.findById(id).orElse(null);
//        if (student == null) {
//            System.out.println("null values");
//            return null; // Or throw an exception, depending on your requirements
//        }
//
//        StudentDTO studentDTO = new StudentDTO();
//        studentDTO.setStudentId(student.getStudentId());
//        studentDTO.setUserId(student.getUserId());
//
//        // Assuming you have a method to map Lecture entity to LectureDTO
//        List<LectureDTO> lectureDTOs = student.getBuyLecture().stream()
//                .map(this::mapToLectureDTO)
//                .collect(Collectors.toList());
//        studentDTO.setBoughtLectures(lectureDTOs);
//
//        return studentDTO;
//    }

    // Method to map Lecture entity to LectureDTO
    private LectureDTO mapToLectureDTO(Lecture lecture) {
        LectureDTO lectureDTO = new LectureDTO();
        // Map properties from Lecture to LectureDTO
        lectureDTO.setLectureId(lecture.getLectureId());
        // Map other properties as needed
        return lectureDTO;
    }

    public List<CourseInfinitDTO> getAllC(int pageNumber, int pageSize, long Id){
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "uploadDate"));
        return repo.findEnrolledCoursesForStudent(Id,pageable).toList();
    }

}
