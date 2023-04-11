package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 恢复二叉搜索树 {

    @Test
    public void 恢复二叉搜索树() {

        TreeNode treeNode = new TreeNode(3);
        treeNode.setLeft(new TreeNode(5));
        treeNode.setRight(new TreeNode(1));
        treeNode.getLeft().setLeft(new TreeNode(4));
        treeNode.getLeft().setRight(new TreeNode(2));
        treeNode.getLeft().getLeft().setLeft(new TreeNode(7));
        treeNode.getLeft().getLeft().setRight(new TreeNode(6));
        recoverTree(treeNode);
        System.out.println("恢复二叉搜索树：" + treeNode);
    }

    // 二叉搜索树中的两个节点被错误地交换。
    //
    // 请在不改变其结构的情况下，恢复这棵树。
    //
    // 示例 1:
    //
    // 输入: [1,3,null,null,2]
    //
    //    1
    //   /
    //  3
    //   \
    //    2
    //
    // 输出: [3,1,null,null,2]
    //
    //    3
    //   /
    //  1
    //   \
    //    2
    // 示例 2:
    //
    // 输入: [3,1,4,null,null,2]
    //
    //  3
    // / \
    // 1   4
    //    /
    //   2
    //
    // 输出: [2,1,4,null,null,3]
    //
    //  2
    // / \
    // 1   4
    //    /
    //  3
    // 进阶:
    //
    // 使用 O(n) 空间复杂度的解法很容易实现。
    // 你能想出一个只使用常数空间的解决方案吗？
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public void recoverTree(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        List<TreeNode> nodeList = new ArrayList<>();

        // 思路1：中序遍历二叉树，返回遍历后的二叉树列表，以及二叉树值
        getVal(list, nodeList, root);

        // 排序二叉树中的节点的值
        Collections.sort(list);

        // 给二叉树重新赋值（由于二叉搜索树中序遍历后是个排序数组）
        //        for (int i = 0, l = list.size(); i < l; i++) {
        //            nodeList.get(i).val = list.get(i);
        //        }

        // 思路2：遍历二叉树，返回二叉树值，并排序
        // 计算二叉树左子树数量，则二叉树节点值为列表当前位置+左子树数量
        getChildCount(list, root, 0);
    }

    private void getVal(List<Integer> list, List<TreeNode> nodeList, TreeNode root) {
        if (root == null) {
            return;
        }
        getVal(list, nodeList, root.left);

        nodeList.add(root);
        list.add(root.val);

        getVal(list, nodeList, root.right);
    }

    private int getChildCount(List<Integer> list, TreeNode root, int index) {
        if (root == null) {
            return 0;
        }

        // 计算左子树数量
        int leftCount = getChildCount(list, root.left, index);
        index += leftCount;

        // 此节点的值为左子树数量+遍历到此时数组中的位置
        root.val = list.get(index);

        // 计算右子树数量
        int rightCount = getChildCount(list, root.right, index + 1);

        // 加上此节点总数量
        return leftCount + rightCount + 1;
    }
}
