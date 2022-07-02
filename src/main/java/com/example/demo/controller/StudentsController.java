package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import com.example.demo.util.ApplyCourseDto;
import com.example.demo.util.UniversalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alex Maina
 * @created 02/07/2022
 **/
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StudentsController {
    private final StudentService studentService;

    @PostMapping("/login")
    public ResponseEntity<UniversalResponse> loginStudent(@RequestBody Student student){
        return ResponseEntity.ok().body(studentService.loginStudent(student));
    }

    @GetMapping("/courses/all/{studentId}")
    public ResponseEntity<UniversalResponse>  getAllCourses(@PathVariable long studentId){
        return ResponseEntity.ok().body(studentService.getAllCourses(studentId));
    }

    @GetMapping("/student/courses")
    public ResponseEntity<UniversalResponse> getStudentCourses(@RequestParam long studentId){
        return ResponseEntity.ok().body(studentService.getAppliedCourses(studentId));
    }

    @GetMapping("/student/info")
    public ResponseEntity<UniversalResponse> getStudentInfoById(@RequestParam long studentId){
        return ResponseEntity.ok().body(studentService.getStudentInfoById(studentId));
    }

    @PostMapping("/apply/course")
    public ResponseEntity<UniversalResponse> applyCourse(@RequestBody ApplyCourseDto applyCourseDto){
        return ResponseEntity.ok().body(studentService.applyCourse(applyCourseDto.getStudentId(), applyCourseDto.getCourseId()));
    }


}
