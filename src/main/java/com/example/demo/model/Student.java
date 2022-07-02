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
@Table(name = "tb_student")
public class Student  extends BaseEntity{
    private String firstName;
    private String middleName;
    private String regNo;
    private int year;
    private int sem;
    private String email;
    private String phoneNo;
    private String password;


}
