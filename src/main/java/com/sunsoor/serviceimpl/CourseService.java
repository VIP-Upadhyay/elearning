package com.sunsoor.serviceimpl;


import com.sunsoor.dto.LectureStatusDto;
import com.sunsoor.entity.Courses;
import com.sunsoor.entity.Lecture;
import com.sunsoor.entity.Student;
import com.sunsoor.entity.StudentLectureLock;
import com.sunsoor.repository.CoursesRepository;
import com.sunsoor.repository.LectureRepository;
import com.sunsoor.repository.StudentLectureLockRepository;
import com.sunsoor.repository.StudentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private final StudentRepository studentRepository;
    private final CoursesRepository courseRepository;
    private final LectureRepository lectureRepository;
    private final StudentLectureLockRepository studentLectureLockRepository;

    public CourseService(StudentRepository studentRepository, CoursesRepository courseRepository,
                             LectureRepository lectureRepository, StudentLectureLockRepository studentLectureLockRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.lectureRepository = lectureRepository;
        this.studentLectureLockRepository = studentLectureLockRepository;
    }


    public List<Courses> findEnrolledCoursesWithLecturesForStudent(Long studentId) {
        Student student = studentRepository.findByUserId(studentId);
        return new ArrayList<>(student.getEnrolledCourses());
    }

    public void unlockLectureForStudent(Long studentId, List<Long> lectureId,Courses courses) {
        Student student = studentRepository.findByUserId(studentId);
        List<Courses> coursesList = new ArrayList<>();
        if (student==null){
            student = new Student();
            student.setUserId(studentId);
            coursesList.add(courses);
            student.setEnrolledCourses(coursesList);
            student = studentRepository.save(student);
        }
        List<Student> studentList = courses.getStudents();
        if (!studentList.contains(student)){
           studentList.add(student);
           courses.setStudents(studentList);
           courseRepository.save(courses);
        }


        List<Lecture> lectures = lectureRepository.findAllById(lectureId);


        Student finalStudent = student;
        lectures.forEach(lecture -> {
            StudentLectureLock studentLectureLock = studentLectureLockRepository.findByStudentStudentIdAndLectureLectureId(finalStudent.getStudentId(), lecture.getLectureId())
                    .stream()
                    .findFirst()
                    .orElseGet(() -> {
                        StudentLectureLock newLock = new StudentLectureLock();
                        newLock.setStudent(finalStudent);
                        newLock.setLecture(lecture);
                        newLock.setLocked(true);
                        return newLock;
                    });

            studentLectureLock.setLocked(false);
            studentLectureLockRepository.save(studentLectureLock);
        });
    }

    public List<LectureStatusDto> getLecturesForStudentWithPagination(Long studentId,Long courseId, Pageable pageable) {
        Student student = studentRepository.findByUserId(studentId);
        if (student==null){
            student = new Student();
            student.setUserId(studentId);
            student = studentRepository.save(student);
        }
        List<LectureStatusDto> lectureDTOs = new ArrayList<>();

        List<Lecture> lectures = lectureRepository.findLectureByCourseId(courseId,pageable).stream().toList();


        for (Lecture lecture : lectures) {
            Student finalStudent = student;
            StudentLectureLock studentLectureLock = studentLectureLockRepository.findByStudentStudentIdAndLectureLectureId(finalStudent.getStudentId(), lecture.getLectureId())
                    .stream()
                    .findFirst()
                    .orElseGet(() -> {
                        StudentLectureLock newLock = new StudentLectureLock();
                        newLock.setStudent(finalStudent);
                        newLock.setLecture(lecture);
                        newLock.setLocked(true);
                        return newLock;
                    });

            boolean locked = studentLectureLock.isLocked();
            lectureDTOs.add(new LectureStatusDto(lecture.getLectureId(), lecture.getLectureTitle(),locked));
        }



        return lectureDTOs;
    }
}
