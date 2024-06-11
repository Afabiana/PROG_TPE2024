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
        int tiempoLimite = 50;
        AlgoritmoDeAsignacion algoritmoDeAsignacion = new AlgoritmoDeAsignacion();
        algoritmoDeAsignacion.asignarTareas(procesadores, tareas, tiempoLimite);
        algoritmoDeAsignacion.imprimirResultado();

        Greedy greedy = new Greedy();
        greedy.asignarTareas(procesadores, tareas, tiempoLimite);
        greedy.imprimirResultado();


        long endTime = System.currentTimeMillis();  // termina el timer
        long duracion = endTime - startTime;  // Duración en milisegundos
        System.out.println("Duración de la ejecución: " + duracion + " ms");


    }
}
