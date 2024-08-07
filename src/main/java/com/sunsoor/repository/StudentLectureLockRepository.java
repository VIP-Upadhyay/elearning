package com.sunsoor.repository;

import com.sunsoor.entity.StudentLectureLock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentLectureLockRepository extends JpaRepository<StudentLectureLock,Long> {

    List<StudentLectureLock> findByStudentStudentIdAndLectureLectureId(Long studentId, Long lectureId);

    Page<StudentLectureLock> findByStudentStudentIdAndLectureLectureId(Long studentId, Long lectureId, Pageable pageable);
}
