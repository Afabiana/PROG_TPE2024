package main;

public class Tarea implements Comparable<Tarea> {
    private String id;
    private String nombre;
    private Integer tiempo;
    private Boolean esCritica;
    private Integer prioridad;

    public Tarea(String id, Integer tiempo){
        this.id = id;
        this.tiempo = tiempo;
    }

    public Tarea(String id, String nombre, Integer tiempo, Boolean esCritica, Integer prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.esCritica = esCritica;
        this.prioridad = prioridad;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public Boolean getEsCritica() {
        return esCritica;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    @Override
    public int compareTo(Tarea tarea) {
        return tarea.getTiempo().compareTo(this.tiempo);
    }

    public String toString(){
        return
                this.id + " " +
                "prioridad: " + this.prioridad
                + " critica: " + this.esCritica
                + " tiempo: " + this.tiempo + "\n";
    }


}
