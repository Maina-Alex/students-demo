package com.example.demo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Alex Maina
 * @created 02/07/2022
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_courses")
public class Course extends BaseEntity {
    private String name;
    private String description;
    private long durationInMonths;

    @Transient
    private boolean applied;

    public Course(String name, String description, long durationInMonths) {
        this.name = name;
        this.description = description;
        this.durationInMonths = durationInMonths;
    }
}
