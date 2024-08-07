package com.sunsoor.controller;


import com.sunsoor.entity.Courses;
import com.sunsoor.entity.Tags;
import com.sunsoor.repository.CoursesRepository;
import com.sunsoor.repository.TagsRepository;
import com.sunsoor.serviceimpl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagServiceImpl service;
    @Autowired
    private TagsRepository repos;
    @Autowired
    private CoursesRepository coursesRepos;
    @PostMapping("/create/{coursId}")
    public ResponseEntity<String> createTag(@RequestBody Tags tag,@PathVariable List<Long> coursId){
        List<Courses> courses=coursId.stream().map(coursesRepos::findById).map(Optional::get).toList();
        System.out.println(courses.toString());
        tag.setCourses(courses);
        System.out.println(tag.toString());

        return new ResponseEntity<>(service.createTag(tag), HttpStatus.OK);

    }
    @GetMapping("/")
    public ResponseEntity<List<Tags>> getAllTags(){
        List<Tags> tags=this.repos.findAll();
        return new ResponseEntity<>(tags,HttpStatus.OK);
    }
}

