package org.bedu.java.backend.pw.persistence;

import org.bedu.java.backend.pw.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
