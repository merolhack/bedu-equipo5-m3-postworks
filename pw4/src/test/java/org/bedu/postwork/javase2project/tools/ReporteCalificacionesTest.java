package org.bedu.postwork.javase2project.tools;

import org.assertj.core.api.Assertions;
import org.bedu.postwork.javase2project.model.Curso;
import org.bedu.postwork.javase2project.model.Estudiante;
import org.bedu.postwork.javase2project.model.Materia;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ReporteCalificacionesTest {

    private static final Materia MATERIA = new Materia();
    private static final Estudiante ESTUDIANTE_1 = new Estudiante();
    private static final Estudiante ESTUDIANTE_2 = new Estudiante();
    private static final Estudiante ESTUDIANTE_3 = new Estudiante();
    private static final Curso CURSO = new Curso();

    private static ReporteCalificaciones.Reporte reporte1;
    private static ReporteCalificaciones.Reporte reporte2;
    private static ReporteCalificaciones.Reporte reporte3;

    static{
        MATERIA.setNombre("Fisica cuantica");

        ESTUDIANTE_1.setNombreCompleto("Paulo Zamora");
        ESTUDIANTE_2.setNombreCompleto("Lenin Meza");
        ESTUDIANTE_3.setNombreCompleto("Francisco Camas");

        CURSO.setCiclo("2024");
        CURSO.setMateria(MATERIA);

        Map<Estudiante,Integer> calificaciones = new HashMap<>();
        calificaciones.put(ESTUDIANTE_1, 7);
        calificaciones.put(ESTUDIANTE_2, 8);
        calificaciones.put(ESTUDIANTE_3, 9);

        CURSO.setCalificaciones(calificaciones);

        reporte1 = new ReporteCalificaciones.Reporte("Paulo Zamora", 7);
        reporte2 = new ReporteCalificaciones.Reporte("Lenin Meza", 8);
        reporte3 = new ReporteCalificaciones.Reporte("Francisco Camas", 9);


    }

    @Test
    @DisplayName("Ordena alfabéticamente")
    void alfabetico(){
        ReporteCalificaciones reporteCalificaciones = new ReporteCalificaciones();

        assertThat(reporteCalificaciones.alfabetico(CURSO)).containsExactly(reporte3, reporte2, reporte1);
    }


    @Test
    @DisplayName("Ordena por calificaciones")
    void calificaciones(){
        ReporteCalificaciones reporteCalificaciones = new ReporteCalificaciones();

        assertThat(reporteCalificaciones.calificacion(CURSO)).containsExactly(reporte3, reporte2, reporte1);
    }

}