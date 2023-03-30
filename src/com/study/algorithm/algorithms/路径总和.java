package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

public class 路径总和 {

    @Test
    public void 路径总和() {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(4);
        treeNode.right = new TreeNode(8);
        treeNode.left.left = new TreeNode(11);
        treeNode.right.left = new TreeNode(13);
        treeNode.right.right = new TreeNode(4);
        treeNode.right.right.right = new TreeNode(1);
        treeNode.right.left.right = new TreeNode(1);
        treeNode.left.left.left = new TreeNode(7);
        treeNode.left.left.right = new TreeNode(2);
        System.out.println("路径总和：" + hasPathSum(treeNode, 22));
        /*List<Integer> list = new ArrayList<>();
        list.add(1, 1);*/

    }

    // 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
    //
    // 说明: 叶子节点是指没有子节点的节点。
    //
    // 示例:
    // 给定如下二叉树，以及目标和 sum = 22，
    //
    //              5
    //             / \
    //            4   8
    //           /   / \
    //          11  13  4
    //         /  \      \
    //        7    2      1
    // 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/path-sum
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean hasPathSum(TreeNode root, int sum) {
        return countPathSum(root, 0, sum);
    }

    // 深度优先遍历，每遍历到一个非叶子节点，对当前节点数值进行累加，直到到达叶子节点时，判断累加值是否等于目标值
    public boolean countPathSum(TreeNode root, int currentSum, int targetSum) {

        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return (currentSum + root.val) == targetSum;
        }

        return countPathSum(root.left, currentSum + root.val, targetSum)
                || countPathSum(root.right, currentSum + root.val, targetSum);
    }
}
