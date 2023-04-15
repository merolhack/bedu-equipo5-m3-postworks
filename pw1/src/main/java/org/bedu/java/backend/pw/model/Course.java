package org.bedu.java.backend.pw.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Setter
@Getter
@EqualsAndHashCode
@Builder
@Entity
@Table(name="courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="cycle")
    @Size(max=10)
    private String cycle;

    @Column(name="created_at")
    private Timestamp createdAt;

}
