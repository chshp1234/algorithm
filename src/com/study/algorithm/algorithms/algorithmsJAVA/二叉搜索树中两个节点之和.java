package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

import java.util.HashSet;
import java.util.Set;

//给定一个二叉搜索树的 根节点 root 和一个整数 k , 请判断该二叉搜索树中是否存在两个节点它们的值之和等于 k 。假设二叉搜索树中节点的值均唯一。
//
// 
//
//示例 1：
//
//输入: root = [8,6,10,5,7,9,11], k = 12
//输出: true
//解释: 节点 5 和节点 7 之和等于 12
//示例 2：
//
//输入: root = [8,6,10,5,7,9,11], k = 22
//输出: false
//解释: 不存在两个节点值之和为 22 的节点
// 
//
//提示：
//
//二叉树的节点个数的范围是  [1, 104].
//-104 <= Node.val <= 104
//root 为二叉搜索树
//-105 <= k <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/opLdQZ
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 二叉搜索树中两个节点之和 {
    public boolean findTarget(TreeNode root, int k) {
        //二叉树版两数之和
        //用一个哈希表存储已遍历的元素，计算目标和当前值差值，若差值存在于哈希表中，则找到两个数，否则继续遍历二叉树。
        return dfs(new HashSet<>(), root, k);

    }

    public boolean dfs(Set<Integer> set, TreeNode treeNode, int k) {
        if (treeNode == null) {
            return false;
        }

        int diff = k - treeNode.val;
        if (set.contains(diff)) {
            return true;
        }
        set.add(treeNode.val);

        if (diff <= 0) {
            return dfs(set, treeNode.left, k);
        }

        return dfs(set, treeNode.left, k) || dfs(set, treeNode.right, k);
    }

}
