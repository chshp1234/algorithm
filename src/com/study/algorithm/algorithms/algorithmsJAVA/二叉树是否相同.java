package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

public class 二叉树是否相同 {

    @Test
    public void 相同的树() {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(3);
        TreeNode treeNode1 = new TreeNode(5);
        treeNode1.left = new TreeNode(3);
        System.out.println("相同的树:" + isSameTree(treeNode, treeNode1));
    }
    /**
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     *
     * <p>如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     *
     * <p>示例 1:
     *
     * <p>输入: 1 1 / \ / \ 2 3 2 3
     *
     * <p>[1,2,3], [1,2,3]
     *
     * <p>输出: true 示例 2:
     *
     * <p>输入: 1 1 / \ 2 2
     *
     * <p>[1,2], [1,null,2]
     *
     * <p>输出: false 示例 3:
     *
     * <p>输入: 1 1 / \ / \ 2 1 1 2
     *
     * <p>[1,2,1], [1,1,2]
     *
     * <p>输出: false
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/same-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q == null) {

            return false;
        } else if (p == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
