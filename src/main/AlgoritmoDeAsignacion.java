package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlgoritmoDeAsignacion{
    private List<Procesador> resultado;
    private List<Procesador> procesadores;
    private List<Tarea> tareas;
    private int cantProcesadores;
    private double desviacion;
    private double promedio;
    private int tiempoMaxPorProcesador;
    private HashMap<Procesador, Integer> contadorTareasCriticas;

    public AlgoritmoDeAsignacion(){

        this.resultado = new ArrayList<>();
        this.contadorTareasCriticas = new HashMap<>();
    }

    public List<Procesador> asignarTareas(List<Procesador> procesadores, List<Tarea> tareas, int tiempoMaxPorProcesador){
        this.desviacion = Integer.MAX_VALUE;
        this.procesadores = procesadores;
        this.tareas = tareas;
        this.cantProcesadores = procesadores.size();
        this.tiempoMaxPorProcesador = tiempoMaxPorProcesador;
        this.promedio = calcularPromedio(tareas, cantProcesadores);
        double sumatoriaDeCuadrados = iniciarSumatoriaDeCuadrados();

        _asignarTareas( 0,sumatoriaDeCuadrados, contadorTareasCriticas );
        System.out.println("Tiempo maximo de procesamiento: " );
        return this.resultado;

    }

    //esto asumiendo que todos los procesadores entran vacios
    private double iniciarSumatoriaDeCuadrados() {
        return ((0 - promedio) * (0 - promedio)) * cantProcesadores;
    }

    private int calcularPromedio(List<Tarea> tareas, int cantProcesadores) {
        int sumatoria = 0;
        for (Tarea tarea : tareas)
            sumatoria += tarea.getTiempo();
        return sumatoria / cantProcesadores;
    }

    private void _asignarTareas(double desviacionActual, double sumatoriaDeCuadradosActual, HashMap<Procesador, Integer> contadorTareasCriticas ) {
        System.out.println("entro");
        if (tareas.isEmpty()){
            desviacionActual = calcularDesviacionEstandar(sumatoriaDeCuadradosActual);
            //System.out.println("Resultado: " + resultado.toString());
            if(desviacionActual < desviacion){
                resultado.clear();
                this.desviacion = desviacionActual;
                for (Procesador procesador : procesadores)
                    resultado.add(new Procesador(procesador));
                //tengo que hacer una copia sino cuando vuelvo en el arbol se borra el resultado
            }
        }else{
            Tarea tareaActual = tareas.remove(tareas.size()-1);
            for (Procesador procesador : procesadores){
                //esto internamente lo que hace es que si no existe la clave la crea con el valor 0
                contadorTareasCriticas.putIfAbsent(procesador, 0);
                //auxiliares para volver hacia atras en el arbol
                int tiempoAnterior = procesador.getTiempoProcesamiento();
                double sumatoriaDeCuadradosAnterior = sumatoriaDeCuadradosActual;
                //int tiempoMaxAnterior = tiempoMaxActual;

                //tiempoMaxActual = Math.max(tiempoMaxActual, procesador.getTiempoProcesamiento());
                if (tareaActual.getEsCritica())
                    contadorTareasCriticas.put(procesador, contadorTareasCriticas.get(procesador) + 1);

                procesador.asignarTarea(tareaActual);
                sumatoriaDeCuadradosActual = calcularSumatoriaActual(sumatoriaDeCuadradosActual, tiempoAnterior, procesador); //sumatoria de los tiempos al cuadrado
                //System.out.println("tiempo max: " + tiempoMaxActual + "antes: " + tiempoMaxAnterior);
                //if (tiempoMaxActual < tiempoMaximoSolucion)
                if (contadorTareasCriticas.get(procesador) <= 2 &&
                        procesador.getTiempoProcesamiento() <= tiempoMaxPorProcesador)
                    _asignarTareas(desviacionActual, sumatoriaDeCuadradosActual, contadorTareasCriticas);

                sumatoriaDeCuadradosActual = sumatoriaDeCuadradosAnterior; //sumatoria de los tiempos al cuadrado
                procesador.quitarTarea(tareaActual);
                //tiempoMaxActual = tiempoMaxAnterior;
            }
            tareas.add(tareaActual);
        }
    }


    private double calcularSumatoriaActual(double sumatoriaDeCuadradosActual, int tiempoAnterior, Procesador procesador) {
        return sumatoriaDeCuadradosActual -
                Math.pow((tiempoAnterior - promedio),2) +
                Math.pow((procesador.getTiempoProcesamiento() - promedio),2);
    }

    //esto aplica nomas la definicion de desviacion estandard
    private double calcularDesviacionEstandar(double sumatoriaActual) {
        return Math.sqrt( sumatoriaActual / this.cantProcesadores );
    }

}