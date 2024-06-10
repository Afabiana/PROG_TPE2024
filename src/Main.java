import main.AlgoritmoDeAsignacion;
import main.Procesador;
import main.Servicios;
import main.Tarea;
import utils.CSVReader;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Servicios servicios = new Servicios("./src/datasets/Procesadores.csv", "./src/datasets/Tareas.csv");

        /*AlgoritmoDeAsignacion algoritmoDeAsignacion = new AlgoritmoDeAsignacion();

        CSVReader reader = new CSVReader();

        List<Tarea> tareas = reader.readTasks("./src/datasets/Tareas.csv");
        List<Procesador> procesadores = reader.readProcessors("./src/datasets/Procesadores.csv");*/


        System.out.println("resultado: xxxxxxxxxxxxxxxxxxxxxxxxxxx ");
        long startTime = System.currentTimeMillis();  // arranca el timer

//        System.out.println(algoritmoDeAsignacion.asignarTareas(procesadores, tareas, 220));
//      probé con las 2 alternativas y diferentes rangos            arbol                   Recorrertodo
        //                                         rango[1,50]       0ms                        1ms
        //                                         rango[1,100]       1ms                       1ms
        // List<Tarea> tareasprioridadmenor50 = servicios.servicio3(1,100);
         List<Tarea> tareasprioridadmenor50 = servicios.servicio4(1,100);

        for (Tarea t: tareasprioridadmenor50){
            System.out.println(t);
        }

        long endTime = System.currentTimeMillis();  // termina el timer
        long duracion = endTime - startTime;  // Duración en milisegundos
        System.out.println("Duración de la ejecución: " + duracion + " ms");

        LinkedList<Integer> lista = new LinkedList<>();
        lista.add(1);
        lista.add(2);
        System.out.println(lista.getFirst());

    }
}
