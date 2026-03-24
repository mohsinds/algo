package com.mohsindev.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Quick interview-learning notes for BT/BST.
 *
 * Usage:
 * - Call `getTopQuestions()` to read concise Q/A list.
 * - Call `printTopQuestions()` for console-ready revision.
 *
 * Why this class:
 * - Keeps practical "what to revise" in one place.
 * - Useful for fast refresh before interviews.
 */
public class TreeInterviewGuide {

    public record InterviewQuestion(
            String title,
            String purpose,
            String approach,
            String complexity,
            String quickExample
    ) {}

    public List<InterviewQuestion> getTopQuestions() {
        List<InterviewQuestion> questions = new ArrayList<>();

        questions.add(new InterviewQuestion(
                "1) BST Search / Insert / Delete",
                "Core data-structure question. Tests recursion + pointer updates.",
                "Use BST property to go left/right. For delete with two children, replace with inorder successor.",
                "Avg O(log n), worst O(n) on skewed trees.",
                "Delete 5 in BST: find 5, replace with min of right subtree, then delete successor."
        ));

        questions.add(new InterviewQuestion(
                "2) Tree Traversals (Pre/In/Post/Level)",
                "Tests DFS/BFS understanding and recursion flow.",
                "DFS with recursion; Level order with queue.",
                "O(n) time, O(h) recursion stack or O(w) queue.",
                "Inorder of BST gives sorted values."
        ));

        questions.add(new InterviewQuestion(
                "3) Height and Diameter",
                "Classic optimization question from O(n^2) to O(n).",
                "Return pair (height, diameter) in one DFS.",
                "O(n) optimized.",
                "Diameter through node = leftHeight + rightHeight + 1."
        ));

        questions.add(new InterviewQuestion(
                "4) Validate BST",
                "Tests understanding beyond local parent-child checks.",
                "Either min/max constraints or inorder monotonic check.",
                "O(n) time, O(h) space.",
                "A node in right subtree must satisfy global range, not just parent relation."
        ));

        questions.add(new InterviewQuestion(
                "5) Lowest Common Ancestor (LCA)",
                "Very common for BT and BST variants.",
                "BT: recurse both sides. BST: move by value comparisons.",
                "BT O(n), BST O(h).",
                "In BST, for p=2 q=8 at root=6 => root is LCA."
        ));

        questions.add(new InterviewQuestion(
                "6) Subtree Check",
                "Tests structural matching and recursion composition.",
                "At each node, check identical; otherwise recurse left/right.",
                "Worst O(n*m), can be improved using serialization/hash.",
                "Check if [2,4,5] exists as exact subtree in bigger tree."
        ));

        questions.add(new InterviewQuestion(
                "7) Build BST from Unsorted Array",
                "Frequently asked when discussing preprocessing choices.",
                "Repeated insertion; balanced build can be done by sorting first.",
                "Insert method O(n log n) avg, O(n^2) worst; sorted+middle build O(n log n)+O(n).",
                "Input [5,1,7,3] => inorder [1,3,5,7]."
        ));

        questions.add(new InterviewQuestion(
                "8) Kth Smallest in BST",
                "Checks inorder reasoning + early stopping.",
                "Inorder traversal with counter until k.",
                "O(h + k) typical.",
                "3rd smallest in [1,2,3,4,5] is 3."
        ));

        questions.add(new InterviewQuestion(
                "9) Level-based Sums / Views",
                "BFS pattern used in many variants (right/left view, zigzag).",
                "Queue by levels; compute per-level aggregates.",
                "O(n).",
                "Right view: last node visited on each level."
        ));

        questions.add(new InterviewQuestion(
                "10) Balanced Tree Check",
                "Common follow-up after discussing performance.",
                "Postorder DFS returning height; fail fast when balance > 1.",
                "O(n).",
                "Height difference of left/right > 1 means unbalanced."
        ));

        return questions;
    }

    public void printTopQuestions() {
        for (InterviewQuestion q : getTopQuestions()) {
            System.out.println("--------------------------------------------------");
            System.out.println(q.title());
            System.out.println("Purpose: " + q.purpose());
            System.out.println("Approach: " + q.approach());
            System.out.println("Complexity: " + q.complexity());
            System.out.println("Example: " + q.quickExample());
        }
        System.out.println("--------------------------------------------------");
    }
}
