package org.BinaryTrees;

import org.w3c.dom.Node;

public class BinaryTree<T> {
    private TreeNode<T> root;
    private int depth;
    private int size;

    public void add(T data) {
        if (size == 0) {
            add(data, null);
        } else {
            add(data, root);
        }
        size++;
    }

    private TreeNode<T> add(T data, TreeNode<T> node) {
        if (node == null) {
            root = new TreeNode<>();
            root.data = data;
            root.left = null;
            root.right = null;
            root.parent = null;
            depth++;
            return root;
        }
//      if (data < node.data) {}
        return node;
    }

}
