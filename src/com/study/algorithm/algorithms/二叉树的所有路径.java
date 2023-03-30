package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 二叉树的所有路径 {
    @Test
    public void 二叉树的所有路径() {
        TreeNode treeNode = new TreeNode(37);
        treeNode.left = new TreeNode(-34);
        treeNode.right = new TreeNode(-48);
        treeNode.left.right = new TreeNode(-100);
        treeNode.right.left = new TreeNode(-100);
        treeNode.right.right = new TreeNode(48);
        treeNode.right.right.left = new TreeNode(-54);
        treeNode.right.right.left.left = new TreeNode(-71);
        treeNode.right.right.left.right = new TreeNode(-22);
        treeNode.right.right.left.right.right = new TreeNode(8);

        /*List<Integer> list = new ArrayList<>();
        list.add(1, 1);*/

        System.out.println("二叉树的所有路径:" + binaryTreePaths(treeNode));
    }

    public List<String> binaryTreePaths(TreeNode root) {

        // 回溯，深度优先遍历

        List<String> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        backtrack(result, temp, root);

        return result;
    }

    private void backtrack(List<String> result, List<Integer> temp, TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        if (treeNode.left == null && treeNode.right == null) {
            // 如果左右子树为空，则遍历到叶子节点，加入该跳路径
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0, l = temp.size(); i < l; i++) {
                stringBuilder.append(temp.get(i));
                stringBuilder.append('-');
                stringBuilder.append('>');
            }
            stringBuilder.append(treeNode.val);
            result.add(stringBuilder.toString());
            return;
        }

        // 加入该节点
        temp.add(treeNode.val);
        backtrack(result, temp, treeNode.left);
        backtrack(result, temp, treeNode.right);
        // 回溯，删除该节点
        temp.remove(temp.size() - 1);
    }
}
