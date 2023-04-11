package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

//给定一棵二叉搜索树，请找出其中第k大的节点。
//
// 
//
//示例 1:
//
//输入: root = [3,1,4,null,2], k = 1
//   3
//  / \
// 1   4
//  \
//   2
//输出: 4
//示例 2:
//
//输入: root = [5,3,6,2,4,null,null,1], k = 3
//       5
//      / \
//     3   6
//    / \
//   2   4
//  /
// 1
//输出: 4
// 
//
//限制：
//
//1 ≤ k ≤ 二叉搜索树元素个数
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 二叉搜索树的第k大节点 {
    @Test
    public void 二叉搜索树的第k大节点() {

        TreeNode treeNode = new TreeNode(0);
        treeNode.right = new TreeNode(10);
        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(14);
        treeNode.right.left.left = new TreeNode(2);
        //        int[] b = {5, 2, 6, 2, 3, 2};
        System.out.println("二叉搜索树的第k大节点：" + kthLargest(treeNode, 3));
    }

    int kth, result;

    public int kthLargest(TreeNode root, int k) {

        //逆中序遍历
        //二叉搜索树的中序遍历为递增序列，则逆中序遍历为递减数列
        //即找出逆中序遍历中第k个节点的值
        kth = k;
        kthVal(root);
        return result;

    }

    public void kthVal(TreeNode root) {

        if (root == null) {
            return;
        }

        //先遍历右子树
        kthVal(root.right);

        //若已找到值则返回
        if (kth == 0) {
            return;
        }

        //计数kth-1
        kth--;
        //若kth=0，则说明遍历到当前第k大的值，赋值给结果result，返回
        if (kth == 0) {
            result = root.val;
            return;
        }
        //若没找到，则再遍历左子树
        kthVal(root.left);
    }
}
