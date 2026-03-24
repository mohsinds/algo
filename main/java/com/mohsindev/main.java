package com.mohsindev;

import com.mohsindev.array.SubArraySum;
import com.mohsindev.tree.BinarySearchTree;
import com.mohsindev.tree.BinaryTree;
import com.mohsindev.tree.Node;
import com.mohsindev.tree.TreeInterviewGuide;

import static com.mohsindev.array.SubArraySum.kadaneSum;
import static com.mohsindev.array.SubArraySum.maxSubArraySum;

class Main {

    public static void testArrays() {

        int[] arr = {3,-4,5,4,-1,7,-8};

        SubArraySum.MaxSubArrayResult result = maxSubArraySum(arr);
        result.print();
        System.out.println("Max sum of subarray = " + result.max());

        SubArraySum.MaxSubArrayResult kadane = kadaneSum(arr);
        kadane.print();
        System.out.println("Kadaned sum of subarray = " + kadane.max());

    }

    public static void testBinaryTreeFromLevelOrder() {
        int[] tree = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTree binaryTree = new BinaryTree();
        Node root = binaryTree.buildTree(tree);
        System.out.println("Preorder (NLR): " + binaryTree.preorderNLR(root));
        System.out.println("Postorder (LRN): " + binaryTree.postorderLRN(root));
        System.out.println("Inorder (LNR): " + binaryTree.inorderLNR(root));
        System.out.println("Level Order (BFS): " + binaryTree.levelOrderBFS(root));
        System.out.println("Count nodes: " + binaryTree.countOfNodes(root));
        System.out.println("Sum of nodes: " + binaryTree.sumOfNodes(root));
        System.out.println("Height of tree: " + binaryTree.height(root));
        System.out.println("Diameter of tree: " + binaryTree.diameter(root));
        System.out.println("Diameter (optimized) of tree: " + binaryTree.optimizedDiameter(root));
        System.out.println("root node: " + root.data);

        System.out.println("--------------------------------");
        int[] tree2 = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        binaryTree.resetIdx();
        Node root2 = binaryTree.buildTree(tree2);

        int[] leftSubtree = {2, 4, -1, -1, 5, -1, -1};
        binaryTree.resetIdx();
        Node subRoot = binaryTree.buildTree(leftSubtree);
        System.out.println("Is left shape a subtree? " + binaryTree.isSubtree(root2, subRoot));

        int[] notASubtree = {9, 1, -1, -1};
        binaryTree.resetIdx();
        Node fake = binaryTree.buildTree(notASubtree);
        System.out.println("Is fake a subtree? " + binaryTree.isSubtree(root2, fake));

        System.out.println("--------------------------------");

        System.out.println("Sum of nodes at level 2: " + binaryTree.sumOfNodesAtLevel(root, 2));
    }

    public static void testBST() {
        int[] unsorted = {5, 3, 7, 1, 9, 4, 6, 2, 8};
        BinaryTree btUtils = new BinaryTree();
        BinarySearchTree bst = new BinarySearchTree();

        Node root = bst.buildFromUnsortedArray(unsorted);
        System.out.println("Unsorted input: " + java.util.Arrays.toString(unsorted));
        System.out.println("BST inorder (sorted order): " + btUtils.inorderLNR(root));
        System.out.println("BST preorder: " + btUtils.preorderNLR(root));
        System.out.println("BST level order: " + btUtils.levelOrderBFS(root));
        System.out.println("BST size: " + bst.size(root));
        System.out.println("BST min / max: " + bst.minValue(root) + " / " + bst.maxValue(root));
        System.out.println("isValidBST: " + bst.isValid(root));

        System.out.println("contains 4: " + bst.contains(root, 4));
        System.out.println("contains 99: " + bst.contains(root, 99));
        System.out.println("search 7 -> " + (bst.search(root, 7) != null ? "found" : "null"));

        root = bst.update(root, 4, 40);
        System.out.println("After update 4 -> 40, inorder: " + btUtils.inorderLNR(root));

        root = bst.delete(root, 40);
        System.out.println("After delete 40, inorder: " + btUtils.inorderLNR(root));

        Node balanced = bst.buildBalancedFromUnsortedArray(unsorted);
        System.out.println("Balanced BST from same array — height: " + btUtils.height(balanced)
                + ", inorder: " + btUtils.inorderLNR(balanced));
    }

    public static void testInterviewGuide() {
        TreeInterviewGuide guide = new TreeInterviewGuide();
        guide.printTopQuestions();
    }

    public static void main(String[] args) {
        testArrays();
        testBinaryTreeFromLevelOrder();
        testBST();
        testInterviewGuide();
    }
}