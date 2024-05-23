package main;

import java.util.ArrayList;
import java.util.List;

public class AlgoritmoDeAsignacion{
    private List<Procesador> resultado;
    private Integer tiempoMaxProcesamiento;
    private Integer tiempoMinProcesamiento;

    public AlgoritmoDeAsignacion(){
        this.resultado = new ArrayList<>();
    }

    public List<Procesador> asignarTareas(List<Procesador> procesadores, List<Tarea> tareas){
        this.tiempoMaxProcesamiento = Integer.MAX_VALUE;
        this.tiempoMinProcesamiento = Integer.MIN_VALUE;
        _asignarTareas(procesadores, tareas);
        System.out.println("Tiempo maximo de procesamiento: " + this.tiempoMaxProcesamiento);
        return this.resultado;

    }

    private void _asignarTareas(List<Procesador> procesadores, List<Tarea> tareas) {
        if (tareas.isEmpty()){
            int tiempoMaxEncontrado = calcularTiempoMaximo(procesadores);
            if(tiempoMaxEncontrado < this.tiempoMaxProcesamiento){
                resultado.clear();
                for (Procesador procesador : procesadores)
                    resultado.add(new Procesador(procesador));
                //tengo que hacer una copia sino cuando vuelvo en el arbol se borra el resultado
                this.tiempoMaxProcesamiento = tiempoMaxEncontrado;
            }
        }else{
            Tarea tareaActual = tareas.remove(0);
            for (Procesador procesador : procesadores){
                procesador.asignarTarea(tareaActual);
                _asignarTareas(procesadores, tareas);
                procesador.quitarTarea(tareaActual);
            }
            tareas.add(0,tareaActual);
        }
    }

    private boolean estaMejorDistribuido(int tiempoMinActual, int tiempoMaxActual) {
        return tiempoMaxProcesamiento - tiempoMinProcesamiento < tiempoMaxActual - tiempoMinActual;
    }

    //otra idea es sacar un promedio
    //aca establecemos el tiempo Maximo de la solucion ACTUAL
    private int calcularTiempoMaximo(List<Procesador> procesadores) {
        int tiempoMaximo = Integer.MIN_VALUE;
        for (Procesador procesador : procesadores){
            int aux = procesador.getTiempoProcesamiento();
            if (tiempoMaximo < aux)
                tiempoMaximo = aux;
            else if(aux < this.tiempoMinProcesamiento)
                this.tiempoMinProcesamiento = aux;
        }
        return tiempoMaximo;
    }

}
