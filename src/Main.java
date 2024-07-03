import main.*;
import utils.CSVReader;

import java.util.*;
import main.tree.Tree;

public class Main {
    public static void main(String[] args) {



        Servicios servicios = new Servicios("./src/datasets/Procesadores.csv", "./src/datasets/Tareas1.csv");


        CSVReader reader = new CSVReader();
        List<Procesador> procesadores = reader.readProcessors("./src/datasets/Procesadores.csv");
        LinkedList<Tarea> tareas1 = reader.readTasks("./src/datasets/Tareas1.csv");
        LinkedList<Tarea> tareas2 = reader.readTasks("./src/datasets/Tareas2.csv");


        System.out.println("arbol: " + servicios.getArbol());

        //TEST CON DATASET 1

        //SERVICIO 1
        Tarea tarea3 = servicios.servicio1("T3");
        Tarea tarea4 = servicios.servicio1("T4");
        Tarea tarea5 = servicios.servicio1("T5");

        System.out.println("Tarea 3: " + tarea3);
        System.out.println("Tarea 4: " + tarea4);
        System.out.println("Tarea 5: " + tarea5);

        //SERVICIO 2
        List<Tarea> tareasCriticas = servicios.servicio2(true);
        List<Tarea> tareasNoCriticas = servicios.servicio2(false);

        System.out.println("Tareas criticas: " + tareasCriticas);
        System.out.println("Tareas no criticas: " + tareasNoCriticas);

        //SERVICIO 3
        List<Tarea> tareasEntrePrioridades = servicios.servicio3(30, 60);
        System.out.println("Tareas entre prioridades 30 y 60: " + tareasEntrePrioridades);

        //BACKTRACKING con dataset 1 y Tiempo máximo de procesadores no refrigerados = 10
        Backtracking backtracking = new Backtracking(procesadores, tareas1);
        Greedy greedy = new Greedy(procesadores, tareas1);
        int tiempoMaxPorProcesador = 10;
        System.out.println("dataset 1 - tiempo maximo de procesadores no refrigerados = 10");

        backtracking.asignarTareas( tiempoMaxPorProcesador  );
        backtracking.imprimirResultado();

        //GREEDY con dataset 1 y Tiempo máximo de procesadores no refrigerados = 10
        greedy.asignarTareas(   tiempoMaxPorProcesador  );
        greedy.imprimirResultado();

        //BACKTRACKING con dataset 1 y Tiempo máximo de procesadores no refrigerados = 200
        backtracking = new Backtracking(procesadores, tareas1);
        System.out.println("dataset 1 - tiempo maximo de procesadores no refrigerados = 200");
        tiempoMaxPorProcesador = 200;

        backtracking.asignarTareas( tiempoMaxPorProcesador  );
        backtracking.imprimirResultado();

        //GREEDY con dataset 1 y Tiempo máximo de procesadores no refrigerados = 200
        greedy = new Greedy(procesadores, tareas1);
        greedy.asignarTareas(   tiempoMaxPorProcesador  );
        greedy.imprimirResultado();

        //TEST CON DATASET 2
        backtracking = new Backtracking(procesadores, tareas2);
        greedy = new Greedy(procesadores, tareas2);
        tiempoMaxPorProcesador = 200;
        System.out.println("dataset 2 - tiempo maximo de procesadores no refrigerados = 200");

        //BACKTRACKING con dataset 2 y Tiempo máximo de procesadores no refrigerados = 200
        backtracking.asignarTareas(   tiempoMaxPorProcesador   );
        backtracking.imprimirResultado();

        //GREEDY con dataset 2 y Tiempo máximo de procesadores no refrigerados = 200
        greedy.asignarTareas(   tiempoMaxPorProcesador  );
        greedy.imprimirResultado();

        //BACKTRACKING con dataset 2 y Tiempo máximo de procesadores no refrigerados = 100
        backtracking = new Backtracking(procesadores, tareas2);
        greedy = new Greedy(procesadores, tareas2);
        tiempoMaxPorProcesador = 100;
        System.out.println("dataset 2 - tiempo maximo de procesadores no refrigerados = 100");

        backtracking.asignarTareas(   tiempoMaxPorProcesador   );
        backtracking.imprimirResultado();

        //GREEDY con dataset 2 y Tiempo máximo de procesadores no refrigerados = 100
        greedy.asignarTareas(   tiempoMaxPorProcesador   );
        greedy.imprimirResultado();

        //BACKTRACKING con dataset 2 y Tiempo máximo de procesadores no refrigerados = 80
        backtracking = new Backtracking(procesadores, tareas2);
        greedy = new Greedy(procesadores, tareas2);
        tiempoMaxPorProcesador = 80;

        backtracking.asignarTareas(   tiempoMaxPorProcesador   );
        backtracking.imprimirResultado();


        //GREEDY con dataset 2 y Tiempo máximo de procesadores no refrigerados = 80
        greedy.asignarTareas(   tiempoMaxPorProcesador   );
        greedy.imprimirResultado();
/*
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

        //otro arbol
        System.out.println("ARBOL 2");
        Tree<Integer, String> tree2 = new Tree<>();

        //50, 10, 70, 60, 80
        tree2.add(50, "cincuenta");
        tree2.add(10, "diez");
        tree2.add(20, "veinte");
        tree2.add(70, "setenta");
        tree2.add(60, "sesenta");
        tree2.add(70, "tuki");
        tree2.add(80, "ochenta");
        System.out.println("Inorder traversal of constructed tree is : " + tree2.toString());

        tree2.add(85, "ochenta y cinco");
        tree2.add(85, "hola");

        tree2.add(5, "cinco");
        tree2.add(2, "dos");
        System.out.println("Inorder traversal of constructed tree is : " + tree2.toString());

        System.out.println(tree2.search(85));
        System.out.println(tree2.searchBetween(9,86));

        Tree<Integer, String> tree3 = new Tree<>();
        tree3.add(100, "cien");
        tree3.add(50, "cincuenta");
        tree3.add(120, "ciento veinte");
        tree3.add(110, "ciento diez");
        tree3.add(101, "ciento uno");
        System.out.println("Inorder traversal of constructed tree is : " + tree3.toString());

        Tree<Integer, String> tree4 = new Tree<>();
        tree4.add(100, "cien");
        tree4.add(110, "ciento diez");
        tree4.add(120, "ciento veinte");
        tree4.add(121, "ciento veintiuno");
        tree4.add(122, "ciento veintidos");
        tree4.add(123, "ciento veintitres");
        tree4.add(124, "ciento veinticuatro");
        tree4.add(125, "ciento veinticinco");

        System.out.println("Inorder traversal of constructed tree is : " + tree4.toString());
        System.out.println(tree4.searchBetween(100, 124));

        Tree<Integer, String> tree5 = new Tree<>();
        //100,10, 5, 180, 168, 20, 182, 3, 25, 167, 181, 169, 110
        tree5.add(100, "cien");
        tree5.add(10, "diez");
        tree5.add(5, "cinco");
        tree5.add(180, "ciento ochenta");
        tree5.add(168, "ciento sesenta y ocho");
        tree5.add(20, "veinte");
        tree5.add(182, "ciento ochenta y dos");
        tree5.add(3, "tres");
        tree5.add(25, "veinticinco");
        tree5.add(167, "ciento sesenta y siete");
        tree5.add(181, "ciento ochenta y uno");
        tree5.add(169, "ciento sesenta y nueve");
        tree5.add(110, "ciento diez");

        System.out.println("Inorder traversal of constructed tree is : " + tree5.toString());

        //100,10, 180, 5,  168, 20, 182, 3, 167, 25,  181, 169, 110
        Tree<Integer, String> tree6 = new Tree<>();
        tree6.add(100, "cien");
        tree6.add(10, "diez");
        tree6.add(180, "ciento ochenta");
        tree6.add(5, "cinco");
        tree6.add(168, "ciento sesenta y ocho");
        tree6.add(20, "veinte");
        tree6.add(182, "ciento ochenta y dos");
        tree6.add(3, "tres");
        tree6.add(167, "ciento sesenta y siete");
        tree6.add(25, "veinticinco");
        tree6.add(181, "ciento ochenta y uno");
        tree6.add(169, "ciento sesenta y nueve");
        tree6.add(110, "ciento diez");

        System.out.println("Inorder traversal of constructed tree is : " + tree6.toString());
        System.out.println(tree6.searchBetween(10, 120));
*/
    }
}
