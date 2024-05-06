package org.BinaryTrees;

public class TreeNode<T extends Comparable<T>>  {
    T data;
    TreeNode<T> left;
    TreeNode<T> right;
    TreeNode<T> parent;
}