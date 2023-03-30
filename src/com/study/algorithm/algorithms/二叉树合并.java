package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

public class 二叉树合并 {
    @Test
    public void 二叉树合并() {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(3);
        treeNode.left.left = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(5);
        treeNode1.left = new TreeNode(3);
        treeNode1.right = new TreeNode(2);
        treeNode1.right.left = new TreeNode(4);
        treeNode1.left.right = new TreeNode(3);
        System.out.println("二叉树合并:" + mergeTrees(treeNode, treeNode1));
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 != null && t2 != null) {
            t1.val = t1.val + t2.val;
            t1.left = mergeTrees(t1.left, t2.left);
            t1.right = mergeTrees(t1.right, t2.right);
        } else if (t2 != null) {
            return t2;
        }

        return t1;
    }
}
