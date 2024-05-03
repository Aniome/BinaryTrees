package org.BinaryTrees;

public class TreeNode<T extends Comparable<T>>  {
    T data;
    TreeNode<T> left;
    TreeNode<T> right;
    TreeNode<T> parent;
}

/*
implements Comparable<TreeNode<T>>
    @Override
    public int compareTo(TreeNode<T> o) {
        return data.compareTo(o.data);
    }
 */