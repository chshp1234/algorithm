package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
//
//例如：
//给定二叉树 [3,9,20,null,null,15,7],
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
//返回锯齿形层序遍历如下：
//
//[
//  [3],
//  [20,9],
//  [15,7]
//]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 二叉树的锯齿形层序遍历 {
    @Test
    public void 二叉树的锯齿形层序遍历() {
        TreeNode treeNode = new TreeNode(0);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(2);
        treeNode.right.left = new TreeNode(3);
        treeNode.right.right = new TreeNode(4);
        treeNode.right.left.right = new TreeNode(5);
        treeNode.right.right.right = new TreeNode(6);
        treeNode.right.right.right.right = new TreeNode(7);

        /*List<Integer> list = new ArrayList<>();
        list.add(1, 1);*/

        System.out.println("二叉树的锯齿形层序遍历:" + zigzagLevelOrder(treeNode));
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        //广度优先搜索

        //偶数层正序遍历，奇数层逆序遍历
        //可使用双端队列：
        //偶数层时，依次获取队列头部，并加入左节点和右节点
        //奇数层时，依次获取队列尾部，并加入右节点和左节点

        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode>     temp   = new LinkedList<>();
        temp.add(root);

        List<Integer> item;

        boolean up = true;
        while (!temp.isEmpty()) {
            item = new ArrayList<>();
            int size = temp.size();
            if (up) {
                for (int i = 0; i < size; i++) {
                    TreeNode node = temp.pollFirst();

                    if (node.left != null) {
                        temp.offerLast(node.left);
                    }
                    if (node.right != null) {
                        temp.offer(node.right);
                    }
                    item.add(node.val);
                }

            } else {
                for (int i = 0; i < size; i++) {
                    TreeNode node = temp.pollLast();
                    if (node.right != null) {
                        temp.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        temp.offerFirst(node.left);
                    }
                    item.add(node.val);
                }
            }
            up = !up;
            result.add(item);
        }
        return result;
    }
}
