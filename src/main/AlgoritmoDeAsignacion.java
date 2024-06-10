package main;

import java.util.ArrayList;
import java.util.List;

public class AlgoritmoDeAsignacion{
    private List<Procesador> resultado;
    private List<Procesador> procesadores;
    private List<Tarea> tareas;
    private int tiempoMaxPorProcesador;
    private int tiempoMaxSolucion;

    public AlgoritmoDeAsignacion(){
        this.resultado = new ArrayList<>();
        this.tiempoMaxSolucion = Integer.MAX_VALUE;
    }

    public List<Procesador> asignarTareas(List<Procesador> procesadores, List<Tarea> tareas, int tiempoMaxPorProcesador){
        this.procesadores = procesadores;
        this.tareas = tareas;
        //Collections.sort(this.tareas); esto nomas lo hice para que encuentre un toque mas rapido la solucion
        this.tiempoMaxPorProcesador = tiempoMaxPorProcesador; //tiempo maximo que puede tener un procesador no refrigerado
        int tiempoMaxActual = 0;
        _asignarTareas( tiempoMaxActual );
        return this.resultado;

    }


    private void _asignarTareas( int tiempoMaxActual) {

        if (tareas.isEmpty()){
            if(tiempoMaxActual < tiempoMaxSolucion){
                resultado.clear();
                tiempoMaxSolucion = tiempoMaxActual;
                for (Procesador procesador : procesadores)
                    resultado.add(new Procesador(procesador));
                //tengo que hacer una copia sino cuando vuelvo en el arbol se van a modificar los procesadores
            }
        }else{
            Tarea tareaActual = tareas.remove(0);
            for (Procesador procesador : procesadores){
                //auxiliar para volver hacia atras en el arbol
                int tiempoMaxAnterior = tiempoMaxActual;

                procesador.asignarTarea(tareaActual);
                tiempoMaxActual = Math.max(tiempoMaxActual, procesador.getTiempoProcesamiento());


                if (tiempoMaxActual < tiempoMaxSolucion){
                    if (seLePuedeaAsignarTareaActual(procesador, tareaActual))
                        _asignarTareas(tiempoMaxActual);
                }

                procesador.quitarTarea(tareaActual);
                tiempoMaxActual = tiempoMaxAnterior;
            }
            tareas.add(tareaActual);
        }
    }

    // retorna si la tarea se puede asignar al procesador
    private boolean seLePuedeaAsignarTareaActual(Procesador procesador, Tarea tareaActual) {
        boolean noSuperaTiempoLimite = true;
        boolean noSuperaCantidadTareasCriticas = true;

        if (!procesador.isRefrigerado())
            noSuperaTiempoLimite = procesador.getTiempoProcesamiento() + tareaActual.getTiempo() <= tiempoMaxPorProcesador;
        if (tareaActual.getEsCritica())
            noSuperaCantidadTareasCriticas = procesador.getCantidadTareasCriticas() <= 2;

        //evaluo ambas condiciones porque si procesador es refrigerado y la tarea es critica, ambas condiciones deben cumplirse
        return noSuperaTiempoLimite && noSuperaCantidadTareasCriticas;
    }
}