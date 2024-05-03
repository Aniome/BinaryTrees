package org.BinaryTrees;

public class BinaryTree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private int size;

    public void add(T data) {
        if (size == 0) {
            root = add(data, null);
        } else {
            add(data, root);
        }
        size++;
    }

    private TreeNode<T> add(T data, TreeNode<T> node) {
        if (node == null) {
            node = new TreeNode<>();
            node.data = data;
            node.left = null;
            node.right = null;
            //node.parent = ?;
            return node;
        }
        if (data.compareTo(node.data) < 0) {
            node.left = add(data, node.left);
        } else {
            node.right = add(data, node.right);
        }
        return node;
    }

    public int getDepth() {
        return getDepth(root, 0);
    }

    private int getDepth(TreeNode<T> node, int depth) {
        if (node == null) {
            return depth;
        }
        int depthLeft = getDepth(node.left, depth);
        int depthRight = getDepth(node.right, depth);
        return Math.max(depthLeft, depthRight) + 1;
    }



}
