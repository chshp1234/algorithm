package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 二叉树的层次遍历II {

    @Test
    public void 二叉树的层次遍历II() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.right = new TreeNode(5);
        treeNode.left.right.right = new TreeNode(5);
        treeNode.right.right = new TreeNode(4);

        /*List<Integer> list = new ArrayList<>();
        list.add(1, 1);*/

        System.out.println("二叉树的层次遍历II:" + levelOrderBottom(treeNode));
    }

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // 深度优先搜索方式：
        // 思路：返回的结果列表大小就为此树的高度，且最底层的节点存储在列表中最前面，我们可以根据当前层理应在结果列表中的位置来进行遍历添加。
        // 1.结果集若当前层数大于结果列表大小，则在列表头部加入新的元素（也是个列表），否则取出相对位置的元素：
        // 2.取出的位置为列表大小-当前层数，如当前结果列表大小为5，则说明已经遍历了5层，当前遍历的节点在第3层，则此节点应当在列表中第3位
        // 3.加入当前节点，继续深度优先搜索左右子节点

        dfs(root, 1);

        return result;
    }

    private void dfs(TreeNode root, int tier) {
        if (root == null) {
            return;
        }

        // 树的值待加入的列表
        List<Integer> list;

        // 若当前子树的高度大于列表大小，则在列表首位加入子列表元素
        if (tier > result.size()) {
            list = new ArrayList<>();
            result.add(0, list);
        } else {
            // 否则，当前子树在列表中的位置为：列表大小减子树高度
            list = result.get(result.size() - tier);
        }

        // 加入值
        list.add(root.val);
        // 遍历左右子树
        dfs(root.left, tier + 1);
        dfs(root.right, tier + 1);
    }
}
