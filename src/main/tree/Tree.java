package main.tree;


import java.util.LinkedList;
import java.util.List;

public class Tree<K extends Comparable<K>, V> { //necesito que K sea comparable para poder ordenar el arbol
    private TreeNode<K, V> root;

    public Tree() {
        this.root = null;
    }

    public void add(K key, V value) {
        if (this.root == null)
            this.root = new TreeNode<>(key, value);
        else
            add(null, this.root, key, value);
    }


    private TreeNode<K,V> add(TreeNode<K, V> prevNode, TreeNode<K, V> currentNode,
                              K key, V value) {
        int comp = currentNode.getKey().compareTo(key);
        if (comp > 0) { //si valor que quiero insertar < valor del nodo actual

            if (currentNode.getLeft() == null) { //si no hay nada a la izquierda, inserto
                TreeNode<K, V> temp = new TreeNode(key,   value);
                currentNode.setLeft(temp);
            } else { //si hay algo a la izquierda, sigo buscando
                add(currentNode,    currentNode.getLeft(),  key,  value);
            }

        } else if (comp < 0) { //si valor que quiero insertar > valor del nodo actual

            if (currentNode.getRight() == null) {
                TreeNode<K, V> temp = new TreeNode(key, value);
                currentNode.setRight(temp);
            } else {
                add(currentNode,    currentNode.getRight(), key, value);
            }

        }else { //si son iguales, agrego el valor a la lista asociada a esa key(prioridad)
            currentNode.getValue().add(value);
        }

        return rebalance(prevNode,  currentNode); //rebalanceo el arbol
    }

    private TreeNode<K,V> rebalance(TreeNode<K,V> prevNode, TreeNode<K,V> currentNode) {
        updateHeight(currentNode);
        int balance = getBalance(currentNode);

        TreeNode<K,V> originalNode = currentNode;
        if (balance > 1) {
            if (getBalance(currentNode.getRight()) >= 0) {
                currentNode = leftRotation(currentNode);
            } else {
                currentNode.setRight(rightRotation(currentNode.getRight()));
                currentNode = leftRotation(currentNode);
            }
        } else if (balance < -1) {
            if (getBalance(currentNode.getLeft()) <= 0) {
                currentNode = rightRotation(currentNode);
            } else {
                currentNode.setLeft(leftRotation(currentNode.getLeft()));
                currentNode = rightRotation(currentNode);
            }
        }

        //una vez que rebalanceo, el nodo que antes apuntaba a currentNode, ahora tiene que apuntar al nuevo nodo
        //pude haber actualizado mas arriba la referencia de prev node pero no tenia en cuenta que el nodo podia ser la raiz
        if (prevNode == null) {
            this.root = currentNode;
        } else {
            if (prevNode.getLeft() == originalNode) {
                prevNode.setLeft(currentNode);
            } else {
                prevNode.setRight(currentNode);
            }
        }

        return currentNode;
    }

    private TreeNode<K,V> leftRotation(TreeNode<K,V> unbalancedNode) {
        System.out.println("hubo rotacion izquierda en el subarbol con raiz: " + unbalancedNode.getKey());
        TreeNode<K,V> newRoot = unbalancedNode.getRight(); //newRoot puede ser la raiz del arbol o un subarbol
        TreeNode<K,V> leftSubtreeOfNewRoot = newRoot.getLeft();

        newRoot.setLeft(unbalancedNode);
        unbalancedNode.setRight(leftSubtreeOfNewRoot);

        //como cambio la estructura del arbol, tengo que actualizar las alturas
        updateHeight(unbalancedNode);
        updateHeight(newRoot);

        return newRoot;
    }

    private TreeNode<K,V> rightRotation(TreeNode<K,V> unbalancedNode) {
        System.out.println("hubo rotacion derecha en el subarbol con raiz: " + unbalancedNode.getKey());
        TreeNode<K,V> newRoot = unbalancedNode.getLeft();
        TreeNode<K,V> rightSubtreeOfNewRoot = newRoot.getRight();

        newRoot.setRight(unbalancedNode);
        unbalancedNode.setLeft(rightSubtreeOfNewRoot);

        updateHeight(unbalancedNode);
        updateHeight(newRoot);

        return newRoot;
    }

    private int getBalance(TreeNode<K,V> currentNode) {
        return (currentNode == null) ? 0 : height(currentNode.getRight()) - height(currentNode.getLeft());
    }

    private void updateHeight(TreeNode<K,V> currentNode) {
        currentNode.setHeight(1 + Math.max(height(currentNode.getLeft()), height(currentNode.getRight())));
    }

    private int height(TreeNode<K,V> currentNode) {
        return currentNode == null ? 0 : currentNode.getHeight();
    }

    public List<V> search(K key) {
        List<V> result = new LinkedList<>();

        if (this.root == null) {
            return result;
        }
        return search(this.root, key);
    }

    private List<V> search(TreeNode<K, V> currentNode, K key) {
        int comp = currentNode.getKey().compareTo(key);
        if (comp > 0) {
            if (currentNode.getLeft() == null) {
                return new LinkedList<>();
            } else {
                return search(currentNode.getLeft(), key);
            }
        } else if (comp < 0) {
            if (currentNode.getRight() == null) {
                return new LinkedList<>();
            } else {
                return search(currentNode.getRight(), key);
            }
        } else {
            return currentNode.getValue();
        }
    }

    public List<V> searchBetween(K lower, K upper) {
        List<V> result = new LinkedList<>();
        if (this.root == null) {
            return result;
        }
        searchBetween(this.root, lower, upper, result);
        System.out.println("iteraciones: " + iteraciones);
        return result;
    }

    int iteraciones = 0;
    private List<V> searchBetween(TreeNode<K, V> currentNode, K lower, K upper, List<V> result) {
        iteraciones++;
        if (currentNode == null) {
            return result;
        }
        int compLower = currentNode.getKey().compareTo(lower);
        int compUpper = currentNode.getKey().compareTo(upper);


        if (compLower <= 0) { //mientras la key actual sea menor al lower, busco a la derecha
            searchBetween(currentNode.getRight(), lower, upper, result);
        }
        if (compLower > 0 && compUpper < 0) { //si la key actual esta entre los limites, agrego a la lista
            result.addAll(currentNode.getValue());
            searchBetween(currentNode.getRight(), lower, upper, result);
            searchBetween(currentNode.getLeft(), lower, upper, result);
        }
        if (compUpper >= 0) { //misntras la key actual es mayor al upper, busco a la izquierda
            searchBetween(currentNode.getLeft(), lower, upper, result);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }
}
