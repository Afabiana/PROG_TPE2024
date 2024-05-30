package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlgoritmoDeAsignacion{
    private List<Procesador> resultado;
    private List<Procesador> procesadores;
    private List<Tarea> tareas;
    private int cantProcesadores;
    private int tiempoMaxPorProcesador;
    private int tiempoMaxSolucion;

    public AlgoritmoDeAsignacion(){
        this.resultado = new ArrayList<>();
        this.tiempoMaxSolucion = Integer.MAX_VALUE;
    }

    public List<Procesador> asignarTareas(List<Procesador> procesadores, List<Tarea> tareas, int tiempoMaxPorProcesador){
        this.procesadores = procesadores;
        this.tareas = tareas;
        this.cantProcesadores = procesadores.size();
        this.tiempoMaxPorProcesador = tiempoMaxPorProcesador;
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
                //tengo que hacer una copia sino cuando vuelvo en el arbol se borra el resultado
            }
        }else{
            Tarea tareaActual = tareas.remove(tareas.size()-1);
            for (Procesador procesador : procesadores){
                //esto internamente lo que hace es que si no existe la clave la crea con el valor 0
                //auxiliares para volver hacia atras en el arbol
                int cantTareasCriticasAnterior = procesador.getCantidadTareasCriticas();
                int tiempoMaxAnterior = tiempoMaxActual;

                tiempoMaxActual = Math.max(tiempoMaxActual, procesador.getTiempoProcesamiento());
                if (tareaActual.getEsCritica())
                    procesador.incrementarCantidadTareasCriticas();

                procesador.asignarTarea(tareaActual);
                //System.out.println("tiempo max: " + tiempoMaxActual + "antes: " + tiempoMaxAnterior);
                if (tiempoMaxActual < tiempoMaxSolucion){
                    if (procesador.getCantidadTareasCriticas() <= 2 &&
                            procesador.getTiempoProcesamiento() <= tiempoMaxPorProcesador)
                        _asignarTareas(tiempoMaxActual);
                }

                procesador.quitarTarea(tareaActual);
                procesador.setCantidadTareasCriticas(cantTareasCriticasAnterior);
                tiempoMaxActual = tiempoMaxAnterior;
            }
            tareas.add(tareaActual);
        }
    }

}