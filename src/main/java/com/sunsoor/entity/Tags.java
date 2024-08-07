package com.sunsoor.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_sequence")
    @SequenceGenerator(name = "tag_sequence", sequenceName = "tag_sequence", allocationSize = 1)

    private Long tagId;
    private String name;
    @JsonIgnoreProperties("tags")
    @ManyToMany(mappedBy = "tags")
    private List<Courses> courses;
}
