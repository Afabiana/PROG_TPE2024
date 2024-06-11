package main;

import utils.CSVReader;

import java.util.*;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "main.Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {


    private HashMap<String, Tarea> tareas = new HashMap<>();
    private TreeMap<Integer, ArrayList<String>> arbolito;
    private int LIMITEDEPRIORIDAD;
    /*
     * Expresar la complejidad temporal del constructor.
     */
    public Servicios(String pathProcesadores, String pathTareas)
    {
        LIMITEDEPRIORIDAD = 99;
        CSVReader reader = new CSVReader();
        arbolito = new TreeMap();
        this.inicializarArbol();
        reader.readProcessors(pathProcesadores);
        List<Tarea> tareas = reader.readTasks(pathTareas);
        this.cargarTareas(tareas);
    }

    private void inicializarArbol(){
        //inicializamos el arbol con los 100 valores posibles de prioridad.
        for (int i=1; i<100; i++){
            arbolito.put(i, new ArrayList<>());
        }
    }

    private void cargarTareas(List<Tarea> tareas) {

        for(Tarea tarea : tareas){
            //se carga en la matriz
            this.tareas.put(tarea.getId(), tarea);
            //se carga en el arbol su id, según la prioridad
            ArrayList<String> valor= arbolito.get(tarea.getPrioridad());
            valor.add(tarea.getId());
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
     * O(n)
     */
    public List<Tarea> servicio2(boolean esCritica) {
        List<Tarea> resultado = new ArrayList<>();

        for (Tarea t: tareas.values()) {
            if(t.getEsCritica() == esCritica){
                resultado.add(t);
            }
        }

        return resultado;
    }

    /*
     * Expresar la complejidad temporal del servicio 3.
     * Obtener todas las tareas entre 2 niveles de prioridad indicados.
     *
     * EVALUANDO USAR UN TREEMAP PARA ESTE SERVICIO
     *
     * caso promedio  O(log n)
     * peor caso  O(log n * n)
     * Gran alternativa por el uso en memoria
     */
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        int cursor = prioridadInferior;
        ArrayList<String> claves = new ArrayList<>();
        ArrayList<Tarea> valores = new ArrayList<>();

        while(cursor <= prioridadSuperior && cursor < LIMITEDEPRIORIDAD){
            claves.addAll(arbolito.get(cursor));
            cursor++;
        }

        for (String clave: claves) {
            valores.add(tareas.get(clave));
        }


        return valores;
    }


    /*
     * para chequear
     */
    public List<Tarea> servicio4(int prioridadInferior, int prioridadSuperior) {
        ArrayList<Tarea> copia = new ArrayList<>();

        for (Tarea t: tareas.values()) {
            int prioActual = t.getPrioridad();
            if(prioridadInferior <= prioActual && prioridadSuperior >= prioActual){
                copia.add(t);
            }
        }
        return copia;
    }

}
