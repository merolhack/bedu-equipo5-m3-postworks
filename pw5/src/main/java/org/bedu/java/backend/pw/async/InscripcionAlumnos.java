package org.bedu.java.backend.pw.async;


import org.bedu.java.backend.pw.model.Course;
import org.bedu.java.backend.pw.model.Student;
import org.bedu.java.backend.pw.model.Subject;

import java.sql.Timestamp;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class InscripcionAlumnos {


    public static void main(String[] args) {
        Random rnd = new Random();

        ReceptorSolicitudes eventLoop = new ReceptorSolicitudes(
            requestStudent ->
                            {
                                if (requestStudent.getStudent () == null || requestStudent.getCourse() == null)
                                    System.out.println("Solicitud rechazada  datos incompletos, notifique al estudiante");

                                System.out.println("El estudiante: [" + requestStudent.getStudent().getLastName()+" " + requestStudent.getStudent().getFirstName()
                                    + "] se ha inscrito en la materia: " + requestStudent.getCourse().getSubject().getName() +"");

                                System.out.println("Notificar al Profesor de [" +  requestStudent.getCourse().getSubject().getName()
                                        + "] se ha inscrito en la materia el alumno " + requestStudent.getStudent().getFirstName()+ " "+ requestStudent.getStudent().getLastName()+"\n");

                            });

        eventLoop.start();
        SolicitudEstudiante[] requestsStudents = crearSolicitudes();

        for (SolicitudEstudiante s : requestsStudents) {
            eventLoop.registerEvent (s);
            try {
                TimeUnit.MILLISECONDS.sleep(rnd.nextInt(200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        eventLoop.stop ();
    }


    private static SolicitudEstudiante[] crearSolicitudes() {
        Random rnd = new Random();
        Course[] cursos = new Course[]{
                createCourse(1,"Mathematics"),
                createCourse(2,"Web Development"),
                createCourse(3, "Chaos Molecular Physic"),
                createCourse( 4,"Data Science with Pyton"),
                createCourse(5,"Cyber Security"),
                createCourse(6,"SCRUM Master"),
                createCourse(7, "DEVOPS Advanced Course"),
                createCourse( 8,"English Upper Intermediate II"),
                createCourse(7, "Big Data Manager"),
                createCourse( 8,"Machine Learning")
        };

        SolicitudEstudiante[] solicitudes = new SolicitudEstudiante[25];

            solicitudes[0] = new SolicitudEstudiante(createStudent(1, "Cipriano", "Gato"), cursos[rnd.nextInt(cursos.length)] );
            solicitudes[1] = new SolicitudEstudiante(createStudent(2, "Booxito alias Blacky", "Labrador Negro "), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[2] = new SolicitudEstudiante(createStudent(3, "Mark", "Twain"), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[3] = new SolicitudEstudiante(createStudent(4, "Erick", "Carman"), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[4] = new SolicitudEstudiante(createStudent(5, "Blue", "Demon Jr"), cursos[rnd.nextInt(cursos.length)]);

            solicitudes[5] = new SolicitudEstudiante(createStudent(6, "Miguel", "Cervantes-Saavedra"), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[6] = new SolicitudEstudiante(createStudent(7, "William", "Shakespieare"), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[7] = new SolicitudEstudiante(createStudent(8, "Isack", "Newton"), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[8] = new SolicitudEstudiante(createStudent(9, "Federico", "De Prusia"), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[9] = new SolicitudEstudiante(createStudent(10, "Neron", "Commodo"), cursos[rnd.nextInt(cursos.length)]);

            solicitudes[10] = new SolicitudEstudiante(createStudent(1, "Nicola", "Tesla"), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[11] = new SolicitudEstudiante(createStudent(2, "Miguel", "Angel"), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[12] = new SolicitudEstudiante(createStudent(3, "Lucrecia", "Borgia"), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[13] = new SolicitudEstudiante(createStudent(4, "Maquiavelo", ""), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[14] = new SolicitudEstudiante(createStudent(5, "Rasputin", "Romanofh"), cursos[rnd.nextInt(cursos.length)]);

            solicitudes[15] = new SolicitudEstudiante(createStudent(6, "Nick", "Furry"), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[16] = new SolicitudEstudiante(createStudent(7, "Peso", "Pluma"), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[17] = new SolicitudEstudiante(createStudent(8, "Hernam", "Cortez"), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[18] = new SolicitudEstudiante(createStudent(9, "Domenikos (alias el Greco)", "Theotokopoulos "), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[19] = new SolicitudEstudiante(createStudent(10, "Edgar Allan", "Poe"), cursos[rnd.nextInt(cursos.length)]);

            solicitudes[20] = new SolicitudEstudiante(createStudent(6, "Jose Ramon (Alias la Estaca)", "San Cristobal"), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[21] = new SolicitudEstudiante(createStudent(7, "El chombo", "DJ"), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[22] = new SolicitudEstudiante(createStudent(8, "Cosme", "Fulanito"), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[23] = new SolicitudEstudiante(createStudent(9, "Barth", "Simpson"), cursos[rnd.nextInt(cursos.length)]);
            solicitudes[24] = new SolicitudEstudiante(createStudent(10, "Henry", "Ford"), cursos[rnd.nextInt(cursos.length)]);

        return solicitudes;
    }

    private static Student createStudent( long idStudent, String firtName, String lastName ) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        Student newStudent = new Student();
        newStudent.setFirstName(firtName);
        newStudent.setLastName (lastName);
        newStudent.setId((long)idStudent);
        newStudent.setCreatedAt(currentTimestamp);
       return newStudent;
    }

    private static Course createCourse( long idCourse, String nameSubject ) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        Subject subject_01 = new Subject();
        subject_01.setName (nameSubject);
        subject_01.setCreatedAt(currentTimestamp);

        Course course_01 = new Course();
        course_01.setId(idCourse);
        course_01.setCycle("2025");
        course_01.setSubject (subject_01);
        course_01.setCreatedAt(currentTimestamp);

        return course_01;
    }

}
