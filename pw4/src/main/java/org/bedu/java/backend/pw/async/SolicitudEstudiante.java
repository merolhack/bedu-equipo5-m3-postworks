package org.bedu.java.backend.pw.async;

import org.bedu.java.backend.pw.model.Course;
import org.bedu.java.backend.pw.model.Student;

public class SolicitudEstudiante {
    private Student student;
    private Course course;

    public SolicitudEstudiante(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return  this.course ;
    }

    public void setCourse(Course curso) {
        this.course = curso;
    }
}