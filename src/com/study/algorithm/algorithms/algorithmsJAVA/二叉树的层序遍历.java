package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 二叉树的层序遍历 {

    @Test
    public void 二叉树的层序遍历() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.right = new TreeNode(5);
        treeNode.right.right = new TreeNode(4);

        /*List<Integer> list = new ArrayList<>();
        list.add(1, 1);*/

        System.out.println("二叉树的层序遍历:" + Arrays.toString(levelOrderⅡ(treeNode)));
    }

    public int[] levelOrderⅡ(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        int      index = 0;
        int      len;
        TreeNode temp;
        while (true) {
            len = list.size();
            if (len > index) {
                while (index < len) {
                    temp = list.get(index);
                    if (temp.left != null) {
                        list.add(temp.left);
                    }
                    if (temp.right != null) {
                        list.add(temp.right);
                    }
                    index++;
                }
            } else {
                index = 0;
                break;
            }
        }
        int[] result = new int[len];
        while (index < len) {
            result[index] = list.get(index++).val;
        }
        return result;
    }

    // 107. 二叉树的层次遍历 II
    // 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
    //
    // 例如：
    // 给定二叉树 [3,9,20,null,null,15,7],
    //
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    // 返回其自底向上的层次遍历为：
    //
    // [
    //  [15,7],
    //  [9,20],
    //  [3]
    // ]
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<List<Integer>> levelOrder1(TreeNode root) {

        /*List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }

        return ret;*/

        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> lists = new ArrayList<>();

        // 辅助队列容器
        Queue<TreeNode> containerHelper = new LinkedList<>();
        // 辅助层序容器
        Queue<Integer> tierHelper = new LinkedList<>();
        containerHelper.offer(root);
        tierHelper.add(1);

        int           tier;
        List<Integer> list;
        TreeNode      treeNode;

        // 迭代，tier标识当前层序
        while (!containerHelper.isEmpty()) {
            tier = tierHelper.poll();
            treeNode = containerHelper.poll();
            if (lists.size() < tier) {
                list = new ArrayList<>();
                // 在索引 0 的位置加入一维数组
                lists.add(0, list);
            } else {
                list = lists.get(0);
            }

            list.add(treeNode.val);
            if (treeNode.left != null) {
                containerHelper.offer(treeNode.left);
                tierHelper.offer(tier + 1);
            }
            if (treeNode.right != null) {
                containerHelper.offer(treeNode.right);
                tierHelper.offer(tier + 1);
            }
        }

        return lists;
    }

    // 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
    //
    //
    //
    // 示例：
    // 二叉树：[3,9,20,null,null,15,7],
    //
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    // 返回其层次遍历结果：
    //
    // [
    //  [3],
    //  [9,20],
    //  [15,7]
    // ]
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> lists = new ArrayList<>();

        // 递归，tier标识当前层序
        levelOrder(root, lists, 1);

        return lists;
    }

    public void levelOrder(TreeNode treeNode, List<List<Integer>> lists, int tier) {
        List<Integer> list;
        if (lists.size() < tier) {
            list = new ArrayList<>();
            lists.add(list);
        } else {
            list = lists.get(tier - 1);
        }

        list.add(treeNode.val);
        if (treeNode.left != null) {
            levelOrder(treeNode.left, lists, tier + 1);
        }
        if (treeNode.right != null) {
            levelOrder(treeNode.right, lists, tier + 1);
        }
    }
}
