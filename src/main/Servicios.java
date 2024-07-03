package main;

import main.tree.Tree;
import utils.CSVReader;

import java.util.*;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "main.Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {


    private LinkedList<Tarea> tareasInicial; //esta seria la lista inicial de tareas que viene del reader
    private Map<String,Tarea> mapTareas;
    private List<Tarea> tareasCriticas;
    private List<Tarea> tareasNoCriticas;
    private Tree<Integer, Tarea> arbol;
    private List<Procesador> procesadores;
    private int LIMITEDEPRIORIDAD;

    /*
     * Expresar la complejidad temporal del constructor.
     */
    public Servicios(String pathProcesadores, String pathTareas)
    {
        LIMITEDEPRIORIDAD = 100;
        CSVReader reader = new CSVReader();
        this.procesadores = reader.readProcessors(pathProcesadores);
        //inicializamos las estructuras
        this.tareasInicial = reader.readTasks(pathTareas);
        this.mapTareas = new HashMap<>();
        this.tareasCriticas = new ArrayList<>();
        this.tareasNoCriticas = new ArrayList<>();
        this.arbol = new Tree<>();
        //cargamos las estructuras
        this.cargarEstructuras(tareasInicial);
    }


    private void cargarEstructuras(List<Tarea> tareas) {

        for (Tarea tarea : tareas) {
            //cargo map para servicio1
            mapTareas.put(tarea.getId(), tarea);

            //cargo dos listas para servicio2
            if (tarea.esCritica()) {
                tareasCriticas.add(tarea);
            } else {
                tareasNoCriticas.add(tarea);
            }

            //cargo arbol para servicio3
            this.arbol.add(tarea.getPrioridad(), tarea);
        }

    }

    /*
     * Expresar la complejidad temporal del servicio 1.
     * Dado un identificador de tarea obtener toda la información de la tarea asociada.
     * O(1) ya que el acceso a un elemento en un HashMap es constante
    */
    public Tarea servicio1(String ID) {
        return mapTareas.get(ID);
    }

    /*
     * Permitir que el usuario decida si quiere ver todas las tareas críticas o no críticas y generar
     * el listado apropiado resultante.
     * La complejidad va a ser O(1) porque el costo es constante, no depende de la cantidad de tareas.
    */
    public List<Tarea> servicio2(boolean esCritica) {
        return esCritica ? this.tareasCriticas : this.tareasNoCriticas;
    }

    /*
     * Expresar la complejidad temporal del servicio 3.
     * Obtener todas las tareas entre 2 niveles de prioridad indicados.
     */
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        return this.arbol.searchBetween(prioridadInferior, prioridadSuperior);
    }

    //getter para test
    public Tree<Integer, Tarea> getArbol() {
        return arbol;
    }

    public List<Procesador> getProcesadores() {
        return this.procesadores;
    }

    public List<Tarea> getTareas() {
        return this.tareasInicial;
    }
}
