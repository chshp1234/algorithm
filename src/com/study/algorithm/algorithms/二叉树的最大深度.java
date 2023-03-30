package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

public class 二叉树的最大深度 {

    @Test
    public void 二叉树的最大深度() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.right = new TreeNode(5);
        treeNode.right.right = new TreeNode(4);

        /*List<Integer> list = new ArrayList<>();
        list.add(1, 1);*/

        System.out.println("二叉树的最大深度:" + maxDepth(treeNode));
    }

    // 给定一个二叉树，找出其最大深度。
    //
    // 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
    //
    // 说明: 叶子节点是指没有子节点的节点。
    //
    // 示例：
    // 给定二叉树 [3,9,20,null,null,15,7]，
    //
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    // 返回它的最大深度 3 。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int maxDepth(TreeNode root) {
        // 如果根节点为null，返回0
        if (root == null) {
            return 0;
        }

        // 返回左右子树最大深度，并+1（自己）
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
