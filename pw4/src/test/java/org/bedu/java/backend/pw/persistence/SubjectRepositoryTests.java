package org.bedu.java.backend.pw.persistence;

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

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ComponentScan(basePackages = "org.bedu.java.backend.pw")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SubjectRepositoryTests {

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

        assertNotNull(subject.getId());
    }
}