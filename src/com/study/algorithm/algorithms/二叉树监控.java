package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

@Deprecated
public class 二叉树监控 {

    @Test
    public void 二叉树监控() {
        TreeNode treeNode = new TreeNode(0);
        treeNode.left = new TreeNode(0);
        treeNode.left.left = new TreeNode(0);
        treeNode.left.left.left = new TreeNode(0);
        treeNode.left.right = new TreeNode(0);
        treeNode.left.right.left = new TreeNode(0);
        treeNode.left.right.left.left = new TreeNode(0);
        treeNode.left.right.right = new TreeNode(0);
        treeNode.left.right.right.right = new TreeNode(0);
        treeNode.right = new TreeNode(0);
        treeNode.right.left = new TreeNode(0);
        treeNode.right.right = new TreeNode(0);

        //        TreeNode treeNode = new TreeNode(0);
        //        treeNode.left = new TreeNode(0);
        //        treeNode.right = new TreeNode(0);
        //        treeNode.right.right = new TreeNode(0);

        System.out.println("二叉树监控:" + minCameraCover(treeNode));
    }

    public int[] defaultResult = new int[] {1, 1};

    public int minCameraCover(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 1;
        }

        int[] result = dp(root);
        return Math.min(result[0], result[1]);
    }

    // 当前节点选中和未选中时的数量
    public int[] dp(TreeNode treeNode) {
        if (treeNode == null) {
            return null;
        }
        if (treeNode.left == null && treeNode.right == null) {
            return defaultResult;
        }

        int[] left = dp(treeNode.left);
        int[] right = dp(treeNode.right);

        int[] result = new int[2];

        if (left == null) {
            result[0] = right[1] + 1;
            result[1] = right[0];
        } else if (right == null) {
            result[0] = left[1] + 1;
            result[1] = left[0];
        } else {
            result[0] = left[1] + right[1] + 1;
            result[1] =
                    Math.min(Math.min(left[0] + right[0], left[0] + right[1]), left[1] + right[0]);
        }

        if (left == defaultResult) {
            result[0]--;
        }
        if (right == defaultResult) {
            result[0]--;
        }

        return result;
    }
}
