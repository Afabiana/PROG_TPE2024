package main;

import utils.CSVReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "main.Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {

    HashMap<String, Tarea> tareas = new HashMap<>();
    /*
     * Expresar la complejidad temporal del constructor.
     */
    public Servicios(String pathProcesadores, String pathTareas)
    {
        CSVReader reader = new CSVReader();
        reader.readProcessors(pathProcesadores);
        List<Tarea> tareas = reader.readTasks(pathTareas);
        cargarTareas(tareas);


    }

    private void cargarTareas(List<Tarea> tareas) {
        for(Tarea tarea : tareas){
            this.tareas.put(tarea.getId(), tarea);
        }
    }

    /*
     * Expresar la complejidad temporal del servicio 1.
     * Dado un identificador de tarea obtener toda la información de la tarea asociada.
     * O(1)
     */
    public Tarea servicio1(String ID) {
        return tareas.get(ID);
    }

    /*
     * Expresar la complejidad temporal del servicio 2.
     * Permitir que el usuario decida si quiere ver todas las tareas críticas o no críticas y generar
     * el listado apropiado resultante.
     *
     *
     */
    public List<Tarea> servicio2(boolean esCritica) {
        List<Tarea> resultado = new ArrayList<>();

        return resultado;
    }

    /*
     * Expresar la complejidad temporal del servicio 3.
     * Obtener todas las tareas entre 2 niveles de prioridad indicados.
     *
     * EVALUANDO USAR UN TREEMAP PARA ESTE SERVICIO
     */
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        return null;
    }

}
