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

    /*
     * Si nos quedamos con la complejidad mas alta de las operaciones que se realizan en el constructor
     * la complejidad total del constructor es O(n log n) siendo n la cantidad de tareas
     */
    public Servicios(String pathProcesadores, String pathTareas)
    {
        CSVReader reader = new CSVReader();
        //inicializamos las estructuras
        this.tareasInicial = reader.readTasks(pathTareas); //complejidad O(n) siendo n la cantidad de tareas
        this.mapTareas = new HashMap<>();
        this.tareasCriticas = new ArrayList<>();
        this.tareasNoCriticas = new ArrayList<>();
        this.arbol = new Tree<>();
        //cargamos las estructuras
        this.cargarEstructuras(tareasInicial);
    }

    //teniendo en cuenta que el arbol es un arbol balanceado, la complejidad de insercion es O(log n) para cada insercion
    //entnoces la complejidad total de cargarEstructuras es O(n log n) siendo n la cantidad de tareas
    //ya que por cada tarea voy a tener que insertar en el arbol
    private void cargarEstructuras(List<Tarea> tareas) {

        for (Tarea tarea : tareas) { //complejidad O(n) siendo n la cantidad de tareas
            //cargo map para servicio1
            mapTareas.put(tarea.getId(), tarea); //complejidad O(1)

            //cargo dos listas para servicio2
            if (tarea.esCritica()) { //complejidad O(1)
                tareasCriticas.add(tarea);
            } else {
                tareasNoCriticas.add(tarea);
            }

            //cargo arbol para servicio3
            this.arbol.add(tarea.getPrioridad(), tarea);//complejidad O(log n) siendo n la cantidad de tareas en el arbol
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
     * En el peor de los casos
     * prioridadInferior = 0
     * prioridadSuperior = 101 (porque la prioridad va de 1 a 100)
     * y todos los nodos/elementos del arbol formaran parte de la lista solucion
     * COMPLEJIDAD: O(n) siendo n la cantidad de nodos (prioridades) que hayan
     */
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        return this.arbol.searchBetween(prioridadInferior, prioridadSuperior);
    }

}
