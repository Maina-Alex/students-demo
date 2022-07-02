package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.util.UniversalResponse;

/**
 * @author Alex Maina
 * @created 02/07/2022
 **/
public interface StudentService {
    UniversalResponse loginStudent(Student student);
    UniversalResponse getAllCourses(long studentId);
    UniversalResponse getAppliedCourses(long studentId);
    UniversalResponse getStudentInfoById(long studentId);
    UniversalResponse applyCourse(long studentId, long courseId);

}
