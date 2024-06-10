package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Greedy {
    private List<Procesador> resultado;
    private List<Procesador> procesadores;
    private List<Tarea> tareas;
    private int tiempoLimite;
    private HashSet<String> procesadoresNoDisponibles;

    public Greedy(){
        this.resultado = new ArrayList<>();
    }

    public List<Procesador> asignarTareas(List<Procesador> procesadores, List<Tarea> tareas, int tiempoLimite){
        //capaz todas estas variables se podrian pasar por parametro en el constructor
        this.procesadores = procesadores;
        this.tareas = tareas;
        this.tiempoLimite = tiempoLimite;
        this.procesadoresNoDisponibles = new HashSet<>();
        return greedy();

    }

    private List<Procesador> greedy() {
        ordenarTareas();
        while(!tareas.isEmpty()){
            boolean tareaAsignada = false; // flag para saber si se pudo encontrar un procesador disponible que cumpla con las limitaciones
            Tarea tareaActual = tareas.remove(0); // recien se actualiza la tarea actual cuando ya se pudo asignar la tarea anterior
            Procesador mejorProcesador = seleccionarProcesadorMenosOcupado();
            while (!tareaAsignada && procesadoresNoDisponibles.size()!=procesadores.size()){
                if(seLePuedeaAsignarTareaActual(mejorProcesador, tareaActual)){
                    mejorProcesador.asignarTarea(tareaActual);
                    tareaAsignada = true; // si se asigna la tarea, se cambia el flag y corta el while
                    procesadoresNoDisponibles.clear(); // se limpia la lista de procesadores no disponibles para que TODOS vuelvan a ser candidatos para la prox tarea
                }else {
                    procesadoresNoDisponibles.add(mejorProcesador.getId_procesador());
                    mejorProcesador = seleccionarProcesadorMenosOcupado();
                }
            }

            //si no se pudo asignar la tarea a NINGUN procesador, no itera mas porque no hay solucion
            if(!tareaAsignada){
                return new ArrayList<>();
            }
        }
        // si se asignaron todas las tareas, se devuelve el resultado
        this.resultado= new ArrayList<>(procesadores);
        return this.resultado;
    }

    private boolean seLePuedeaAsignarTareaActual(Procesador mejorProcesador, Tarea tareaActual) {
        boolean noSuperaTiempoLimite = true;
        boolean noSuperaCantidadTareasCriticas = true;

        if (mejorProcesador.isRefrigerado())
            noSuperaTiempoLimite = mejorProcesador.getTiempoProcesamiento() + tareaActual.getTiempo() <= tiempoLimite;
        if (tareaActual.getEsCritica())
            noSuperaCantidadTareasCriticas = mejorProcesador.getCantidadTareasCriticas() < 2;

        return noSuperaTiempoLimite && noSuperaCantidadTareasCriticas;
    }

    // selecciona el procesador con menor tiempo de procesamiento
    private Procesador seleccionarProcesadorMenosOcupado() {
        Procesador procesadorMenosOcupado = new Procesador(Integer.MAX_VALUE);
        for (Procesador procesador : procesadores) {
            if (procesador.getTiempoProcesamiento() < procesadorMenosOcupado.getTiempoProcesamiento()
                    && !procesadoresNoDisponibles.contains(procesador.getId_procesador())) {
                procesadorMenosOcupado = procesador;
            }
        }
        return procesadorMenosOcupado;
    }

    // ordena las tareas de mayor a menor tiempo de ejecución
    private void ordenarTareas() {
        Collections.sort(tareas);
    }

}
