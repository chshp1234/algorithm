package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

public class 二叉搜索树转换为累加树 {

    @Test
    public void 二叉搜索树转换为累加树() {
        TreeNode treeNode = new TreeNode(2);
        treeNode.left = new TreeNode(0);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(-4);
        treeNode.left.right = new TreeNode(1);
        //        int[] b = {5, 2, 6, 2, 3, 2};
        System.out.println("二叉搜索树转换为累加树：" + convertBST(treeNode));
    }

    public TreeNode convertBST(TreeNode root) {

        // 二叉搜索树，逆中序遍历，输出结果为从大到小
        // 所以我们可以根据，右-根-左的顺序进行遍历，累计计算出从大到小的值
        if (root != null) {
            accumulative(root, 0);
        }
        return root;
    }

    public int accumulative(TreeNode root, int sum) {

        // 如果右子树不为空，则计算右子树的累加值
        if (root.right != null) {
            sum = accumulative(root.right, sum);
        }

        // 根节点值为：右子树累加值+当前节点值
        sum = sum + root.val;
        root.val = sum;

        // 如果左子树不为空，则计算左子树的累加值
        if (root.left != null) {
            sum = accumulative(root.left, root.val);
        }

        // 返回累加值
        return sum;
    }
}
