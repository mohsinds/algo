package com.mohsindev.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
//    public int[] tree = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};

    int idx = -1;

    public Node buildTree(int[] nodes) {
        idx++;
        if(nodes[idx] == -1)
            return null;

        Node newNode = new Node(nodes[idx]);
        newNode.left = buildTree(nodes);
        newNode.right = buildTree(nodes);
        return newNode;
    }

    public ArrayList<Integer> preorderNLR(Node node) {
        ArrayList<Integer> out = new ArrayList<>();
        preorderNLR(node, out);
        return out;
    }

    private void preorderNLR(Node node, ArrayList<Integer> out) {
        if (node == null) return;
        out.add(node.data);
        preorderNLR(node.left, out);
        preorderNLR(node.right, out);
    }

    public ArrayList<Integer> inorderLNR(Node node) {
        ArrayList<Integer> out = new ArrayList<>();
        inorderLNR(node, out);
        return out;
    }

    private void inorderLNR(Node node, ArrayList<Integer> out) {
        if (node == null) return;
        inorderLNR(node.left, out);
        out.add(node.data);
        inorderLNR(node.right, out);
    }

    public ArrayList<Integer> postorderLRN(Node node) {
        ArrayList<Integer> out = new ArrayList<>();
        postorderLRN(node, out);
        return out;
    }

    private void postorderLRN(Node node, ArrayList<Integer> out) {
        if (node == null) return;
        postorderLRN(node.left, out);
        postorderLRN(node.right, out);
        out.add(node.data);
    }

    public ArrayList<Integer> levelOrderBFS(Node node) {
        ArrayList<Integer> out = new ArrayList<>();
        if (node == null) return out;
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            out.add(cur.data);
            if (cur.left != null) q.add(cur.left);
            if (cur.right != null) q.add(cur.right);
        }
        return out;
    }

    public int countOfNodes(Node node) {
        if(node == null)
            return 0;

        int x = 0, y = 0;
        x = countOfNodes(node.left);
        y = countOfNodes(node.right);

        return x + y + 1;
    }

    public int sumOfNodes(Node node) {
        if(node == null)
            return 0;

        return sumOfNodes(node.left) + sumOfNodes(node.right) + node.data;
    }

    public int heightOfTree(Node node) {
        if(node == null)
            return 0;

        return Math.max(heightOfTree(node.left), heightOfTree(node.right)) + 1;

//        if(node == null)
//            return 0;
//
//        Queue<Node> queue = new LinkedList<>();
//        queue.add(node);
//        queue.add(null);
//        int height = 0;
//
//        while(!queue.isEmpty()) {
//            Node n = queue.poll();
//            if(n == null) {
//                height++;
//                if(queue.isEmpty())
//                    break;
//                else
//                    queue.add(n);
//            }
//            else {
//                if(n.left != null) queue.add(n.left);
//                if(n.right != null) queue.add(n.right);
//            }
//        }
//
//        return height;
    }

}
