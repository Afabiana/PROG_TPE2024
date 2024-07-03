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

    public Boolean esCritica() {
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
                this.id + " ";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Tarea)) return false;
        Tarea tarea = (Tarea) obj;
        return this.id.equals(tarea.getId());
    }

}
