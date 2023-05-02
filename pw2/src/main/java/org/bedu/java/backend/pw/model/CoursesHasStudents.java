package org.bedu.java.backend.pw.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@EqualsAndHashCode
@Builder
@IdClass(CourseId.class)
@Entity
@Table(name="courses_has_students")
public class CoursesHasStudents implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "courses_FK", referencedColumnName = "id")
    private Course courseId;

    @Id
    @ManyToOne
    @JoinColumn(name = "students_FK", referencedColumnName = "id")
    private Student studentId;

    @Column(name="score")
    private Integer score;

    @Column(name="created_at")
    private Timestamp createdAt;
}
