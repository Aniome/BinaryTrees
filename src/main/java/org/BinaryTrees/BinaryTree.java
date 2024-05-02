package org.BinaryTrees;

public class BinaryTree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private int depth;
    private int size;

    public void add(T data) {
        if (size == 0) {
            add(data, null);
        } else {
            root = add(data, root);
        }
        size++;
    }

    private TreeNode<T> add(T data, TreeNode<T> node) {
        if (node == null) {
            node = new TreeNode<>();
            node.data = data;
            node.left = null;
            node.right = null;
            node.parent = null;
            depth++;
            return node;
        }
        if (node.data.compareTo(data) < 0) {
            node.left = add(data, root.left);
        } else {
            node.right = add(data, root.right);
        }
        return node;
    }

}
