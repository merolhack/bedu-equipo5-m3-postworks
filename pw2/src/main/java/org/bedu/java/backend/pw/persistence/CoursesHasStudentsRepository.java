package org.bedu.java.backend.pw.persistence;

import org.bedu.java.backend.pw.model.CourseId;
import org.bedu.java.backend.pw.model.CoursesHasStudents;
import org.springframework.data.repository.CrudRepository;

public interface CoursesHasStudentsRepository extends CrudRepository<CoursesHasStudents, CourseId> {
}
