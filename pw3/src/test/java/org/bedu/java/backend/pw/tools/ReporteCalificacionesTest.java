package org.bedu.java.backend.pw.tools;
//package org.bedu.java.backend.pw.persistence;

import org.bedu.java.backend.pw.tools.ReporteCalificaciones;
     //org.bedu.java.backend.pw.tools;
import org.bedu.java.backend.pw.model.Course;
import org.bedu.java.backend.pw.model.Student;
import org.bedu.java.backend.pw.model.Subject;

import org.bedu.java.backend.pw.persistence.CourseRepository;
import org.bedu.java.backend.pw.persistence.StudentRepository;
import org.bedu.java.backend.pw.persistence.SubjectRepository;

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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan(basePackages = "org.bedu.java.backend.pw")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReporteCalificacionesTest {


    private static Course  COURSE_01 ;
    private static Course  COURSE_02 ;
    private static Course  COURSE_03 ;
    private static Subject SUBJECT_01;
    private static Subject SUBJECT_02;
    private static Subject SUBJECT_03;
    private static Student STUDENT_01;
    private static Student STUDENT_02;
    private static Student STUDENT_03;



    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CourseRepository courseRepository;

    @BeforeAll
    @DisplayName("Smoke test")
    void smokeTest() {
        courseRepository.deleteAll();
        studentRepository.deleteAll();
        subjectRepository.deleteAll();

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        SUBJECT_01    = new Subject();
        SUBJECT_01.setName("Mathematics");
        SUBJECT_01.setCreatedAt(currentTimestamp);
        subjectRepository.save(SUBJECT_01);

        SUBJECT_02    = new Subject();
        SUBJECT_02.setName("Web Development");
        SUBJECT_02.setCreatedAt(currentTimestamp);
        subjectRepository.save(SUBJECT_02);

        SUBJECT_03    = new Subject();
        SUBJECT_03.setName("Chaos Molecular Physic");
        SUBJECT_03.setCreatedAt(currentTimestamp);
        subjectRepository.save(SUBJECT_03);

        STUDENT_01= new Student();
        STUDENT_01.setFirstName("Lenin");
        STUDENT_01.setLastName("Meza");
        STUDENT_01.setCreatedAt(currentTimestamp);
        studentRepository.save(STUDENT_01);


        STUDENT_02= new Student();
        STUDENT_02.setFirstName("Paolo");
        STUDENT_02.setLastName("Zamora");
        STUDENT_02.setCreatedAt(currentTimestamp);
        studentRepository.save(STUDENT_02);

        STUDENT_03= new Student();
        STUDENT_03.setFirstName("Francisco");
        STUDENT_03.setLastName("Camas");
        STUDENT_03.setCreatedAt(currentTimestamp);
        studentRepository.save(STUDENT_03);

        COURSE_01    = new Course();
        COURSE_01.setCycle("2025");
        COURSE_01.setCreatedAt(currentTimestamp);
        COURSE_01.setSubject (SUBJECT_01);

        COURSE_02    = new Course();
        COURSE_02.setCycle("2025");
        COURSE_02.setCreatedAt(currentTimestamp);
        COURSE_02.setSubject (SUBJECT_02);

        COURSE_03    = new Course();
        COURSE_03.setCycle("2025");
        COURSE_03.setCreatedAt(currentTimestamp);
        COURSE_03.setSubject (SUBJECT_03);

        Map<Student,Integer> scores_01 = new HashMap<>();
        scores_01.put(STUDENT_01, 8);
        scores_01.put(STUDENT_02, 6);
        scores_01.put(STUDENT_03, 10);

        Map<Student,Integer> scores_02 = new HashMap<>();
        scores_02.put(STUDENT_01, 9);
        scores_02.put(STUDENT_02, 10);
        scores_02.put(STUDENT_03, 8);

        Map<Student,Integer> scores_03 = new HashMap<>();
        scores_03.put(STUDENT_01, 8);
        scores_03.put(STUDENT_02, 7);
        scores_03.put(STUDENT_03, 9);


        COURSE_01.setScore (scores_01);
        courseRepository.save(COURSE_01);

        COURSE_02.setScore (scores_02);
        courseRepository.save(COURSE_02);

        COURSE_03.setScore (scores_03);
        courseRepository.save(COURSE_03);
    }

    @Test
    void shortCourses() {
        ReporteCalificaciones   reporteCalificaciones;
        reporteCalificaciones = new ReporteCalificaciones(courseRepository);
        Long totalCourses     = courseRepository.count();
        System.out.println("Por ordenar: [" + totalCourses + "] elementos"); //"
        List<Course> listCourses = reporteCalificaciones.getCourses();

        System.out.println("Elementos en desorden [" +  totalCourses+ "]");
        listCourses.stream().map( Course::getSubjectName  ).collect(Collectors.toList()).forEach (  e-> System.out.println("\t"+e) );

        List<Course> listCoursesShorted = reporteCalificaciones.shortCourses(listCourses);

        System.out.println("Elementos ordenados: [" +  totalCourses+ "]");
        listCoursesShorted.stream().map( Course::getSubjectName  ).collect(Collectors.toList()).forEach ( e-> System.out.println("\t"+e) );

        System.out.println(listCoursesShorted.toString());
        assertNotNull(  listCoursesShorted);
        //assertThat
        assertEquals(listCourses.stream().sorted( Comparator.comparing(Course::getSubjectName ) ).collect(Collectors.toList()),listCoursesShorted);
    }

    @Test
    void shortGrades() {

        ReporteCalificaciones   reporteCalificaciones;
        reporteCalificaciones = new ReporteCalificaciones(courseRepository);
        Long totalCourses     = courseRepository.count();

        System.out.println("total de cursos : [" + totalCourses + "] elementos");
        /* Obtenemos todos los cursos */
        List<Course> listCourses = reporteCalificaciones.getCourses();
        /* Pero trabajaremos solo con uno */
        Iterator<Course> iterCourse =listCourses.iterator();
        while (iterCourse.hasNext() ){
            Course       course      = iterCourse.next();

            System.out.println("Por ordenar Calicaciones de la asignatura [" + course.getSubjectName()  + "]");

            /* para que persista  la estructura en memoria en el modelo es encesario apliacar
             * (fetch=FetchType.EAGER) o se pierden la informacion terminar la ejecution de getScore() */
            Map<Student, Integer> cal = course.getScore();

            cal.forEach((key, value) -> System.out.printf( "\t %s %s \t %d\n",key.getLastName(), key.getFirstName(),value));

            Map<Student, Integer> calOrd = reporteCalificaciones.shortGrades( course );
            System.out.println("Calicaciones Ordenadas de la asignatura [" + course.getSubjectName()  + "]");
            calOrd.entrySet().stream().map(  e -> "\t"+ e.getKey().getLastName()+ " "+e.getKey().getFirstName() + "\t " +e.getValue()  ).forEach(System.out::println);
            System.out.println("\n");

            assertEquals(cal.entrySet().stream()
                         .sorted(  Map.Entry.comparingByValue(Comparator.reverseOrder()) )
                         .collect(LinkedHashMap::new,(map,entry) -> map.put(entry.getKey(),entry.getValue()),LinkedHashMap::putAll), calOrd );
        }

    }
}