import main.AlgoritmoDeAsignacion;
import main.Procesador;
import main.Servicios;
import main.Tarea;
import utils.CSVReader;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        //Servicios servicios = new Servicios("./src/datasets/Procesadores.csv", "./src/datasets/Tareas.csv");

        AlgoritmoDeAsignacion algoritmoDeAsignacion = new AlgoritmoDeAsignacion();

        Tarea tarea1 = new Tarea("T1","tarea 1", 10, true, 1);
        Tarea tarea2 = new Tarea("T2","tarea 2",1,true, 5);
        Tarea tarea3 = new Tarea("T3","tarea 3",1,false, 5);
        Tarea tarea4 = new Tarea("T4","tarea 4",2, true, 2);
        Tarea tarea5 = new Tarea("T5","tarea 5",6,false, 1);
        Tarea tarea6 = new Tarea("T6","tarea 6", 8,false,1);
        CSVReader reader = new CSVReader();

        List<Tarea> tareas = reader.readTasks("./src/datasets/Tareas.csv");
        List<Procesador> procesadores = reader.readProcessors("./src/datasets/Procesadores.csv");


        System.out.println(algoritmoDeAsignacion.asignarTareas(procesadores, tareas));

        //esto del TreeMap nomas es para poder hacer ctrl + click y ver la implementacion de TreeMap
        TreeMap<String, Integer> arbolito = new TreeMap();
        arbolito.put(tarea1.getId(), tarea1.getTiempo());
        arbolito.put(tarea2.getId(), tarea2.getTiempo());
        arbolito.put(tarea3.getId(), tarea3.getTiempo());


        /*
        CON LA IMPLEMENTACION ACTUAL LOGRAMOS EL MENOR TIEMPO DE EJECUCION PERO NO LA MEJOR DISTRIBUCION DE TAREAS
        Para lograr una mejor distribucion necesitaria calcular la diferencia entre el max y min tiempo.

        en el estado que voy arrastrando en el metodo recursivo podemos tener una variable que guarde la diferencia entre el max y min tiempo
        entonces al asignar todas las tareas en el metodo backtracking podemos chequear tambien si
            difActual < difMinima
                mejorSolucion = solucionActual

       con un treemap puedo ordenar los procesadores por tiempo de ejecucion
       y asi accedo mas rapido al mayor y menor de ejecucion pidiendo nomas el primero y el ultimo (O(h)) : h=altura del arbol
       pero al hacer el put en el treemap en cada llamado recursivo tendria una complejidad de O(log n) porque el arbol se reordena

       en cambio el for siempre va a ser o(n) para calcular el maximo y minimo PERO solo se calcularia cuando se encontro una posible solucion

       Lo mejor para simplificar el tiempo CREO(no estoy segura) que seria crear una clase que internamente tenga un hashmap<Integer, Integer> donde K:id_procesador
        y V:procesador.tiempo_de_ejecucion

       pero que tambien tenga como atributos el max y minimo de tiempo de ejecucion. Ahi se podria hacer un put en O(1) y obtener el max y min en O(1)
       total al agregar o modificar el tiempo de ejecucion de un procesador chequeo
            si valor_actualizado > maximo
                maximo = valor_actualizado
            si valor_actualizado < minimo
                minimo = valor_actualizado
        */

    }
}
