package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

//给你二叉树的根结点 root ，此外树的每个结点的值要么是 0 ，要么是 1 。
//
//返回移除了所有不包含 1 的子树的原二叉树。
//
//节点 node 的子树为 node 本身加上所有 node 的后代。
//
// 
//
//示例 1：
//
//
//输入：root = [1,null,0,0,1]
//输出：[1,null,0,null,1]
//解释：
//只有红色节点满足条件“所有不包含 1 的子树”。 右图为返回的答案。
//示例 2：
//
//
//输入：root = [1,0,1,0,0,0,1]
//输出：[1,null,1,null,1]
//示例 3：
//
//
//输入：root = [1,1,0,1,1,0,1,0]
//输出：[1,1,0,1,1,null,1]
// 
//
//提示：
//
//树中节点的数目在范围 [1, 200] 内
//Node.val 为 0 或 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/binary-tree-pruning
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 二叉树剪枝 {
    public TreeNode pruneTree(TreeNode root) {
        //深度优先搜索
        //递归遍历二叉树
        //当节点为子节点时，并且值为0，则删除当前节点
        //若左右子节点都被删除，切当前节点为0，则删除当前节点
        //否则不删除当前节点
        if (dfsPruneTree(root) && root.val == 0) {
            return null;
        }
        return root;
    }

    //用布尔值判断当前节点是否删除
    public boolean dfsPruneTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean pruneL = dfsPruneTree(root.left);
        boolean pruneR = dfsPruneTree(root.right);
        if (pruneL) {
            root.left = null;
        }
        if (pruneR) {
            root.right = null;
        }
        return pruneL && pruneR && root.val == 0;
    }
}
