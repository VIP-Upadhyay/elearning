package com.sunsoor.dto;

import com.sunsoor.entity.Tags;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagsDTO {
    private String name;

    public TagsDTO(Tags tags) {
        this.name=tags.getName();
    }
}
