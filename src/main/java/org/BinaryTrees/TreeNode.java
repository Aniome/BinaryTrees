package org.BinaryTrees;

public class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>> {
    T data;
    TreeNode<T> left;
    TreeNode<T> right;
    TreeNode<T> parent;

    @Override
    public int compareTo(TreeNode<T> o) {
        return data.compareTo(o.data);
    }
}
