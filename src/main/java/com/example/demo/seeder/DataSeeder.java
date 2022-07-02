package com.example.demo.seeder;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepo;
import com.example.demo.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Alex Maina
 * @created 02/07/2022
 **/
@Component
@RequiredArgsConstructor
public class DataSeeder {
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    @PostConstruct
    public void seedData(){
        List<Student> studentsData= List.of(
                new Student("Felix","Karani", "A02024",3,2,"karan.maina@gmail.com","0795861420","1234"),
                new Student("Alex","Muriithi", "A05024",2,1,"alek.maina@gmail.com","0726690702","1234"),
                new Student("John","Doe", "A0102",1,2,"john.doe@gmail.com","0711988430","1234")

        );

        List<Course> courses= List.of(
                new Course("Data engineering", " Data control, Data access engineering and data clean up",10),
                new Course("Programming in C++", "Memory pointer, Arrays , Variables ",8),
                new Course("Programming in java", "Variable declarations, class instantiation, inheritance, polymorphism",10),
                new Course("Programming in Angular", "Type script, callback functions, Asynchronous paradigms",10),
                new Course("Machine Learning", "Identify pattern in data, Regression analysis, Classification problems",10),
                new Course("Machine Learning advanced", "Model creation , Model tuning and Model deployment",10)
        );
         for (int i=0;i<studentsData.size();i++){
             Student student= studentsData.get(i);
             student.setId(i);
             studentRepo.save(student);
         }
         for (int i=0;i<courses.size();i++){
             Course course=courses.get(i);
             course.setId(i);
             courseRepo.save(course);
         }
    }
}
