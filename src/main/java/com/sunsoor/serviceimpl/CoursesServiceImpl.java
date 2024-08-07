package com.sunsoor.serviceimpl;

import com.sunsoor.dto.*;
import com.sunsoor.entity.*;
import com.sunsoor.files.FileStorageServices;
import com.sunsoor.repository.*;
import com.sunsoor.request.CourseFileRequest;
import com.sunsoor.service.CoursesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CoursesServiceImpl implements CoursesService {
    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TagsRepository tagsRepository;

    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private FileStorageServices fileStorageServices;

    @Override
    @Transactional
    public Courses createCourse(CourseWithMDTO courses, Long teacherId) {
        Courses courses1 = new Courses();
        Teacher teacher = teacherRepository.findByUserId(teacherId);
        if (teacher != null && teacher.getUserId() != null) {
            saveNewTags(courses.getTags());
            courses1.setTeacher(teacher);
            courses1.setTags(courses.getTags());
            courses1.setCategory(courses.getCategory());
            courses1.setTitle(courses.getTitle());
            courses1.setLanguage(courses.getLanguage());
            courses1.setPricePerLecture(courses.getPricePerLecture());
            courses1.setDescription(courses.getDescription());
            courses1.setUploadDate(LocalDateTime.now());
            String image = fileStorageServices.storeFile(courses.getThumbNail(), courses.getThumbNail().getOriginalFilename());
            courses1.setThumbNail(image);
            String video = fileStorageServices.storeFile(courses.getUploadDemo(), courses.getUploadDemo().getOriginalFilename());
            courses1.setUploadDemo(video);
            return coursesRepository.save(courses1);
        }else {
            return null;
        }


    }
    private void saveNewTags(List<Tags> tags) {
        if (tags != null) {
            for (Tags tag : tags) {
                tagsRepository.save(tag);
            }
        }
    }




    @Override
    public String uploadThumbNaiAndDemo(Long courseId,MultipartFile thumbNail, MultipartFile demo) {
        Optional<Courses> courses=coursesRepository.findById(courseId);
        if(courses.isPresent()){
            Courses courses1= courses.get();
            if(courses1.getUploadDemo()!=null){
                fileStorageServices.deleteFile(courses1.getUploadDemo());
            }
            if(courses1.getThumbNail()!=null){
                fileStorageServices.deleteFile(courses1.getThumbNail());
            }
            String image = fileStorageServices.storeFile(thumbNail, thumbNail.getOriginalFilename());
            courses1.setThumbNail(image);
            String video = fileStorageServices.storeFile(demo, demo.getOriginalFilename());
            courses1.setUploadDemo(video);
            coursesRepository.save(courses1);
            return "Course Demo lecture and ThumbNail uploaded successfully";
        } else  {
            return "Course Id Not found";
        }
    }



    public String updateThumbNaiAndDemo(Long courseId, CourseFileRequest courseFileRequest) {
        Optional<Courses> courses=coursesRepository.findById(courseId);
        if(courses.isPresent()){
            Courses courses1= courses.get();
            if (courseFileRequest.getThumbNail()!=null){
                fileStorageServices.deleteFile(courses1.getThumbNail());
                String image = fileStorageServices.storeFile(courseFileRequest.getThumbNail(), courseFileRequest.getThumbNail().getOriginalFilename());
                courses1.setThumbNail(image);
            }
            if (courseFileRequest.getUploadDemo()!=null){
                fileStorageServices.deleteFile(courses1.getUploadDemo());
                String video = fileStorageServices.storeFile(courseFileRequest.getUploadDemo(), courseFileRequest.getUploadDemo().getOriginalFilename());
                courses1.setUploadDemo(video);
            }
            coursesRepository.save(courses1);
            return "Course Demo lecture and ThumbNail uploaded successfully";
        } else  {
            return "Course Id Not found";
        }
    }

    @Override
    public CourseDetailDTO getCourseById(Long courseId) {
        return coursesRepository.findCourseDetailsDTOById(courseId);
    }



    @Override
    public List<CoursesDTO> getAllCourses(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "uploadDate"));
        return coursesRepository.findAll(pageable)
                .map(course -> {
                    return new CoursesDTO(
                            course.getCourseId(),
                            course.getTeacher().getTeacherId(),
                            course.getTitle(),
                            course.getCategory(),
                            course.getLanguage(),
                            course.getPricePerLecture(),
                            course.getDescription(),
                            course.getTags().stream().map(TagsDTO::new).toList(),
                            course.getLectures().stream().map(LectureDTO::new).toList(),
                            course.getUploadDemo(),
                            course.getThumbNail(),
                            course.getUploadDate()
                    );
                }).getContent();

    }

    public List<CourseInfinitDTO> getAllC(int pageNumber,int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "uploadDate"));
        return coursesRepository.findCourseInfinitDTOById(pageable).toList();
    }


    @Override
    public String editeCourseDetails(Long id, Courses courses) {
        Courses courses1=coursesRepository.findById(id).get();
        saveNewTags(courses.getTags());
       // courses1.setTeacher(courses.getTeacher());
        if(courses.getTags()!=null)
        courses1.setTags(courses.getTags());
        courses1.setCategory(courses.getCategory());
        courses1.setTitle(courses.getTitle());
        courses1.setLanguage(courses.getLanguage());
        courses1.setPricePerLecture(courses.getPricePerLecture());
        courses1.setDescription(courses.getDescription());
        coursesRepository.save(courses1);
            return "Course save successfully";

        }
        public String editeCourseFiles(Long id, Courses courses) {
        Courses courses1=coursesRepository.findById(id).get();

        coursesRepository.save(courses1);
        return "Course save successfully";

    }
    @Override
    public String deleteCourses(Long id) {
        coursesRepository.deleteById(id);
        return "Deleted successfully";
    }




    public List<CourseInfinitDTO> searchCourses(String searchKey,Integer pageNumber, Integer pageSize)
    {
        Pageable pageable = PageRequest.of(pageNumber , pageSize);
       Page<CourseInfinitDTO> courses =coursesRepository.findByTitleIgnoreCaseContainingOrCategoryNameIgnoreCaseContainingOrLanguageIgnoreCaseContainingOrTagsNameIgnoreCaseContaining(searchKey,searchKey,searchKey,searchKey,pageable);
        //Page<CoursesDTO> courses =repo.findByTitleOrCategoryOrLanguageOrTags(searchKey,searchKey,searchKey,searchKey,pageable);
        return courses.toList();

    }


}
