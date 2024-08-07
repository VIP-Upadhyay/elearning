package com.sunsoor.repository;

import com.sunsoor.dto.CourseTeacherIdDTO;
import com.sunsoor.dto.LectureDTO;
import com.sunsoor.entity.Courses;
import com.sunsoor.entity.Lecture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture,Long> {
    //Page<Lecture> findAll(Pageable pageable);
    @Query("SELECT NEW com.sunsoor.dto.LectureDTO(l.lectureTitle, l.price) FROM Lecture l")
    List<LectureDTO> findAllLectureDTOs();

    @Query("SELECT l FROM Lecture l WHERE l.courses.courseId = ?1")
    Page<Lecture> findLectureByCourseId(Long userId, Pageable pageable);

    List<Lecture> findByCoursesCourseId(Long courseId);
    List<Lecture> findAllByLectureIdInAndCoursesCourseId(List<Long> lectureIds, Long courseId);

    Lecture findByLectureId(long id);

    @Query("SELECT l.courses.teacher.teacherId FROM Lecture l WHERE l.lectureId = ?1")
    Long getTeacherIdByLectureId(Long lectureId);

    @Query("SELECT NEW com.sunsoor.dto.CourseTeacherIdDTO(l.courses.teacher.userId, l.courses) FROM Lecture l WHERE l.lectureId = ?1")
    CourseTeacherIdDTO getCoursesByLectureId(Long lectureId);

}
