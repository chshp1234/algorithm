package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class 二叉树的最小深度 {

    @Test
    public void 二叉树的最大深度() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(2);
        treeNode.right.right = new TreeNode(2);
        treeNode.right.left = new TreeNode(2);
        treeNode.right.left.right = new TreeNode(2);
        treeNode.right.right.right = new TreeNode(5);
        treeNode.right.right.right.right = new TreeNode(4);

        /*List<Integer> list = new ArrayList<>();
        list.add(1, 1);*/

        System.out.println("二叉树的最大深度:" + minDepthByBFS(treeNode));
    }

    // 给定一个二叉树，找出其最小深度。
    //
    // 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
    //
    // 说明: 叶子节点是指没有子节点的节点。
    //
    // 示例:
    //
    // 给定二叉树 [3,9,20,null,null,15,7],
    //
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    // 返回它的最小深度  2.
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int minDepthByBFS(TreeNode root) {

        // 广度优先搜索：
        // 二叉树的层次遍历，遍历到第一个左右子节点都为空的叶子节点时，当前的深度即为最小深度。所以我们只要在层次遍历中记录当前层的深度即可。

        // 计算层的深度：
        // 思路一：用一个队列，一一对应每个节点的深度
        // 思路二：设计一个数据格式，一个字段对应于当前的节点，一个字段对应于当前节点的深度
        // 思路三：在队列中加入一个标记，每当遍历到此标记时，说明上一层已经遍历完，开始遍历下一层的节点，则层数+1即可

        // 前置条件
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        Queue<TreeNode> deque = new LinkedList<>();
        int depth = 1;

        // 为方便计算层的深度，这里加入根节点当作标记，我们规定，每次遍历到根节点时，即开始下一层的遍历，并重新把根节点加入队列中。
        deque.offer(root);

        deque.offer(root.left);
        deque.offer(root.right);

        TreeNode temp;
        while (!deque.isEmpty()) {
            temp = deque.poll();

            // 如果遍历到标记节点，则层数+1，并把标记节点重新入队
            if (temp == root) {
                depth++;
                deque.offer(root);
            } else if (temp != null) {
                // 如果当前节点左右子节点都为空，则表明是一个叶子节点，直接返回
                if (temp.left == null && temp.right == null) {
                    break;
                } else {
                    // 继续加入左右子节点
                    deque.offer(temp.left);
                    deque.offer(temp.right);
                }
            }
        }

        return depth;
    }

    public int minDepthByDFS(TreeNode root) {

        // 深度优先搜索：
        // 使用深度优先搜索时，需要注意左右节点都为空，都不为空，和一个为空的情况

        if (root == null) {
            return 0;
        }

        return minDepthOfDfs(root, 1);
    }

    private int minDepthOfDfs(TreeNode treeNode, int depth) {

        // 根节点为空，或左右子节点都为空时，直接返回当前的深度
        if (treeNode == null || (treeNode.left == null && treeNode.right == null)) {
            return depth;
        }

        // 如果左右子节点都不为空，计算左右子节点的最小深度
        if (treeNode.left != null && treeNode.right != null) {
            return Math.min(
                    minDepthOfDfs(treeNode.left, depth + 1),
                    minDepthOfDfs(treeNode.right, depth + 1));
        }

        // 如果左右子节点有一个为空，另一个不为空，因为此节点不是叶子节点，所以计算左右子节点中的最大深度
        else {
            return Math.max(
                    minDepthOfDfs(treeNode.left, depth + 1),
                    minDepthOfDfs(treeNode.right, depth + 1));
        }
    }
}
