package org.bedu.postwork.javase2project.multithreading;



import org.bedu.postwork.javase2project.model.Curso;
import org.bedu.postwork.javase2project.model.Estudiante;
import org.bedu.postwork.javase2project.model.Materia;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Postwork2 {
    public static void main(String[] args) {
        Random r = new Random();
        ExecutorService pool = Executors.newCachedThreadPool();
        Curso[] cursos = new Curso[]{
                crearCurso(r.nextInt(10), "Algebra Lineal", 1),
                crearCurso(r.nextInt(10), "Fisica Cuantica", 2),
                crearCurso(r.nextInt(10), "Calculo Diferencial", 3),
                crearCurso(r.nextInt(10), "Calculo Integral", 4),
                crearCurso(r.nextInt(10), "Algebra Multidimensional", 5)
        };
        for(Curso curso : cursos){
            pool.execute(new CalculadorPromedioCurso(curso));
        }
        pool.shutdown();
    }

    private static Curso crearCurso(int calificacion, String nombreMateria, long idCurso) {
        Curso curso = new Curso();
        curso.setId(idCurso);
        Materia materia = new Materia();
        materia.setNombre(nombreMateria);
        curso.setMateria(materia);

        Map<Estudiante, Integer> calificaciones = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            Estudiante estudiante = new Estudiante();
            estudiante.setId((long)i);
            estudiante.setNombreCompleto("Estudiante Numero " + i);
            calificaciones.put(estudiante, calificacion);
        }

        curso.setCalificaciones(calificaciones);
        return curso;
    }
}
