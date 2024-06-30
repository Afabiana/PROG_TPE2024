package main.tree;


public class Tree<K extends Comparable<K>, V> { //necesito que K sea comparable para poder ordenar el arbol
    private TreeNode<K, V> root;

    public Tree() {
        this.root = null;
    }

    public void add(K key, V value) {
        if (this.root == null)
            this.root = new TreeNode<>(key, value);
        else
            add(this.root, key, value);
    }


    private TreeNode<K,V> add(TreeNode<K, V> currentNode, K key, V value) {
        int comp = currentNode.getKey().compareTo(key);
        if (comp > 0) { //si valor que quiero insertar < valor del nodo actual

            if (currentNode.getLeft() == null) { //si no hay nada a la izquierda, inserto
                TreeNode<K, V> temp = new TreeNode(key,   value);
                currentNode.setLeft(temp);
            } else { //si hay algo a la izquierda, sigo buscando
                add(currentNode.getLeft(),  key,  value);
            }

        } else if (comp < 0) { //si valor que quiero insertar > valor del nodo actual

            if (currentNode.getRight() == null) {
                TreeNode<K, V> temp = new TreeNode(key, value);
                currentNode.setRight(temp);
            } else {
                add(currentNode.getRight(), key, value);
            }

        }else { //si son iguales, agrego el valor a la lista asociada a esa key(prioridad)
            currentNode.getValue().add(value);
        }

        return rebalance(currentNode); //rebalanceo el arbol
    }

    private TreeNode<K,V> rebalance(TreeNode<K,V> currentNode) {
        updateHeight(currentNode); // Actualiza la altura del nodo después de la inserción
        int balance = getBalance(currentNode); // Calcula el balance del nodo

        // Rotación izquierda-izquierda o rotación derecha
        if (balance > 1) {
            if (getBalance(currentNode.getRight()) >= 0) {
                TreeNode<K,V> temp = leftRotation(currentNode);
                System.out.println("Rotación izquierda" + temp);
                currentNode = temp;
            } else {
                currentNode.setRight(rightRotation(currentNode.getRight()));
                currentNode = leftRotation(currentNode);
            }
        }
        // Rotación derecha-derecha o rotación izquierda
        else if (balance < -1) {
            if (getBalance(currentNode.getLeft()) <= 0) {
                currentNode = rightRotation(currentNode);
            } else {
                currentNode.setLeft(leftRotation(currentNode.getLeft()));
                currentNode = rightRotation(currentNode);
            }
        }
        return currentNode;
    }

    private TreeNode<K,V> leftRotation(TreeNode<K,V> unbalancedNode) {
        //aca me falta enganchar el nuevo root al nodo previo al nodo desbalanceado
        TreeNode<K,V> newRoot = unbalancedNode.getRight(); //newRoot puede ser la raiz del arbol o un subarbol
        TreeNode<K,V> leftSubtreeOfNewRoot = newRoot.getLeft();

        newRoot.setLeft(unbalancedNode);
        unbalancedNode.setRight(leftSubtreeOfNewRoot);

        //como cambie la raiz, tengo que actualizar la altura de los nodos
        updateHeight(unbalancedNode);
        updateHeight(newRoot);

        return newRoot;
    }

    private TreeNode<K,V> rightRotation(TreeNode<K,V> unbalancedNode) {
        TreeNode<K,V> newRoot = unbalancedNode.getLeft();
        TreeNode<K,V> rightSubtreeOfNewRoot = newRoot.getRight(); //aca salta si newRoot es null COMO LO ARREGLO?

        newRoot.setRight(unbalancedNode);
        unbalancedNode.setLeft(rightSubtreeOfNewRoot);

        updateHeight(unbalancedNode);
        updateHeight(newRoot);

        return newRoot;
    }

    private int getBalance(TreeNode<K,V> currentNode) {
        return (currentNode == null) ? 0 : height(currentNode.getRight()) - height(currentNode.getLeft());
    }

    //calculo la altura de un nodo
    private void updateHeight(TreeNode<K,V> currentNode) {
        currentNode.setHeight(1 + Math.max(height(currentNode.getLeft()), height(currentNode.getRight())));
    }

    private int height(TreeNode<K,V> currentNode) {
        return currentNode == null ? 0 : currentNode.getHeight();
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }
}
