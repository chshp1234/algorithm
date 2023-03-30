package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

public class 验证二叉搜索树 {

    @Test
    public void 验证二叉搜索树() {
        Integer[] a = {24, -60, null, -60, -6};
        TreeNode treeNode = new TreeNode(3);
        treeNode.setLeft(new TreeNode(1));
        treeNode.setRight(new TreeNode(5));
        treeNode.getLeft().setLeft(new TreeNode(0));
        treeNode.getLeft().setRight(new TreeNode(2));
        //        treeNode.getLeft().getLeft().setLeft(new TreeNode(7));
        //        treeNode.getLeft().getLeft().setRight(new TreeNode(6));
        treeNode.getLeft().getRight().setRight(new TreeNode(3));
        //        int[] b = {5, 2, 6, 2, 3, 2};
        System.out.println("验证二叉搜索树：" + isValidBST(treeNode));
    }

    // 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
    //
    // 假设一个二叉搜索树具有如下特征：
    //
    // 节点的左子树只包含小于当前节点的数。
    // 节点的右子树只包含大于当前节点的数。
    // 所有左子树和右子树自身必须也是二叉搜索树。
    // 示例 1:
    //
    // 输入:
    //    2
    //   / \
    //  1   3
    // 输出: true
    // 示例 2:
    //
    // 输入:
    //    3
    //   / \
    //  1   5
    //  /\  /\
    //  0 2 4 6
    // 输出: false
    // 解释: 输入为: [5,1,4,null,null,3,6]。
    //      根节点的值为 5 ，但是其右子节点值为 4 。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode temp = root;
        while (temp != null) {
            if ((temp.left == null) || (temp.left.val < temp.val)) {
                temp = temp.left;
            } else {
                return false;
            }
        }
        temp = root;
        while (temp != null) {
            if ((temp.right == null) || (temp.right.val > temp.val)) {
                temp = temp.right;
            } else {
                return false;
            }
        }

        // 深度优先遍历，
        // 判断左子树的最大值比根节点小，
        // 判断右子树的最小值比根节点大。
        return isValidBSTLeftMax(root.left, root.val) && isValidBSTRightMin(root.right, root.val);
    }
    // 判断左子树的最大值比根节点小
    public boolean isValidBSTLeftMax(TreeNode root, int rootVal) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }

        TreeNode temp = root;
        while (temp != null) {
            if ((temp.right == null) || (temp.right.val > temp.val && temp.right.val < rootVal)) {
                temp = temp.right;
            } else {
                return false;
            }
        }

        return isValidBSTLeftMax(root.left, root.val) && isValidBSTRightMin(root.right, root.val);
    }
    // 判断右子树的最小值比根节点大
    public boolean isValidBSTRightMin(TreeNode root, int rootVal) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }

        TreeNode temp = root;
        while (temp != null) {
            if ((temp.left == null) || (temp.left.val < temp.val && temp.left.val > rootVal)) {
                temp = temp.left;
            } else {
                return false;
            }
        }

        return isValidBSTLeftMax(root.left, root.val) && isValidBSTRightMin(root.right, root.val);
    }
}
