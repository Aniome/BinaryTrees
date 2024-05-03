package org.BinaryTrees;

import java.util.*;

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

    public void DFSPreorder() {
        if (!isTreeEmpty()) {
            DFSPreorder(root);
        }
    }

    private void DFSPreorder(TreeNode<T> node) {
        if (node != null) {
            System.out.println(node.data);
            DFSPreorder(node.left);
            DFSPreorder(node.right);
        }
    }

    public void BFS(){
        if (!isTreeEmpty()) {
            BFS(root);
        }
    }

    private void BFS(TreeNode<T> node) {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode<T> current = queue.poll();
            System.out.print(current.data);
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }


    private boolean isTreeEmpty(){
        if (size == 0){
            System.out.println("Tree is empty");
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        LinkedHashMap<TreeNode<T>, Boolean> map = new LinkedHashMap<>();
        map.put(root, true);

        while (!map.isEmpty()) {
            Map.Entry<TreeNode<T>, Boolean> entry = map.pollFirstEntry();
            TreeNode<T> current = entry.getKey();

            if (entry.getValue()){
                str.append(current.data).append("\n");
            } else {
                str.append(current.data).append(" ");
            }

            if (current.left != null) {
                map.put(current.left, false);
            }
            if (current.right != null) {
                map.put(current.right, false);
            }

            Iterator<Map.Entry<TreeNode<T>, Boolean>> iterator = map.entrySet().iterator();
            boolean isEnd = false;
            while (iterator.hasNext()) {
                Map.Entry<TreeNode<T>, Boolean> next = iterator.next();
                if (next.getValue()) {
                    isEnd = true;
                }
            }

            if (!isEnd){
                Optional<Map.Entry<TreeNode<T>, Boolean>> optional = Optional.ofNullable(map.lastEntry());
                optional.ifPresent(o -> map.put(o.getKey(), true));
            }
        }

        return str.toString();
    }
}
