package org.bedu.java.backend.pw.tools;

import org.bedu.java.backend.pw.model.Course;
import org.bedu.java.backend.pw.model.Student;
import org.bedu.java.backend.pw.model.Subject;
import org.bedu.java.backend.pw.persistence.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.LinkedHashMap;

public class ReporteCalificaciones  {
    @Autowired
    private CourseRepository courseRepository;

    public ReporteCalificaciones(CourseRepository courseRepository){
        this.courseRepository=courseRepository;
    }

    public  List<Course> getCourses(){
        return (List<Course>) courseRepository.findAll();
    }

    public  List<Course> shortCourses(List<Course> Courses){
       // return Courses.stream().sorted( ).collect(Collectors.toList());
        return Courses.stream().sorted( Comparator.comparing(Course::getSubjectName ) ).collect(Collectors.toList());

    }

    public   Map<Student, Integer>  shortGrades(Course course ){
        //Map<Student, Integer> scores =course.getScore();
        return course.getScore().entrySet().stream()
               .sorted( Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(LinkedHashMap::new,(map,entry) -> map.put(entry.getKey(),entry.getValue()),LinkedHashMap::putAll);
        //.collect(Collectors.toMap( Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));;

    }
}