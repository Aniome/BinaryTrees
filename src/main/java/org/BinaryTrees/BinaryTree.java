package org.BinaryTrees;

import java.util.*;

public class BinaryTree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private int size;

    public void add(T data) {
        if (size == 0) {
            root = add(data, null, null);
        } else {
            add(data, root, root.parent);
        }
        size++;
    }

    private TreeNode<T> add(T data, TreeNode<T> node, TreeNode<T> parent) {
        if (node == null) {
            node = new TreeNode<>();
            node.data = data;
            node.left = null;
            node.right = null;
            node.parent = parent;
            return node;
        }
        if (data.compareTo(node.data) < 0) {
            node.left = add(data, node.left, node);
        } else {
            node.right = add(data, node.right, node);
        }
        return node;
    }

    public void delete(T data) {
        if (size == 0) {
            return;
        }
        Optional<TreeNode<T>> optionalNode = Optional.ofNullable(find(data, root));
        if (optionalNode.isPresent()) {
            TreeNode<T> node = optionalNode.get();
            if (node == root){
                deleteNode(node);
            }
            if (node.left == null && node.right == null) {
                deleteLeaf(node);
            }
            if (node.left == null && node.right != null) {
                deleteRightNode(node);
            }
            if (node.left != null && node.right == null) {
                deleteLeftNode(node);
            }
            if (node.left != null && node.right != null) {
                deleteTwoChildNode(node);
            }
            size--;
        } else {
            System.out.println("Node not found");
        }
    }

    private void deleteTwoChildNode(TreeNode<T> node) {
        TreeNode<T> replaceNode = findMinNode(node.right);
        TreeNode<T> parent = node.parent;
        replaceNode.parent = parent;
        replaceNode.left = node.left;
        if (node.right != replaceNode) {
            replaceNode.right = node.right;
        }
        if (parent.left == node){
            parent.left = replaceNode;
        } else {
            parent.right = replaceNode;
        }
        deleteNode(node);
    }

    private TreeNode<T> findMinNode(TreeNode<T> node) {
        if (node.left != null) {
            return findMinNode(node.left);
        }
        if (node.right != null) {
            return findMinNode(node.right);
        }
        return node;
    }

    private void deleteLeftNode(TreeNode<T> node) {
        TreeNode<T> parent = node.parent;
        if (parent.left == node) {
            parent.left = node.left;
        } else {
            parent.right = node.left;
        }
        deleteNode(node);
    }

    private void deleteRightNode(TreeNode<T> node) {
        TreeNode<T> parent = node.parent;
        if (parent.right == node) {
            parent.right = node.right;
        } else {
            parent.left = node.right;
        }
        deleteNode(node);
    }

    private void deleteLeaf(TreeNode<T> node) {
        TreeNode<T> parent = node.parent;
        node.data = null;
        if (parent.left == node){
            parent.left = null;
        }
        if (parent.right == node){
            parent.right = null;
        }
        deleteNode(node);
    }

    private void deleteNode(TreeNode<T> node) {
        node.parent = null;
        node.left = null;
        node.right = null;
        node.data = null;
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

    public void DFSInorder() {
        if (!isTreeEmpty()) {
            DFSInorder(root);
        }
    }

    private void DFSInorder(TreeNode<T> node) {
        if (node != null) {
            DFSPreorder(node.left);
            System.out.println(node.data);
            DFSPreorder(node.right);
        }
    }

    public void DFSPostorder() {
        if (!isTreeEmpty()) {
            DFSPostorder(root);
        }
    }

    private void DFSPostorder(TreeNode<T> node) {
        if (node != null) {
            DFSPostorder(node.left);
            DFSPostorder(node.right);
            System.out.println(node.data);
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

    public void find(T data){
        Optional<TreeNode<T>> node = Optional.ofNullable(find(data, root));
        if (node.isPresent()) {
            System.out.println(data + " is found");
        } else {
            System.out.println(data + " is not found");
        }
    }

    private TreeNode<T> find(T data, TreeNode<T> node) {
        Optional<TreeNode<T>> nodeData = Optional.ofNullable(node);
        if (nodeData.isPresent()) {
            T value = nodeData.get().data;
            if (data.equals(value)) {
                return node;
            }
            if (data.compareTo(value) < 0) {
                return find(data, node.left);
            } else {
                return find(data, node.right);
            }
        }
        return null;
    }


    public boolean isTreeEmpty(){
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

    private int height(TreeNode<T> root) {
        if (root == null)
            return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    private int getcol(int h) {
        if (h == 1)
            return 1;
        return getcol(h - 1) + getcol(h - 1) + 1;
    }

    private void printTree(int[][] M, TreeNode<T> root, int col, int row, int height) {
        if (root == null)
            return;
        Object obj = root.data;
        M[row][col] = (int) obj;
        printTree(M, root.left, col - (int)Math.pow(2, height - 2), row + 1, height - 1);
        printTree(M, root.right, col + (int)Math.pow(2, height - 2), row + 1, height - 1);
    }

    public void TreePrinter() {
        int h = height(root);
        int col = getcol(h);
        int[][] M = new int[h][col];
        printTree(M, root, col / 2, 0, h);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < col; j++) {
                if (M[i][j] == 0)
                    System.out.print("  ");
                else
                    System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
    }


}
