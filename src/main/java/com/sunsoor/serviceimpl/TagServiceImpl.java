package com.sunsoor.serviceimpl;

import com.sunsoor.entity.Tags;
import com.sunsoor.repository.TagsRepository;
import com.sunsoor.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagsRepository repo;
    @Override
    public String createTag(Tags tag) {
        this.repo.save(tag);
        System.out.println("in service "+tag.toString());
        return "Successful";
    }
}