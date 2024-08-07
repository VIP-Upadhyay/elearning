package com.sunsoor.repository;

import com.sunsoor.dto.CourseInfinitDTO;
import com.sunsoor.dto.TeacherCourseDTO;
import com.sunsoor.entity.Courses;
import com.sunsoor.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    Teacher findByUserId(Long userId);

    Boolean existsByUserId(Long userId);

    @Query("SELECT new com.sunsoor.dto.TeacherCourseDTO(t.teacherId, t.userId, t.teachingDomain, t.experience, t.description, t.profilePic, t.teacherName, SIZE(t.courses)) FROM Teacher t WHERE t.userId = :teacherId")
    TeacherCourseDTO findTeacherWithCourseCountById(@Param("teacherId") Long teacherId);

//    @Query("SELECT NEW com.sunsoor.dto.CourseInfinitDTO(" +
//            "c.courseId, c.teacher.teacherName, c.title, c.pricePerLecture, c.thumbNail, (SELECT COUNT(s) FROM c.students s)) " +
//            "FROM Teacher t JOIN t.courses c WHERE t.userId = :userId"+
//            "ORDER BY c.uploadDate DESC")
//    Page<CourseInfinitDTO> findPaginatedCoursesByUserId(@Param("userId") Long userId, Pageable pageable);
}
