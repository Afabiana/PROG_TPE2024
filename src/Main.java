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
        long startTime = System.currentTimeMillis();  // Inicio del timer
        System.out.println(algoritmoDeAsignacion.asignarTareas(procesadores, tareas, 80));
        long endTime = System.currentTimeMillis();  // Fin del timer
        long duration = endTime - startTime;  // Duración en milisegundos
        System.out.println("Duración de la ejecución: " + duration + " ms");


        //esto del TreeMap nomas es para poder hacer ctrl + click y ver la implementacion de TreeMap
        TreeMap<String, Integer> arbolito = new TreeMap();

        LinkedList<Integer> lista = new LinkedList<>();
        lista.add(1);
        lista.add(2);
        System.out.println(lista.getFirst());

        /*
        Probamos la idea de usar la dif entre tiempo maximo y minimo pero no funciono porque no es
        representativo de TODOS los procesadores (hay procesadores que quedan vacios y no se toman en cuenta)
        La otra idea es sacar un promedio
        y ver cual es la diferencia de cada procesador con el promedio

        ACTUALIZACION:
            matematicamente se llama desviacion estandar (teoricamente deberia full funcionar)

       Entonces la desviacion seria parte del estado en el metodo backtracking
       para actualizar la desviacion estandar de la solucion actual deberiamos hacer:
       int tiempoAnterior = procesadorActual.getTiempoProcesamiento();
       procesadorActual.asignarTarea(tareaActual);
       desviacionActual = calcularDesviacionEstandar(tiempoAnterior, procesadorActual.getTiempoProcesamiento());

        y para volver atras deberiamos hacer:

         */
    }
}
