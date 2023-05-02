package org.bedu.postwork.javase2project.async;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class ReceptorSolicitudes implements Runnable{

    private boolean Ejecutando = false;
    private Queue<SolicitudEstudiante> solicitudesEnEspera = new LinkedList<>();
    private final NotificadorInscripcion worker;

    public ReceptorSolicitudes(NotificadorInscripcion worker) {
        this.worker = worker;
    }

    @Override
    public void run() {
        try{
            while(Ejecutando || !solicitudesEnEspera.isEmpty()){
                SolicitudEstudiante solicitud = solicitudesEnEspera.poll();

                if(solicitud == null){
                    System.out.println("En pausa - no hay solicitudes para ejecutar...");
                    TimeUnit.SECONDS.sleep(1);
                    continue;
                }else{
                    System.out.println("Enviando notificacion...");
                    worker.enviarNotificacion(solicitud);
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void iniciar(){
        this.Ejecutando = true;
        new Thread(this).start();
    }

    public void detener(){
        this.Ejecutando = false;
    }

    public void registrarEvento(SolicitudEstudiante evento){
        solicitudesEnEspera.add(evento);
    }

    public boolean isEnEjecucion(){
        return Ejecutando;
    }
}