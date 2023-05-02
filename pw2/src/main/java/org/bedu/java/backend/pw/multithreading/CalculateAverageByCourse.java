package org.bedu.java.backend.pw.multithreading;

import org.bedu.java.backend.pw.model.Course;

public class CalculateAverageByCourse implements Runnable {

    private Course course;
    private double average;

    public CalculateAverageByCourse(Course course) {
        this.course = course;
    }
    @Override
    public void run() {
        int countStudents = 0;
        for(Integer i : course.getScore().values()){
            average += i;
            countStudents++;
        }
        average /= countStudents;

        System.out.println(course.getId() + " " + course.getSubject().getName() + " course average = " + average);
    }

}
