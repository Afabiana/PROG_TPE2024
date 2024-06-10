package main;

import java.util.HashMap;

public class Procesador{
    private HashMap<String, Tarea> tareasAsignadas;
    private int tiempoProcesamiento;
    private boolean refrigerado;
    private String id_procesador;
    private String codigo_procesador;
    private int anio_funcionamiento;
    private int cantidadTareasCriticas;


    public Procesador(String id_procesador, String codigo_procesador, boolean refrigerado, int anio_funcionamiento){
        this.id_procesador = id_procesador;
        this.codigo_procesador = codigo_procesador;
        this.refrigerado = refrigerado;
        this.anio_funcionamiento = anio_funcionamiento;
        this.tareasAsignadas = new HashMap<>();
        this.tiempoProcesamiento = 0;
    }

    Procesador(){
        this.tareasAsignadas = new HashMap<>();
        this.tiempoProcesamiento = 0;
    }

    public Procesador(Procesador p){
        this.id_procesador = p.getId_procesador();
        this.codigo_procesador = p.getCodigo_procesador();
        this.refrigerado = p.isRefrigerado();
        this.anio_funcionamiento = p.getAnio_funcionamiento();
        this.tareasAsignadas = new HashMap<>(p.getTareasAsignadas());
        this.tiempoProcesamiento = p.getTiempoProcesamiento();
    }

    public Procesador(int tiempoProcesamiento){
        this.tiempoProcesamiento = tiempoProcesamiento;
    }

    public HashMap<String, Tarea> getTareasAsignadas() {
        return new HashMap<>(this.tareasAsignadas);
    }

    public int getTiempoProcesamiento() {
        return tiempoProcesamiento;
    }

    public void asignarTarea(Tarea t){
        this.tareasAsignadas.put(t.getId(), t);
        this.tiempoProcesamiento += t.getTiempo();
    }

    public void quitarTarea(Tarea t){
        this.tiempoProcesamiento -= t.getTiempo();
        this.tareasAsignadas.remove(t.getId());
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

    public String getId_procesador() {
        return id_procesador;
    }

    public String getCodigo_procesador() {
        return codigo_procesador;
    }

    public int getAnio_funcionamiento() {
        return anio_funcionamiento;
    }

    public String toString(){
        return "Procesador " + this.id_procesador +": "+ this.tareasAsignadas + "\n";
    }

    public void setTiempoProcesamiento(int tiempoProcesamiento) {
        this.tiempoProcesamiento = tiempoProcesamiento;
    }


    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        Procesador p = (Procesador) o;
        return this.id_procesador.equals(p.getId_procesador());
    }

    public int getCantidadTareasCriticas() {
        return cantidadTareasCriticas;
    }

    public void setCantidadTareasCriticas(int cantidadTareasCriticas) {
        this.cantidadTareasCriticas = cantidadTareasCriticas;
    }

    public void incrementarCantidadTareasCriticas() {
        this.cantidadTareasCriticas++;
    }
}
