package org.bedu.java.backend.pw.async;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class ReceptorSolicitudes implements Runnable{

    private boolean running = false;
    private Queue<SolicitudEstudiante>  requestsPending = new LinkedList<>();
    private final NotificadorInscripcion worker;

    public ReceptorSolicitudes(NotificadorInscripcion aWorker) {
        this.worker = aWorker;
    }

    @Override
    public void run() {
        try{
            while(this.running || !this.requestsPending.isEmpty()){
                SolicitudEstudiante studentRequests = requestsPending.poll();

                if(studentRequests == null){
                    System.out.println("En Espera de solicitudes nuevas...");
                    TimeUnit.SECONDS.sleep(1);
                    continue;
                }
                worker.notifyTeacher(studentRequests);
                TimeUnit.MILLISECONDS.sleep(250);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        this.running = true;
        new Thread(this).start();
    }

    public void stop(){
        this.running = false;
    }

    public void registerEvent(SolicitudEstudiante event){
        requestsPending.add(event);
    }

    public boolean isRunning(){
        return running;
    }
}