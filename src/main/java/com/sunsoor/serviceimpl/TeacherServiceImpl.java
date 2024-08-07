package com.sunsoor.serviceimpl;

import com.sunsoor.dto.CourseInfinitDTO;
import com.sunsoor.dto.TeacherCourseDTO;
import com.sunsoor.dto.TeacherDTO;
import com.sunsoor.entity.Teacher;
import com.sunsoor.files.FileStorageServices;
import com.sunsoor.repository.CoursesRepository;
import com.sunsoor.repository.StudentRepository;
import com.sunsoor.repository.TeacherRepository;
import com.sunsoor.request.TeacherRequest;
import com.sunsoor.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private FileStorageServices storageServices;

    @Autowired
    private CoursesRepository coursesRepository;

    @Override
    public String createTeacher(TeacherRequest teacher, MultipartFile profile) {
        return null;
    }

    @Override
    public String createTeacher(TeacherRequest teacher,String teacherName) {

            Teacher teacher1=new Teacher();
                teacher1.setUserId(teacher.getUserId());
                teacher1.setTeachingDomain(teacher.getTeachingDomain());
                teacher1.setExperience(teacher.getExperience());
                teacher1.setDescription(teacher.getDescription());
                teacher1.setTeacherName(teacherName);
                String image=storageServices.storeFile(teacher.getProfilePic(),teacher.getProfilePic().getOriginalFilename());
                teacher1.setProfilePic(image);
                this.teacherRepository.save(teacher1);
                System.out.println(teacher1);
                return "Teacher Created Successfully";



        }


    public String updateTeacher(TeacherRequest teacher,String teacherName) {

        Teacher teacher1= teacherRepository.findByUserId(teacher.getUserId());
        teacher1.setUserId(teacher.getUserId());
        teacher1.setTeachingDomain(teacher.getTeachingDomain());
        teacher1.setExperience(teacher.getExperience());
        teacher1.setDescription(teacher.getDescription());
        teacher1.setTeacherName(teacherName);
        if (teacher.getProfilePic()!=null){
            storageServices.deleteFile(teacher1.getProfilePic());
            String image=storageServices.storeFile(teacher.getProfilePic(),teacher.getProfilePic().getOriginalFilename());
            teacher1.setProfilePic(image);
        }
        this.teacherRepository.save(teacher1);
        return "Teacher Created Successfully";



    }


    @Override
    public List<TeacherDTO> getAllTeacher() {
        List<Teacher> teachers=teacherRepository.findAll();
        return teachers.stream().map(teacher -> new TeacherDTO(
                teacher.getTeacherId(),
                teacher.getUserId(),
                teacher.getTeachingDomain(),
                teacher.getExperience(),
                teacher.getDescription(),
                teacher.getProfilePic(),
                teacher.getTeacherName()

        )).toList();
    }

    @Override
    public String editeTeacher(Long Id, Teacher teacher) {
        Teacher teacher1=teacherRepository.findById(Id).get();
        teacher1.setUserId(teacher.getUserId());
        teacher1.setTeachingDomain(teacher.getTeachingDomain());
        teacher1.setExperience(teacher.getExperience());
        teacher1.setDescription(teacher.getDescription());
        teacher1.setCourses(teacher.getCourses());
        this.teacherRepository.save(teacher);
        return "Teacher Edited Successfully";
    }

    @Override
    public String deleteTeacher(Long Id) {
        this.teacherRepository.deleteById(Id);
        return "Teacher deleted SuccessFully";
    }

    @Override
    public TeacherDTO getTeacherById(Long tearcherId) {
        Optional<Teacher> teacher1= Optional.ofNullable(teacherRepository.findByUserId(tearcherId));
        if(teacher1.isPresent()){
            Teacher teacher=teacher1.get();
            return new TeacherDTO(
                    teacher.getTeacherId(),
                    teacher.getUserId(),
                    teacher.getTeachingDomain(),
                    teacher.getExperience(),
                    teacher.getDescription(),
                    teacher.getProfilePic(),
                    teacher.getTeacherName()
            );
        }
        return null;
    }

    public TeacherCourseDTO getTeacherWithCourseCountById(Long teacherId) {
        return teacherRepository.findTeacherWithCourseCountById(teacherId);
    }

    public List<CourseInfinitDTO> getAllC(int pageNumber, int pageSize,long Id){
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "uploadDate"));
        return coursesRepository.findCourseInfinitDTOByTeacherId(Id,pageable).toList();
    }

    @Override
    public Boolean teacherExistsByUserId(Long userId) {
         return teacherRepository.existsByUserId(userId);

    }

}
