import main.AlgoritmoDeAsignacion;
import main.Procesador;
import main.Servicios;
import main.Tarea;
import utils.CSVReader;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TreeMap;


public class Main {
    public static void main(String[] args) {
        //Servicios servicios = new Servicios("./src/datasets/Procesadores.csv", "./src/datasets/Tareas.csv");

        AlgoritmoDeAsignacion algoritmoDeAsignacion = new AlgoritmoDeAsignacion();

        CSVReader reader = new CSVReader();

        List<Tarea> tareas = reader.readTasks("./src/datasets/Tareas.csv");
        List<Procesador> procesadores = reader.readProcessors("./src/datasets/Procesadores.csv");


        System.out.println("resultado: xxxxxxxxxxxxxxxxxxxxxxxxxxx ");
        long startTime = System.currentTimeMillis();  // arranca el timer

        System.out.println(algoritmoDeAsignacion.asignarTareas(procesadores, tareas, 220));

        long endTime = System.currentTimeMillis();  // termina el timer
        long duracion = endTime - startTime;  // Duración en milisegundos
        System.out.println("Duración de la ejecución: " + duracion + " ms");


        //esto del TreeMap nomas es para poder hacer ctrl + click y ver la implementacion de TreeMap
        TreeMap<String, Integer> arbolito = new TreeMap();

        LinkedList<Integer> lista = new LinkedList<>();
        lista.add(1);
        lista.add(2);
        System.out.println(lista.getFirst());

    }
}
