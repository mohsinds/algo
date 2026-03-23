package com.mohsindev;

import com.mohsindev.array.SubArraySum;
import com.mohsindev.tree.BinaryTree;
import com.mohsindev.tree.Node;

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

    public static void main(String[] args) {
//        testArrays();
        int[] tree = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTree binaryTree = new BinaryTree();
        Node root = binaryTree.buildTree(tree);
        System.out.println("Preorder (NLR): " + binaryTree.preorderNLR(root));
        System.out.println("Postorder (LRN): " + binaryTree.postorderLRN(root));
        System.out.println("Inorder (LNR): " + binaryTree.inorderLNR(root));
        System.out.println("Level Order (BFS): " + binaryTree.levelOrderBFS(root));
        System.out.println("Count nodes: " + binaryTree.countOfNodes(root));
        System.out.println("Sum of nodes: " + binaryTree.sumOfNodes(root));
        System.out.println("Height of tree: " + binaryTree.heightOfTree(root));
        System.out.println("root node: " + root.data);
    }
}