package com.example.demo.repository;

import com.example.demo.model.StudentCourses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentCoursesRepo extends JpaRepository<StudentCourses,Long> {
    Optional<StudentCourses> findTopByCourseIdAndStudentId(long courseId, long studentId);
}
