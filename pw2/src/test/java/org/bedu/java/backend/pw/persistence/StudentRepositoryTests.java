package org.bedu.java.backend.pw.persistence;

import org.bedu.java.backend.pw.model.Student;
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
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    @BeforeAll
    void cleanDatabases(){
        studentRepository.deleteAll();
    }

    @Test
    @DisplayName("Smoke test")
    void smokeTest(){
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        Student student = new Student();
        student.setFirstName("Lenin");
        student.setLastName("Meza");
        student.setCreatedAt(currentTimestamp);
        studentRepository.save(student);

        assertNotNull(student.getId());
    }
}