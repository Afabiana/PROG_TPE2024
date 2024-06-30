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
    private int cantidadEstados;
    private int tiempoMaxSolucion;

    public Greedy(){
        this.resultado = new ArrayList<>();
    }

    public List<Procesador> asignarTareas(List<Procesador> procesadores, List<Tarea> tareas, int tiempoLimite){
        //capaz todas estas variables se podrian pasar por parametro en el constructor
        this.procesadores = procesadores;
        this.tareas = tareas;
        this.tiempoLimite = tiempoLimite;
        this.procesadoresNoDisponibles = new HashSet<>();
        greedy();
        return this.resultado;
    }


    /*
    * La tecnica implementada se basa en
    * -Ordenar las tareas de mayor a menor tiempo de ejecución
    * -Iterar sobre las tareas y asignarlas a los procesadores disponibles que cumplan con las limitaciones
    * -ACLARACION: en este caso los candidatos son las tareas, no los procesadores
    * -El criterio para seleccionar el mejor candidato: el procesador con menor tiempo de procesamiento
    *
    * -COMPLEJIDAD: O(n*m) donde n es la cantidad de tareas y m la cantidad de procesadores
    * ya que en el peor de los casos se recorren todas las tareas y se intenta asignar a todos los procesadores
    *
    */
    private List<Procesador> greedy() {
        ordenarTareas();
        while(!tareas.isEmpty()){
            boolean tareaAsignada = false;
            Tarea tareaActual = tareas.remove(0); // recien se actualiza la tarea actual cuando ya se pudo asignar la tarea anterior
            Procesador mejorProcesador = seleccionarProcesadorMenosOcupado();

            while (!tareaAsignada && procesadoresNoDisponibles.size()!=procesadores.size()){

                if(seLePuedeaAsignarTareaActual(mejorProcesador, tareaActual)){
                    this.cantidadEstados++;
                    mejorProcesador.asignarTarea(tareaActual);
                    tareaAsignada = true; // si se asigna la tarea, se cambia el flag para cortar el while
                    tiempoMaxSolucion = Math.max(tiempoMaxSolucion, mejorProcesador.getTiempoProcesamiento());
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

        if (!mejorProcesador.isRefrigerado())
            noSuperaTiempoLimite = mejorProcesador.getTiempoProcesamiento() + tareaActual.getTiempo() <= tiempoLimite;
        if (tareaActual.esCritica())
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


    public void imprimirResultado(){
        if (!this.resultado.isEmpty()){
            String resultado = "Solución obtenida: \n";
            for (Procesador procesador : this.resultado){
                resultado += procesador.toString();
            }
            resultado += "Solucion obtenida - Tiempo máximo de ejecución: " + this.tiempoMaxSolucion + "\n";
            resultado += "Cantidad de estados generados: " + this.cantidadEstados + "\n";
            System.out.println(resultado);
        }else {
            System.out.println("No se pudo asignar las tareas a los procesadores");
        }

    }
}
