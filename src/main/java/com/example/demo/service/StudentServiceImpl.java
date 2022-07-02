package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.StudentCourses;
import com.example.demo.repository.CourseRepo;
import com.example.demo.repository.StudentCoursesRepo;
import com.example.demo.repository.StudentRepo;
import com.example.demo.util.UniversalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Alex Maina
 * @created 02/07/2022
 **/
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepo studentRepo;
    private final StudentCoursesRepo studentCoursesRepo;
    private final CourseRepo courseRepo;

    @Override
    public UniversalResponse loginStudent(Student student) {
         Student std= studentRepo.
                 findByPhoneNoAndPassword(student.getPhoneNo(),student.getPassword())
                 .orElse(null);
         if(std!=null)
             return UniversalResponse.builder().status(200)
                     .message("Student successfully logged in").data(std).build();
        return UniversalResponse.builder().status(400).message("Bad credentials").build();
    }

    @Override
    public UniversalResponse getAllCourses(long studentId) {
        List<Course> courses= courseRepo.findAll()
                .stream()
                .peek(course -> {
                    boolean isRegistered = studentCoursesRepo
                            .findTopByCourseIdAndStudentId(course.getId(), studentId).isPresent();
                    if (isRegistered)
                        course.setApplied(true);
                }).toList();
        return UniversalResponse.builder().status(200).message("Student courses").data(courses).build();
    }

    @Override
    public UniversalResponse getAppliedCourses(long studentId) {
        List<Course> courses= courseRepo.findAll()
                .stream()
                .map(course -> {
                    boolean isRegistered = studentCoursesRepo
                            .findTopByCourseIdAndStudentId(course.getId(), studentId).isPresent();
                    if (isRegistered) {
                        course.setApplied(true);
                        return course ;
                    }
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toList());
        return UniversalResponse.builder().status(200).message("Student courses").data(courses).build();
    }

    @Override
    public UniversalResponse getStudentInfoById(long studentId) {
        Student student= studentRepo.findById(studentId).orElse(null);
        if(student==null)
            return UniversalResponse.builder().status(400).message("Student not found").build();
        return UniversalResponse.builder().status(200).message("Student info").data(student).build();
    }

    @Override
    public UniversalResponse applyCourse(long studentId, long courseId) {
        Student student= studentRepo.findById(studentId).orElse(null);
        if(student==null)
            return UniversalResponse.builder().status(400).message("Student not found").build();
        Course course= courseRepo.findById(courseId).orElse(null);
        if(course==null)
            return UniversalResponse.builder().status(400).message("Course not found").build();
        StudentCourses studentCourses= studentCoursesRepo
                .findTopByCourseIdAndStudentId(courseId,studentId).orElse(null);
        if(studentCourses==null)
            return UniversalResponse.builder().status(400).message("You have  already applied for this course")
                    .build();
        StudentCourses studentCourses1= new StudentCourses();
        studentCourses1.setStudentId(studentId);
        studentCourses1.setCourseId(courseId);
        studentCoursesRepo.save(studentCourses1);
        return UniversalResponse.builder().status(200).message("Course applied successfully").build();
    }
}
