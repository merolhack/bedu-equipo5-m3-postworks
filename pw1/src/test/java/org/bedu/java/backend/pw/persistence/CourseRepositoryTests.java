package org.bedu.java.backend.pw.persistence;

import org.bedu.java.backend.pw.model.Course;
import org.bedu.java.backend.pw.model.Subject;
import org.bedu.java.backend.pw.persistence.CourseRepository;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan(basePackages = "org.bedu.java.backend.pw")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CourseRepositoryTests {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CourseRepository courseRepository;

    @BeforeAll
    void cleanDatabases(){
        courseRepository.deleteAll();
        subjectRepository.deleteAll();
    }

    @Test
    @DisplayName("Smoke test")
    void smokeTest(){
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        Subject subject = new Subject();
        subject.setName("Web Development");
        subject.setCreatedAt(currentTimestamp);
        subjectRepository.save(subject);

        Course course = new Course();
        course.setCycle("2023");
        course.setSubject(subject);
        course.setCreatedAt(currentTimestamp);
        courseRepository.save(course);

        assertNotNull(subject.getId());
        assertNotNull(course.getId());
    }
}