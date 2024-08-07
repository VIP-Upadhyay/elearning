package com.sunsoor.repository;

import com.sunsoor.dto.CourseDetailDTO;
import com.sunsoor.dto.CourseInfinitDTO;
import com.sunsoor.entity.Courses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Courses,Long> {
    Page<Courses> findAllByOrderByUploadDateDesc(Pageable pageable);
//    @Query("SELECT DISTINCT c FROM Courses c " +
//            "LEFT JOIN c.tags t " +
//            "WHERE (:title IS NULL OR c.title LIKE CONCAT('%', :title, '%')) " +
//            "AND (:category IS NULL OR c.category = :category) " +
//            "AND (:language IS NULL OR c.language = :language) " +
//            "AND (:teacherId IS NULL OR c.teacher.teacherId = :teacherId) " +
//            "AND (:tag IS NULL OR :tag IN (SELECT t.name FROM c.tags t))")
//    Page<Courses> findByTitleAndCategoryAndLanguageAndTeacherAndTag(
//            @Param("title") String title,
//            @Param("category") Category category,
//            @Param("language") String language,
//            @Param("teacherId") Long teacherId,
//            @Param("tag") String tag,
//            Pageable pageable);
//    Page<Courses> findByTitleIgnoreCaseContainingOrCategoryNameIgnoreCaseContainingOrLanguageIgnoreCaseContainingOrTagsNameIgnoreCaseContaining(
//            String title, String category, String language, String tags,  Pageable pageable);
@Query("SELECT NEW com.sunsoor.dto.CourseInfinitDTO(" +
        "c.courseId, c.teacher.teacherName, c.title, c.pricePerLecture, c.thumbNail, (SELECT COUNT(s) FROM c.students s)) " +
        "FROM Courses c " +
        "LEFT JOIN c.tags tag " +
        "WHERE LOWER(c.title) LIKE LOWER(CONCAT('%', :title, '%')) " +
        "OR LOWER(c.category) LIKE LOWER(CONCAT('%', :category, '%')) " +
        "OR LOWER(c.language) LIKE LOWER(CONCAT('%', :language, '%')) " +
        "OR LOWER(tag.name) LIKE LOWER(CONCAT('%', :tags, '%'))")
Page<CourseInfinitDTO> findByTitleIgnoreCaseContainingOrCategoryNameIgnoreCaseContainingOrLanguageIgnoreCaseContainingOrTagsNameIgnoreCaseContaining(
        @Param("title") String title,
        @Param("category") String category,
        @Param("language") String language,
        @Param("tags") String tags,
        Pageable pageable);


    @Query("SELECT NEW com.sunsoor.dto.CourseDetailDTO(" +
            "c.courseId, c.teacher, c.title, c.category, c.language, c.pricePerLecture, c.description, c.uploadDemo, c.thumbNail, c.uploadDate, (SELECT COUNT(s) FROM c.students s)) " +
            "FROM Courses c " +
            "WHERE c.courseId = :courseId")
    CourseDetailDTO findCourseDetailsDTOById(@Param("courseId") Long courseId);

    @Query("SELECT NEW com.sunsoor.dto.CourseInfinitDTO(" +
            "c.courseId, c.teacher.teacherName, c.title, c.pricePerLecture, c.thumbNail, (SELECT COUNT(s) FROM c.students s)) " +
            "FROM Courses c " +
            "ORDER BY c.uploadDate DESC")
    Page<CourseInfinitDTO> findCourseInfinitDTOById(Pageable pageable);

    @Query("SELECT NEW com.sunsoor.dto.CourseInfinitDTO(" +
            "c.courseId, c.teacher.teacherName, c.title, c.pricePerLecture, c.thumbNail, (SELECT COUNT(s) FROM c.students s)) " +
            "FROM Courses c WHERE c.teacher.teacherId = :teacherId " +
            "ORDER BY c.uploadDate DESC")
    Page<CourseInfinitDTO> findCourseInfinitDTOByTeacherId(@Param("teacherId") Long teacherId,Pageable pageable);


}
