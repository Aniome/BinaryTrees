package org.BinaryTrees;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(5);

        tree.add(3);
        tree.add(1);
        tree.add(4);

        tree.add(7);
        tree.add(6);
        tree.add(9);
        //tree.DFSPreorder();
        //tree.BFS();
        //tree.DFSInorder();
        tree.DFSPostorder();
        System.out.println();
        System.out.println(tree);
    }
    //      5
    //  3       7
    //1   4   6   9
}