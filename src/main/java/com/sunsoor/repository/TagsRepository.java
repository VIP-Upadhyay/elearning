package com.sunsoor.repository;

import com.sunsoor.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends JpaRepository<Tags,Long> {
    Tags findByName(String name);
}
