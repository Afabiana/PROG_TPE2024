import main.*;
import utils.CSVReader;

import java.util.*;
import main.tree.Tree;

public class Main {
    public static void main(String[] args) {

        Servicios servicios = new Servicios("./src/datasets/Procesadores.csv", "./src/datasets/Tareas.csv");

        TreeMap arbol = new TreeMap();
        TreeSet set = new TreeSet();

        CSVReader reader = new CSVReader();

        LinkedList tareas = reader.readTasks("./src/datasets/Tareas.csv");
        List<Procesador> procesadores = reader.readProcessors("./src/datasets/Procesadores.csv");

        /*
        System.out.println("Tareas: criticas" + servicios.servicio2(true));
        System.out.println("Tareas: no criticas" + servicios.servicio2(false));


        System.out.println("traer tarea 10: " + servicios.servicio1("T10"));
        System.out.println("traer tarea 20: " + servicios.servicio1("T20"));

        System.out.println("tareas prrioridad > 30 && prioridad < 60 : " +
                servicios.servicio3(30,60));

*/
        System.out.println("Procesadores: " + procesadores);
        System.out.println("Tareas: " + tareas);
        System.out.println("resultado: xxxxxxxxxxxxxxxxxxxxxxxxxxx ");
        long startTime = System.currentTimeMillis();  // arranca el timer
        int tiempoLimite = 10;
        AlgoritmoDeAsignacion algoritmoDeAsignacion = new AlgoritmoDeAsignacion();
        algoritmoDeAsignacion.asignarTareas(procesadores, tareas, tiempoLimite);
        algoritmoDeAsignacion.imprimirResultado();

        Greedy greedy = new Greedy();
        greedy.asignarTareas(procesadores, tareas, tiempoLimite);
        greedy.imprimirResultado();


        long endTime = System.currentTimeMillis();  // termina el timer
        long duracion = endTime - startTime;  // Duración en milisegundos
        System.out.println("Duración de la ejecución: " + duracion + " ms");



        //ahora vamos a probar el arbol AVL
        Tree<Integer, String> tree = new Tree<>();

        //80, 70, 90, 69,89,91,99,71,100
        tree.add(80, "80");
        tree.add(70, "70");
        tree.add(90, "90");
        tree.add(69, "69");
        tree.add(89, "89");
        tree.add(91, "91");
        tree.add(99, "99");
        tree.add(71, "71");

        System.out.println("Inorder traversal of constructed tree is : " + tree.toString());

        tree.add(100, "100");

        System.out.println("Inorder traversal of constructed tree is : " + tree.toString());
        //System.gc();


    }
}
