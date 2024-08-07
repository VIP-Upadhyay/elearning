package com.sunsoor.serviceimpl;

import com.sunsoor.dto.CourseTeacherIdDTO;
import com.sunsoor.dto.LectureDTO;
import com.sunsoor.entity.Courses;
import com.sunsoor.entity.Lecture;
import com.sunsoor.files.FileStorageServices;
import com.sunsoor.repository.CoursesRepository;
import com.sunsoor.repository.LectureRepository;
import com.sunsoor.request.LectureRequest;
import com.sunsoor.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LectureServiceImpl {
    @Autowired
    private LectureRepository lectureRepo;
    @Autowired
    private CoursesRepository coursesRepo;
    @Autowired
    private FileStorageServices fileStorageServices;


    public Lecture createLecture(LectureRequest lecture, MultipartFile video, Long courseId) {
        Lecture lecture1=new Lecture();
        Optional<Courses> courses=coursesRepo.findById(courseId);
        if (courses.isPresent()){
            lecture1.setCourses(courses.get());
            lecture1.setLectureTitle(lecture.getLectureTitle());
            lecture1.setDescription(lecture.getDescription());
            lecture1.setPrice(courses.get().getPricePerLecture());
            String vLecture =fileStorageServices.storeFile(video, video.getOriginalFilename());
            lecture1.setUploadLecture(vLecture);
            lecture1.setLectureDate(LocalDateTime.now());
            return lectureRepo.save(lecture1);
        }
        return null;
    }

    public Lecture getLectureById(Long lectureId) {

        return lectureRepo.findByLectureId(lectureId);

    }

    public List<Lecture> getAllLecture(Long courseId,Integer pageNumber,Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "lectureDate");
        Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
        return lectureRepo.findLectureByCourseId(courseId,pageable).stream().toList();
    }

    public String editeLecture(Long id, Lecture lecture) {
        Lecture lecture1=lectureRepo.findById(id).get();
        lecture1.setUploadLecture(lecture.getUploadLecture());
        lecture1.setCourses(lecture.getCourses());
        lecture1.setDescription(lecture.getDescription());
        lectureRepo.save(lecture1);
        return "Edited Successfully";
    }

    public long getTeacherId(long lectureId){
        return lectureRepo.getTeacherIdByLectureId(lectureId);
    }
    public CourseTeacherIdDTO getTeacherIdWithCourse(long lectureId){
        return lectureRepo.getCoursesByLectureId(lectureId);
    }

    public  Lecture editLecture(LectureRequest lectureRequest,long id){
        Lecture lecture= lectureRepo.findByLectureId(id);
        if(lecture!=null){
            if (lectureRequest.getUploadLecture()!=null){
                fileStorageServices.deleteFile(lecture.getUploadLecture());
                String vLecture =fileStorageServices.storeFile(lectureRequest.getUploadLecture(), lectureRequest.getUploadLecture().getOriginalFilename());
                lecture.setUploadLecture(vLecture);
            }
            lecture.setLectureTitle(lectureRequest.getLectureTitle());
            lecture.setDescription(lectureRequest.getDescription());
            return lectureRepo.save(lecture);
        }else {
            return null;
        }
    }

}


