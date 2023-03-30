package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

public class 平衡二叉树 {
    @Test
    public void 平衡二叉树() {
        TreeNode treeNode = new TreeNode(3);
        treeNode.setLeft(new TreeNode(5));
        treeNode.setRight(new TreeNode(1));
        treeNode.getLeft().setLeft(new TreeNode(4));
        treeNode.getLeft().setRight(new TreeNode(2));
        treeNode.getLeft().getLeft().setLeft(new TreeNode(7));
        treeNode.getLeft().getLeft().setRight(new TreeNode(6));
        //        int[] b = {5, 2, 6, 2, 3, 2};
        System.out.println("平衡二叉树：" + isBalanced(treeNode));
    }

    // 给定一个二叉树，判断它是否是高度平衡的二叉树。
    //
    // 本题中，一棵高度平衡二叉树定义为：
    //
    // 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
    //
    // 示例 1:
    //
    // 给定二叉树 [3,9,20,null,null,15,7]
    //
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    // 返回 true 。
    //
    // 示例 2:
    //
    // 给定二叉树 [1,2,2,3,3,null,null,4,4]
    //
    //       1
    //      / \
    //     2   2
    //    / \
    //   3   3
    //  / \
    // 4   4
    // 返回 false 。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/balanced-binary-tree
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean isBalanced(TreeNode root) {
        int[] isBalanced = isBalance(root);

        // 平衡二叉树：左右子树高度相差不超过1，并且左右子树也是平衡二叉树

        return isBalanced[0] == 1;
    }

    // 返回数组，第一位表示该二叉树是否是平衡二叉树，第二位表示此树的高度
    private int[] isBalance(TreeNode treeNode) {
        if (treeNode == null) {
            return new int[] {1, 0};
        }

        // 获取左右子树数据
        int[] left = isBalance(treeNode.left);
        int[] right = isBalance(treeNode.right);

        // 若左右子树都是平衡二叉树，且左右子树的高度相差不超过1，则此树也是一个平衡二叉树
        if (left[0] == 1 && right[0] == 1 && Math.abs(left[1] - right[1]) <= 1) {
            return new int[] {1, Math.max(left[1], right[1]) + 1};
        } else {
            return new int[] {0, 0};
        }
    }
}
