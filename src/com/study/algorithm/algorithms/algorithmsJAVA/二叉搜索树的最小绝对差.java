package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

public class 二叉搜索树的最小绝对差 {
    @Test
    public void 二叉搜索树的最小绝对差() {
        //        TreeNode treeNode = new TreeNode(11);
        //        treeNode.left = new TreeNode(5);
        //        treeNode.right = new TreeNode(15);
        //        treeNode.left.left = new TreeNode(1);
        //        treeNode.left.right = new TreeNode(7);
        //        treeNode.left.right.right = new TreeNode(9);
        //        treeNode.right.right = new TreeNode(17);

        TreeNode treeNode = new TreeNode(0);
        treeNode.right = new TreeNode(10);
        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(14);
        treeNode.right.left.left = new TreeNode(2);
        //        int[] b = {5, 2, 6, 2, 3, 2};
        System.out.println("二叉搜索树的最小绝对差：" + getMinimumDifference(treeNode));
    }

    // 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
    //
    //
    //
    // 示例：
    //
    // 输入：
    //
    //   1
    //    \
    //     3
    //    /
    //   2
    //
    // 输出：
    // 1
    //
    // 解释：
    // 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
    //
    //
    // 提示：
    //
    // 树中至少有 2 个节点。
    // 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int getMinimumDifference(TreeNode root) {
        // 因为是二叉搜索树，所以中序遍历为递增序列
        // 用一个一位数组，保存前一个节点值和当前计算的最小差值，用中序遍历，依次计算最小差值，并更新前一个节点值
        // 注意第一个节点不参与结算只更新节点值

        int[] min = new int[2];
        min[0] = Integer.MIN_VALUE;
        min[1] = Integer.MAX_VALUE;
        getMinimumDifference(root, min);
        return min[1];
    }

    public void getMinimumDifference(TreeNode root, int[] min) {
        if (root == null) {
            return;
        }

        getMinimumDifference(root.left, min);
        min[1] = Math.min(Math.abs(root.val - min[0]), min[1]);
        if (min[1] < 0) {
            min[1] = Integer.MAX_VALUE;
        }
        min[0] = root.val;

        getMinimumDifference(root.right, min);
    }
}
