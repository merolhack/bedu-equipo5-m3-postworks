package org.bedu.java.backend.pw.persistence;

import org.bedu.java.backend.pw.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
