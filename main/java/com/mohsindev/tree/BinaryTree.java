package com.mohsindev.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Binary Tree (BT) utilities for a general tree (not necessarily ordered).
 *
 * Purpose:
 * - Use when tree nodes do NOT follow BST ordering rules.
 * - Covers traversal, shape-based checks, height/diameter, and level-based sums.
 *
 * How this class works:
 * - `buildTree` expects a preorder array with `-1` as null marker.
 * - Most operations are recursive DFS, while level operations use BFS queue.
 *
 * Quick example:
 * - Input: [1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1]
 * - Represents tree:
 *        1
 *      /   \
 *     2     3
 *    / \     \
 *   4   5     6
 *
 * Why use BT utilities:
 * - Interview questions on generic trees usually do not guarantee sorting.
 * - Traversals + diameter + subtree checks are common foundations.
 */
public class BinaryTree {
    // Stateful cursor used by preorder-with-null-marker builder.
    private int idx = -1;

    public void resetIdx() {
        idx = -1;
    }

    /**
     * Builds a binary tree from preorder array where -1 means null.
     *
     * Example:
     * - [1,2,-1,-1,3,-1,-1] -> 1 with left=2 and right=3.
     */
    public Node buildTree(int[] nodes) {
        idx++;
        if (nodes.length <= idx || nodes[idx] == -1)
            return null;

        Node newNode = new Node(nodes[idx]);
        newNode.left = buildTree(nodes);
        newNode.right = buildTree(nodes);
        return newNode;
    }
    public int[] toArray(Node node){
        ArrayList<Integer> output = new ArrayList<>();
        toArray(node,output);
        return output.stream().mapToInt(Integer::intValue).toArray();
    }

    private void toArray(Node node, ArrayList<Integer> out){
        if (node == null) {
            out.add(-1);
            return;
        }
        out.add(node.data);
        if (node.left != null) toArray(node.left, out); else toArray(null, out);
        if (node.right != null) toArray(node.right, out); else toArray(null, out);

    }

    /**
     * Checks exact same structure + values.
     * Used as helper for subtree checks.
     */
    public boolean isIdentical(Node node, Node subNode) {
        if (node == null && subNode == null)
            return true;

        if (node == null || subNode == null)
            return false;

        if (node.data == subNode.data) {
            return isIdentical(node.left, subNode.left) && isIdentical(node.right, subNode.right);
        }
        return false;
    }

    /**
     * Returns true if `subNode` exists anywhere inside `node`.
     * Time can be O(n*m) in worst case; good for understanding recursion first.
     */
    public boolean isSubtree(Node node, Node subNode) {
        if (subNode == null)
            return true;
        if (node == null)
            return false;
        if (isIdentical(node, subNode))
            return true;
        return isSubtree(node.left, subNode) || isSubtree(node.right, subNode);
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

    /**
     * Level-order based question:
     * - Compute sum at each level, sort descending, return k-th largest level sum.
     */
    public int kthLargestSum(Node node, int k) {
        if (node == null) return -1;

        ArrayList<Integer> levelSums = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                levelSum += current.data;
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
            levelSums.add(levelSum);
        }

        levelSums.sort((a, b) -> b - a);

        if (k <= 0 || k > levelSums.size()) return -1;

        return levelSums.get(k - 1);
    }
    public int sumOfNodesAtLevel(Node node, int k) {
        if (node == null) return 0;
        int sum = 0;
        int level = 0;
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);

        while (!q.isEmpty()) {
            int size = q.size();
            if (level == k) {
                for (int i = 0; i < size; i++) {
                    Node curr = q.poll();
                    sum += curr.data;
                }
                break;
            } else {
                for (int i = 0; i < size; i++) {
                    Node curr = q.poll();
                    if (curr.left != null) q.add(curr.left);
                    if (curr.right != null) q.add(curr.right);
                }
            }
            level++;
        }
        return sum;
    }

    public int countOfNodes(Node node) {
        if (node == null)
            return 0;

        int x = countOfNodes(node.left);
        int y = countOfNodes(node.right);

        return x + y + 1;
    }

    public int sumOfNodes(Node node) {
        if (node == null)
            return 0;

        return sumOfNodes(node.left) + sumOfNodes(node.right) + node.data;
    }

    /**
     * O(n^2) diameter approach:
     * - For each node, compute left/right diameter and height.
     */
    public int diameter(Node node) {
        if (node == null)
            return 0;
        int leftDiameter = diameter(node.left);
        int rightDiameter = diameter(node.right);

        int height = height(node.left) + height(node.right) + 1;
        return Math.max(Math.max(leftDiameter, rightDiameter), height);

    }

    /** Helper object for O(n) optimized diameter. */
    class DiameterInfo {
        public int height;
        public int diameter;

        public DiameterInfo(int height, int diameter) {
            this.height = height;
            this.diameter = diameter;
        }

    }

    public int optimizedDiameter(Node node) {
        DiameterInfo d = this.calcOptimizedDiameter(node);
        return d.diameter;
    }

    /**
     * O(n) diameter approach:
     * - Returns both height and best diameter in one traversal.
     */
    public DiameterInfo calcOptimizedDiameter(Node node) {
        if (node == null)
            return new DiameterInfo(0, 0);

        DiameterInfo leftDiameter = calcOptimizedDiameter(node.left);
        DiameterInfo rightDiameter = calcOptimizedDiameter(node.right);

        int myHeight = Math.max(leftDiameter.height, rightDiameter.height) + 1;

        int diameter1 = leftDiameter.diameter;
        int diameter2 = rightDiameter.diameter;
        int diameter3 = leftDiameter.height + rightDiameter.height + 1;

        int myDiameter = Math.max(Math.max(diameter1, diameter2), diameter3);

        return new DiameterInfo(myHeight, myDiameter);

    }

    /** Height = max depth in nodes (root-only tree has height 1). */
    public int height(Node node) {
        if (node == null)
            return 0;

        return Math.max(height(node.left), height(node.right)) + 1;
    }

}
