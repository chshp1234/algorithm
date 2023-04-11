package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

//给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
//
// 
//
//示例 1：
//
//
//输入：root = [3,1,4,null,2], k = 1
//输出：1
//示例 2：
//
//
//输入：root = [5,3,6,2,4,null,null,1], k = 3
//输出：3
// 
//
// 
//
//提示：
//
//树中的节点数为 n 。
//1 <= k <= n <= 104
//0 <= Node.val <= 104
// 
//
//进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 二叉搜索树中第K小的元素 {
    int index = 0;
    int result = 0;

    public int kthSmallest(TreeNode root, int k) {
        //二叉搜索树的中序遍历
        //中序遍历二叉搜索树，每次遍历令index+1，当index=k时，说明找到第k小的节点，返回该值
        dfs(root, k);
        return result;
    }

    public void dfs(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        dfs(root.left, k);
        //已经找到该节点，直接返回，不继续后续的遍历
        if (index == k) {
            return;
        }
        //index+1
        index++;
        if (index == k) {
            //找到第k小的节点
            result = root.val;
            return;
        }
        dfs(root.right, k);
    }
}
