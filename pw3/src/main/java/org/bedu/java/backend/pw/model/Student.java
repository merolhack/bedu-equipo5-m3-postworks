package org.bedu.java.backend.pw.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Setter
@Getter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="first_name")
    @Size(max=100)
    private String firstName;

    @Column(name="last_name")
    @Size(max=100)
    private String lastName;

    @Column(name="created_at")
    private Timestamp createdAt;
}
