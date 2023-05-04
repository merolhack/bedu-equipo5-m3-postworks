package org.bedu.java.backend.pw.reactive;

import org.bedu.java.backend.pw.model.Course;
import org.bedu.java.backend.pw.model.Student;
import org.bedu.java.backend.pw.model.Subject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CalculateCourseAverageRxTest {
    private static final Course COURSE = new Course();

    static {
        Student student1 = new Student();
        student1.setFirstName("Lenin");
        student1.setLastName("Meza");

        Student student2 = new Student();
        student2.setFirstName("Paulo");
        student2.setLastName("Zamora");

        Student student3 = new Student();
        student3.setFirstName("Francisco");
        student3.setLastName("Camas");

        Subject subject = new Subject();
        subject.setName("Web Development");

        Map<Student, Integer> scores = new HashMap<>();
        scores.put(student1, 5);
        scores.put(student2, 8);
        scores.put(student3, 10);

        COURSE.setCycle("2023");
        COURSE.setSubject(subject);
        COURSE.setScore(scores);
    }

    @Test
    @DisplayName("Postwork 5")
    void calculaPromedio() {
        CalculateCourseAverageRx sut = new CalculateCourseAverageRx();
        sut.calculateAverage(COURSE)
                .subscribe(v -> assertThat(v).isEqualTo(7.66, within(0.1)));
    }
}
