package main;

import java.util.ArrayList;
import java.util.List;

public class AlgoritmoDeAsignacion{
    private List<Procesador> resultado;
    private List<Procesador> procesadores;
    private List<Tarea> tareas;
    private int tiempoMaxPorProcesador;
    private int tiempoMaxSolucion;
    private int cantidadEstados;

    public AlgoritmoDeAsignacion(){
        this.resultado = new ArrayList<>();
        this.tiempoMaxSolucion = Integer.MAX_VALUE;
        this.cantidadEstados = 0;
    }

    public List<Procesador> asignarTareas(List<Procesador> procesadores, List<Tarea> tareas, int tiempoMaxPorProcesador){
        this.procesadores = procesadores;
        this.tareas = tareas;
        //Collections.sort(this.tareas); esto nomas lo hice para que al probarlo encuentre un toque mas rapido la solucion
        this.tiempoMaxPorProcesador = tiempoMaxPorProcesador; //tiempo maximo que puede tener un procesador no refrigerado
        int tiempoMaxActual = 0;
        _asignarTareas( tiempoMaxActual );
        return this.resultado;

    }

    /*
    * La tecnica en este caso es de backtracking
    * -Se recorren todas las tareas y se intenta asignar a todos los procesadores
    * -Se evalua si se puede asignar la tarea al procesador
    * -Si se puede, se asigna y se llama recursivamente a la funcion con la tarea siguiente
    * -Se evalua si la solucion actual es mejor que la solucion anterior
    * -Si es mejor, se actualiza la solucion
    * -Despues para volver en el arból se quita la tarea asignada y se prueba asignandola a otro procesador
    * -COMPLEJIDAD: O(m^n) donde n es la cantidad de tareas y m la cantidad de procesadores
    * */

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
            Tarea tareaActual = tareas.removeFirst();
            for (Procesador procesador : procesadores){
                this.cantidadEstados++;
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
            noSuperaTiempoLimite = procesador.getTiempoProcesamiento() <= tiempoMaxPorProcesador;
        if (tareaActual.esCritica())
            noSuperaCantidadTareasCriticas = procesador.getCantidadTareasCriticas() <= 2;

        //evaluo ambas condiciones porque si procesador es refrigerado y la tarea es critica, ambas condiciones deben cumplirse
        return noSuperaTiempoLimite && noSuperaCantidadTareasCriticas;
    }

    public void imprimirResultado(){
        System.out.println("BACKTRACKING:");
        if(!resultado.isEmpty()){
            String resultado = "Solución obtenida: \n";
            for (Procesador procesador : this.resultado){
                resultado += procesador.toString();
            }
            resultado += "Solucion obtenida - Tiempo máximo de ejecución: " + this.tiempoMaxSolucion + "\n";
            resultado += "Cantidad de estados generados: " + this.cantidadEstados + "\n";
            System.out.println(resultado);
        }else {
            System.out.println("No se puede asignar las tareas a los procesadores");
        }
    }
}