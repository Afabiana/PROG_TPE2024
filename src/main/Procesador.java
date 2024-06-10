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
        // se actualiza el tiempo y la cant de criticas PERO solo si la tarea no es repetida
        if (!this.tareasAsignadas.containsKey(t.getId())){
            this.tareasAsignadas.put(t.getId(), t);
            this.tiempoProcesamiento += t.getTiempo();

            if (t.getEsCritica())
                this.cantidadTareasCriticas++;
        }
    }

    public void quitarTarea(Tarea t){
        this.tareasAsignadas.remove(t.getId());
        this.tiempoProcesamiento -= t.getTiempo();

        if (t.getEsCritica())
            this.cantidadTareasCriticas--;
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
        return "Procesador " + this.id_procesador +": tiempoDeProcesamiento :"+ this.tiempoProcesamiento +"\n tareas: "+ this.tareasAsignadas + "\n";
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

}
