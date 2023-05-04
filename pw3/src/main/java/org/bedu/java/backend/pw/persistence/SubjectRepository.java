package org.bedu.java.backend.pw.persistence;

import org.bedu.java.backend.pw.model.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
}
