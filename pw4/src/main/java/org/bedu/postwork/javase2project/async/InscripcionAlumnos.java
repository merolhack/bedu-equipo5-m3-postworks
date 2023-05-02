package org.bedu.postwork.javase2project.async;

import org.bedu.postwork.javase2project.model.Curso;
import org.bedu.postwork.javase2project.model.Estudiante;
import org.bedu.postwork.javase2project.model.Materia;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class InscripcionAlumnos {
    public static void main(String[] args) {

        ReceptorSolicitudes eventLoop = new ReceptorSolicitudes(solicitud -> {
            if (solicitud.getEstudiante() == null || solicitud.getCurso() == null) {
                System.out.println("No hay datos suficientes para ejecutar la solicitud!");
            }
            System.out.println(solicitud.getEstudiante().getNombreCompleto()
                    + " ha sido inscrito en la materia " + solicitud.getCurso().getMateria().getNombre());
        });
        SolicitudEstudiante[] solicitudes = crearSolicitudes();

        for (SolicitudEstudiante s : solicitudes) {
            eventLoop.registrarEvento(s);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        eventLoop.iniciar();

        eventLoop.detener();
    }

    private static SolicitudEstudiante[] crearSolicitudes() {
        Random rnd = new Random();
        Curso[] cursos = new Curso[]{
                crearCurso(rnd, "Fisica Cuantica", 1),
                crearCurso(rnd, "Algebra Booleana", 2),
                crearCurso(rnd, "Calcula Diferencial", 3),
                crearCurso(rnd, "Calculo Integral", 4)
        };

        SolicitudEstudiante[] solicitudes = new SolicitudEstudiante[20];
        for (int i = 0; i < 20; i++) {
            Estudiante e = new Estudiante();
            e.setNombreCompleto("Estudiante " + i);
            e.setId((long)i);

            solicitudes[i] = new SolicitudEstudiante(e, cursos[rnd.nextInt(cursos.length)]);
        }
        return solicitudes;
    }

    private static Curso crearCurso(Random rnd, String nombreMateria, long idCurso) {
        Curso curso1 = new Curso();
        curso1.setId(idCurso);
        Materia materia1 = new Materia();
        materia1.setNombre(nombreMateria);
        curso1.setMateria(materia1);
        return curso1;
    }
}