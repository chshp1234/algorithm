package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.LinkedHashMap;

public class 二叉树翻转 {

    @Test
    public void 二叉树翻转() {
        TreeNode treeNode = new TreeNode(4);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(7);
        treeNode.left.left = new TreeNode(1);
        treeNode.left.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(6);
        treeNode.right.right = new TreeNode(9);

        /*List<Integer> list = new ArrayList<>();
        list.add(1, 1);*/

        System.out.println("二叉树翻转:" + invertTree(treeNode));
    }

    // 翻转一棵二叉树。
    //
    // 示例：
    //
    // 输入：
    //
    //      4
    //    /   \
    //   2     7
    //  / \   / \
    // 1   3 6   9
    // 输出：
    //
    //      4
    //    /   \
    //   7     2
    //  / \   / \
    // 9   6 3   1
    // 备注:
    // 这个问题是受到 Max Howell 的 原问题 启发的 ：
    //
    // 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/invert-binary-tree
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public TreeNode invertTree(TreeNode root) {

        // 翻转二叉树，自顶向下翻转，先翻转根节点的左右子节点，再翻转左右子节点中的各自子节点，继续递归翻转子节点的子节点
        // 节点不为空才进行操作
        if (root != null) {
            // 交换左右子节点
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;

            // 递归左右子节点，交换子节点的左右子节点
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }

    public TreeNode invertTreeByBottomToUp(TreeNode root) {
        // 翻转二叉树，自底向上，先翻转叶子节点，再向上反正叶子节点的父节点，继续递归翻转直到翻转到根节点
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
