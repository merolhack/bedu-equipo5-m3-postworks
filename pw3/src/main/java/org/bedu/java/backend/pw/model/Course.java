package org.bedu.java.backend.pw.model;

import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;

@Setter
@Getter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="courses")
public class Course implements Comparable<Course> {

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


    //@ElementCollection
    //@CollectionTable(name = "subjects", joinColumns = {@JoinColumn(name = "subject_id", referencedColumnName = "id")})
   // @MapKeyJoinColumn(name = "subject_id", referencedColumnName = "id", )
    //@Column(name = "name")
    //@JoinTable(name = "subjects",joinColumns = { @JoinColumn(name = "subject_id", referencedColumnName = "id") })
    //@MapKeyColumn(  )
    @Formula("(select subjects.name from subjects where subjects.id = subject_id)")
    private String subjectName;

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "courses_has_students", joinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "student_id", referencedColumnName = "id")
    @Column(name = "score")
    private Map<Student, Integer> score;

    @Column(name="created_at")
    private Timestamp createdAt;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return this.subject.equals(course.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject);
    }

    @Override
    public int compareTo(Course o) {
        return this.subject.getName().compareTo(o.subject.getName());
    }
}
