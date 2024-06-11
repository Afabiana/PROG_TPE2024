import main.*;
import utils.CSVReader;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        //Servicios servicios = new Servicios("./src/datasets/Procesadores.csv", "./src/datasets/Tareas.csv");


        CSVReader reader = new CSVReader();

        List<Tarea> tareas = reader.readTasks("./src/datasets/Tareas.csv");
        List<Procesador> procesadores = reader.readProcessors("./src/datasets/Procesadores.csv");


        System.out.println("resultado: xxxxxxxxxxxxxxxxxxxxxxxxxxx ");
        long startTime = System.currentTimeMillis();  // arranca el timer

        AlgoritmoDeAsignacion algoritmoDeAsignacion = new AlgoritmoDeAsignacion();
        algoritmoDeAsignacion.asignarTareas(procesadores, tareas, 280);
        algoritmoDeAsignacion.imprimirResultado();

        Greedy greedy = new Greedy();
        greedy.asignarTareas(procesadores, tareas, 280);
        greedy.imprimirResultado();


        long endTime = System.currentTimeMillis();  // termina el timer
        long duracion = endTime - startTime;  // Duración en milisegundos
        System.out.println("Duración de la ejecución: " + duracion + " ms");


        //esto del TreeMap nomas es para poder hacer ctrl + click y ver la implementacion de TreeMap
        TreeMap<String, Integer> arbolito = new TreeMap();

        LinkedList<Integer> lista = new LinkedList<>();
        lista.add(1);
        lista.add(2);
        System.out.println(lista.getFirst());

        HashMap<Integer, Integer> numeros = new HashMap<>();
        numeros.put(1, 1);
        numeros.put(2, 2);
        numeros.put(3, 3);
        System.out.println(numeros.put(3,3));
    }
}
