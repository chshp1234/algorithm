package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 二叉树的后序遍历 {

    @Test
    public void 二叉树的后序遍历() {
        TreeNode treeNode = new TreeNode(3);
        treeNode.setLeft(new TreeNode(5));
        treeNode.setRight(new TreeNode(1));
        treeNode.getLeft().setLeft(new TreeNode(4));
        treeNode.getLeft().setRight(new TreeNode(2));
        treeNode.getLeft().getLeft().setLeft(new TreeNode(7));
        treeNode.getLeft().getLeft().setRight(new TreeNode(6));
        System.out.println("二叉树的后序遍历：" + postorderTraversalByStack(treeNode));
    }

    // 给定一个二叉树，返回它的 后序 遍历。
    //
    // 示例:
    //
    // 输入: [1,null,2,3]
    //   1
    //    \
    //     2
    //    /
    //   3
    //
    // 输出: [3,2,1]
    // 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();

        // 递归，深度优先遍历
        dfs(root, list);
        return list;
    }

    // 递归，深度优先遍历
    private void dfs(TreeNode treeNode, List<Integer> result) {
        // 如果节点为空，直接返回
        if (treeNode == null) {
            return;
        }

        // 如果左右子节点都为空，则说明遍历到叶子节点，加入结果集
        if (treeNode.left == null && treeNode.right == null) {
            result.add(treeNode.val);
            return;
        }

        // 继续遍历左子节点，右子节点，根节点
        dfs(treeNode.left, result);
        dfs(treeNode.right, result);
        result.add(treeNode.val);
    }

    public List<Integer> postorderTraversalByStack(TreeNode root) {

        List<Integer> list = new ArrayList<>();

        // 迭代，栈，先进后出
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root);

        TreeNode temp;
        while (!stack.isEmpty()) {
            temp = stack.pollFirst();

            // 如果当前节点为空，直接返回
            if (temp == null) {
                continue;
            }

            // 如果左右子节点都为空，则说明遍历到叶子节点，加入结果集
            if (temp.left == null && temp.right == null) {
                list.add(temp.val);
                continue;
            }

            // 加入根节点，右子节点，左子节点（如果是队列，则加入顺序相反）
            stack.offerFirst(temp);
            stack.offerFirst(temp.right);
            stack.offerFirst(temp.left);

            // 让当前节点左右子节点为空，则当遍历到此节点时才可进行判断并赋值
            temp.left = temp.right = null;
        }

        return list;
    }
}
