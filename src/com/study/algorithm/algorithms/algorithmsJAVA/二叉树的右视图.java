package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 二叉树的右视图 {

    @Test
    public void 二叉树的右视图() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.right = new TreeNode(5);
        treeNode.right.right = new TreeNode(4);

        /*List<Integer> list = new ArrayList<>();
        list.add(1, 1);*/

        System.out.println("二叉树的右视图:" + rightSideView(treeNode));
    }
    /**
     * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     *
     * <p>示例:
     *
     * <p>输入: [1,2,3,null,5,null,4] 输出: [1, 3, 4] 解释:
     *
     * <p>1 <--- / \ 2 3 <--- \ \ 5 4 <---
     */
    /*public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int counter = 0, tier = 1, nullCounter = 0;

        while (true) {

            if (queue.peek() != null) {
                if (list.size() < tier) {
                    list.add(queue.peek().val);
                } else {
                    list.set(tier - 1, queue.peek().val);
                }

                queue.offer(queue.peek().left);
                queue.offer(queue.poll().right);
            } else {
                nullCounter++;
                if (nullCounter == (1 << (tier - 1))) {
                    break;
                }
                queue.poll();
                queue.offer(null);
                queue.offer(null);
            }

            counter++;
            if (counter == (1 << (tier - 1))) {
                counter = 0;
                nullCounter = 0;
                tier += 1;
            }
        }

        return list;
    }*/
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        list.add(root.val);
        rightSideView(root.left, 2, list);
        rightSideView(root.right, 2, list);
        return list;
    }

    public void rightSideView(TreeNode node, int tier, List<Integer> list) {
        if (node == null) {
            return;
        }
        if (list.size() < tier) {
            list.add(node.val);
        } else {
            list.set(tier - 1, node.val);
        }

        rightSideView(node.left, ++tier, list);
        rightSideView(node.right, tier, list);
    }
}
