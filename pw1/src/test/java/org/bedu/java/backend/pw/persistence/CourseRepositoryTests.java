package org.bedu.java.backend.pw.persistence;

import org.bedu.java.backend.pw.model.Course;
import org.bedu.java.backend.pw.model.Student;
import org.bedu.java.backend.pw.model.Subject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan(basePackages = "org.bedu.java.backend.pw")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CourseRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CourseRepository courseRepository;

    @BeforeAll
    void cleanDatabases() {
        studentRepository.deleteAll();
        courseRepository.deleteAll();
        subjectRepository.deleteAll();
    }

    @Test
    @DisplayName("Smoke test")
    void smokeTest() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        Student student = new Student();
        student.setFirstName("Lenin");
        student.setLastName("Meza");
        student.setCreatedAt(currentTimestamp);
        studentRepository.save(student);

        Map<Student, Integer> score = new HashMap<>();
        score.put(student, Course.NO_SCORE);

        Subject subject = new Subject();
        subject.setName("Web Development");
        subject.setCreatedAt(currentTimestamp);
        subjectRepository.save(subject);

        Course course = new Course();
        course.setId(new Random().nextLong());
        course.setCycle("2023");
        course.setSubject(subject);
        course.setCreatedAt(currentTimestamp);
        course.setScore(score);
        courseRepository.save(course);

        assertNotNull(student.getId());
        assertNotNull(subject.getId());
        assertNotNull(course.getId());
    }
}