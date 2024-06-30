package main.tree;


import java.util.LinkedList;
import java.util.List;

public class TreeNode <K, V> {
    private K key;
    private List<V> values; //TODO: lista de valores o deberia aclarar en la parametrizacion que V es una lista?
    private TreeNode<K, V> left;
    private TreeNode<K, V> right;
    private int height;

    public TreeNode(K key, V value) {
        this.key = key;
        this.values = new LinkedList<>();
        this.left = null;
        this.right = null;
        this.height = 1; //altura inicial
    }

    public K getKey() {
        return key;
    }

    public List<V> getValue() {
        return values;
    }

    public TreeNode<K, V> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<K, V> left) {
        this.left = left;
    }

    public TreeNode<K, V> getRight() {
        return this.right;
    }

    public void setRight(TreeNode<K, V> right) {
        this.right = right;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "key=" + key +
                ", values=" + values +
                ", left=" + left +
                ", right=" + right +
                ", height=" + height +
                '}';
    }
}
