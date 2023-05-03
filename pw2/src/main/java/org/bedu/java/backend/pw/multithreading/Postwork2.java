package org.bedu.java.backend.pw.multithreading;

import org.bedu.java.backend.pw.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Postwork2 {
    public static void main(String[] args) {
        Random rnd = new Random();
        ExecutorService pool = Executors.newCachedThreadPool();
        Course[] courses = new Course[]{
                createCourse(rnd, "Data bases", 1),
                createCourse(rnd, "Object Oriented Programming", 2),
                createCourse(rnd, "Artifical Inteligence", 3),
                createCourse(rnd, "Systems Integration", 4)
        };
        for(Course course : courses){
            pool.execute(new CalculateAverageByCourse(course));
        }
        pool.shutdown();
    }

    private static Course createCourse(Random rnd, String subjectName, long idCourse) {
        Course course1 = new Course();
        course1.setId(idCourse);
        Subject subject1 = new Subject();
        subject1.setName(subjectName);
        course1.setSubject(subject1);

        Map<Student, Integer> scores = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            Student student = new Student();
            student.setId((long)i);
            student.setFirstName("Student");
            student.setFirstName("" + i);
            scores.put(student, rnd.nextInt(10));
        }

        course1.setScore(scores);
        return course1;
    }
}