package com.example.demo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "tb_student_courses")
public class StudentCourses extends BaseEntity {
    private long studentId;
    private long courseId;
}
