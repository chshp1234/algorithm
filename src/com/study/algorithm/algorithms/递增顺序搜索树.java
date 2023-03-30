package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

public class 递增顺序搜索树 {
    @Test
    public void 递增顺序搜索树() {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(3);
        treeNode.left.left = new TreeNode(2);
        treeNode.left.right = new TreeNode(4);
        treeNode.left.left.left = new TreeNode(1);
        treeNode.right = new TreeNode(6);
        treeNode.right.right = new TreeNode(8);
        treeNode.right.right.left = new TreeNode(7);
        treeNode.right.right.right = new TreeNode(9);
        //        int[] b = {5, 2, 6, 2, 3, 2};
        reorder(treeNode);
        System.out.println("递增顺序搜索树：" + resNode);
    }

    //上一个节点
    private TreeNode resNode;

    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummyNode = new TreeNode(-1);
        resNode = dummyNode;
        inorder(root);
        return dummyNode.right;
    }

    public void inorder(TreeNode node) {
        //中序遍历时改变指针
        //创建虚拟头结点
        if (node == null) {
            return;
        }
        inorder(node.left);

        // 在中序遍历的过程中修改节点指向
        resNode.right = node;
        node.left = null;
        //使得该节点成为“上一个父节点”
        resNode = node;

        inorder(node.right);
    }

    public void reorder(TreeNode node) {
        //逆中序遍历
        //逆中序遍历最后输出的即为根节点
        if (node == null) {
            return;
        }
        reorder(node.right);

        //在中序遍历的过程中修改节点指向
        node.right = resNode;
        //使得该节点成为“上一个父节点”
        resNode = node;

        reorder(node.left);

        node.left = null;
    }

}
