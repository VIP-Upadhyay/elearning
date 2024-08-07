package com.sunsoor.repository;

import com.sunsoor.dto.CourseInfinitDTO;
import com.sunsoor.dto.StudentDTO;
import com.sunsoor.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

//
//    @Query("SELECT NEW com.sunsoor.dto.StudentDTO(s.studentId, s.userId, " +
//            "   NEW com.sunsoor.dto.LectureDTO(l.lectureTitle, l.price)) " +
//            "FROM Student s " +
//            "JOIN s.buyLecture l " +
//            "WHERE s.studentId = :studentId")
//    List<StudentDTO> findStudentDetails(@Param("studentId") Long studentId);

    @Query("SELECT s From Student s WHERE s.userId = :studentId")
    Student findByUserId(@Param("studentId")long studentId);

    @Query("SELECT NEW com.sunsoor.dto.CourseInfinitDTO(" +
            "c.courseId, t.teacherName, c.title, c.pricePerLecture, c.thumbNail, " +
            "(SELECT COUNT(s) FROM c.students s)) " +
            "FROM Courses c " +
            "JOIN c.teacher t " +
            "LEFT JOIN c.students s " +
            "WHERE s.userId = :userId " +
            "GROUP BY c.courseId, t.teacherName, c.title, c.pricePerLecture, c.thumbNail")
    Page<CourseInfinitDTO> findEnrolledCoursesForStudent(@Param("userId") Long userId, Pageable pageable);

}