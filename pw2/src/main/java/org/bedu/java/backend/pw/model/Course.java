package org.bedu.java.backend.pw.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Map;

@Setter
@Getter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="courses")
public class Course {

    public static final Integer NO_SCORE = -100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="cycle")
    @Size(max=10)
    private String cycle;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @ElementCollection
    @CollectionTable(name = "courses_has_students", joinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "student_id", referencedColumnName = "id")
    @Column(name = "score")
    private Map<Student, Integer> score;

    @Column(name="created_at")
    private Timestamp createdAt;
}
