package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 路径总和II {

    @Test
    public void 路径总和() {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(4);
        treeNode.right = new TreeNode(8);
        treeNode.left.left = new TreeNode(11);
        treeNode.right.left = new TreeNode(13);
        treeNode.right.right = new TreeNode(4);
        treeNode.right.right.right = new TreeNode(1);
        treeNode.right.right.left = new TreeNode(5);
        treeNode.left.left.left = new TreeNode(7);
        treeNode.left.left.right = new TreeNode(2);
        System.out.println("路径总和：" + pathSum(treeNode, 22));
        /*List<Integer> list = new ArrayList<>();
        list.add(1, 1);*/

    }

    List<List<Integer>> result = new ArrayList<>();

    List<Integer> temp = new ArrayList<>();

    // 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
    //
    // 说明: 叶子节点是指没有子节点的节点。
    //
    // 示例:
    // 给定如下二叉树，以及目标和 sum = 22，
    //
    //              5
    //             / \
    //            4   8
    //           /   / \
    //          11  13  4
    //         /  \    / \
    //        7    2  5   1
    // 返回:
    //
    // [
    //   [5,4,11,2],
    //   [5,8,4,5]
    // ]
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/path-sum-ii
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        // 回溯，深度优先遍历
        // 依次把每个节点加入临时数组中，当遍历到根节点时，判断当前所有节点相加的总和是否等于目标值，若是加入结果数组，不是则返回，
        // 从临时数组中删除上一个添加的元素，继续向下一条路径进行便利
        if (root != null) {
            backtrack(root, 0, 1, sum);
        }
        return result;
    }

    public void backtrack(TreeNode root, int currentSum, int index, int sum) {

        int curr = currentSum + root.val;
        temp.add(root.val);
        if (root.left == null && root.right == null) {
            if (curr == sum) {
                result.add(new ArrayList<>(temp));
                return;
            }
            return;
        }

        if (root.left != null) {
            backtrack(root.left, curr, index + 1, sum);
            temp.remove(index);
        }
        if (root.right != null) {
            backtrack(root.right, curr, index + 1, sum);
            temp.remove(index);
        }
    }
}
