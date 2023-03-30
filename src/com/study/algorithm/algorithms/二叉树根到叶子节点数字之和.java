package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

public class 二叉树根到叶子节点数字之和 {
    // 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
    //
    // 例如，从根到叶子节点路径 1->2->3 代表数字 123。
    //
    // 计算从根到叶子节点生成的所有数字之和。
    //
    // 说明: 叶子节点是指没有子节点的节点。
    //
    // 示例 1:
    //
    // 输入: [1,2,3]
    //    1
    //   / \
    //  2   3
    // 输出: 25
    // 解释:
    // 从根到叶子节点路径 1->2 代表数字 12.
    // 从根到叶子节点路径 1->3 代表数字 13.
    // 因此，数字总和 = 12 + 13 = 25.
    // 示例 2:
    //
    // 输入: [4,9,0,5,1]
    //    4
    //   / \
    //  9   0
    //  / \
    // 5   1
    // 输出: 1026
    // 解释:
    // 从根到叶子节点路径 4->9->5 代表数字 495.
    // 从根到叶子节点路径 4->9->1 代表数字 491.
    // 从根到叶子节点路径 4->0 代表数字 40.
    // 因此，数字总和 = 495 + 491 + 40 = 1026.
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    @Test
    public void 二叉树根到叶子节点数字之和() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(2);
        treeNode.right.right = new TreeNode(2);
        treeNode.right.left = new TreeNode(2);
        treeNode.right.left.right = new TreeNode(2);
        treeNode.right.right.right = new TreeNode(5);
        treeNode.right.right.right.right = new TreeNode(4);

        /*List<Integer> list = new ArrayList<>();
        list.add(1, 1);*/

        System.out.println("二叉树根到叶子节点数字之和:" + sumNumbers(treeNode));
    }

    int result = 0;

    public int sumNumbers(TreeNode root) {
        // 深度优先
        // 递归，到叶子节点时，加上结果
        // 否则按照题意，当前数*10+当前节点的值，继续遍历子节点

        sumNumbers(root, 0);
        return result;
    }

    public void sumNumbers(TreeNode root, int curr) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            result += curr * 10 + root.val;
            return;
        }
        int next = curr * 10 + root.val;
        sumNumbers(root.left, next);
        sumNumbers(root.right, next);
    }
}
