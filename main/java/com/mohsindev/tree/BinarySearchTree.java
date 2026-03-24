package com.mohsindev.tree;

import java.util.Arrays;

/**
 * Binary Search Tree (BST) focused operations.
 *
 * Purpose:
 * - Use when keys are ordered: left < node <= right (this class inserts duplicates to right).
 * - Gives faster average search/insert/delete than plain Binary Tree.
 *
 * How this class works:
 * - Build from unsorted array by repeated insert.
 * - Search/delete rely on BST ordering to skip half the tree on average.
 *
 * Quick example:
 * - Input: [5, 3, 7, 1, 9]
 * - Inorder output: [1, 3, 5, 7, 9] (sorted)
 *
 * Important interview info:
 * - Average time: O(log n) for search/insert/delete.
 * - Worst case (skewed): O(n), so balancing matters.
 */
public class BinarySearchTree {

    /**
     * Builds BST by inserting each value in the given order.
     * Why useful: keeps implementation simple and mirrors interview approach.
     */
    public Node buildFromUnsortedArray(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        Node root = null;
        for (int value : values) {
            root = insert(root, value);
        }
        return root;
    }

    /**
     * Builds near-balanced BST:
     * - Sort values
     * - Pick middle as root recursively
     */
    public Node buildBalancedFromUnsortedArray(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        int[] sorted = values.clone();
        Arrays.sort(sorted);
        return buildFromSortedRange(sorted, 0, sorted.length - 1);
    }

    private Node buildFromSortedRange(int[] sorted, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        int mid = (lo + hi) / 2;
        Node root = new Node(sorted[mid]);
        root.left = buildFromSortedRange(sorted, lo, mid - 1);
        root.right = buildFromSortedRange(sorted, mid + 1, hi);
        return root;
    }

    /**
     * Insert key in BST.
     * Duplicate handling in this project: duplicates are inserted to right subtree.
     */
    public Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (key < root.data) {
            root.left = insert(root.left, key);
        } else {
            root.right = insert(root.right, key);
        }
        return root;
    }

    /**
     * Search key using BST property.
     * Returns node if found, null otherwise.
     */
    public Node search(Node root, int key) {
        if (root == null || root.data == key) {
            return root;
        }
        if (key < root.data) {
            return search(root.left, key);
        }
        return search(root.right, key);
    }

    public boolean contains(Node root, int key) {
        return search(root, key) != null;
    }

    /**
     * "Edit" operation on key-based BST:
     * - delete old key
     * - insert new key
     */
    public Node update(Node root, int oldKey, int newKey) {
        if (!contains(root, oldKey)) {
            return root;
        }
        root = delete(root, oldKey);
        return insert(root, newKey);
    }

    /**
     * Delete key from BST.
     * Cases:
     * 1) leaf node
     * 2) one child
     * 3) two children -> replace with inorder successor.
     */
    public Node delete(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.data) {
            root.left = delete(root.left, key);
        } else if (key > root.data) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }

            Node successor = minNode(root.right);
            root.data = successor.data;
            root.right = delete(root.right, successor.data);
        }

        return root;
    }

    public Node minNode(Node root) {
        if (root == null) {
            return null;
        }
        Node curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    public Node maxNode(Node root) {
        if (root == null) {
            return null;
        }
        Node curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }

    public Integer minValue(Node root) {
        Node node = minNode(root);
        return node == null ? null : node.data;
    }

    public Integer maxValue(Node root) {
        Node node = maxNode(root);
        return node == null ? null : node.data;
    }

    public int size(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + size(root.left) + size(root.right);
    }

    /**
     * Validation by inorder monotonicity.
     * Since duplicates are inserted to right, non-decreasing inorder is accepted.
     */
    public boolean isValid(Node root) {
        long[] last = {Long.MIN_VALUE};
        return isValidInorder(root, last);
    }

    private boolean isValidInorder(Node node, long[] last) {
        if (node == null) {
            return true;
        }
        if (!isValidInorder(node.left, last)) {
            return false;
        }
        if (node.data < last[0]) {
            return false;
        }
        last[0] = node.data;
        return isValidInorder(node.right, last);
    }
}
