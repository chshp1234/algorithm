package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

//给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 唯一的答案 。
//
//所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
//
// 
//
//示例 1：
//
//
//输入：root = [1,0,2], low = 1, high = 2
//输出：[1,null,2]
//示例 2：
//
//
//输入：root = [3,0,4,null,2,null,null,1], low = 1, high = 3
//输出：[3,2,null,1]
// 
//
//提示：
//
//树中节点数在范围 [1, 104] 内
//0 <= Node.val <= 104
//树中每个节点的值都是 唯一 的
//题目数据保证输入是一棵有效的二叉搜索树
//0 <= low <= high <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/trim-a-binary-search-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 修剪二叉搜索树 {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        //递归
        //递归进行修剪左边界和右边界
        //首先找到合适的区域(使根节点在[low,high]之间),如果当前节点小于low,则递归遍历root.right;如果当前节点大于high,则递归遍历root.left
        //其次修剪左右边界(此时根节点必然在[low,high]之间)
        //修剪左边界trimLow:
        //如果根节点root.val >= low,说明根节点在区间内,继续遍历root.left,并且root.left=trimLow(root.left, low);否则,不在区间内,那么返回修剪后的右子节点trimLow(root.right, low)
        //修剪右边界trimHigh:
        //如果根节点root.val <= high,说明根节点在区间内,继续遍历root.right,并且root.right=trimLow(root.right, low);否则,不在区间内,那么返回修剪后的左子节点trimLow(root.left, low)
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        //到此说明root.val在[low,high]之间,返回哪个都一样
        trimHigh(root, high);
        return trimLow(root, low);
    }

    //修剪低位节点,并返回修剪后的子节点
    public TreeNode trimLow(TreeNode root, int low) {
        if (root == null) {
            return null;
        }
        if (root.val >= low) {
            root.left = trimLow(root.left, low);
            return root;
        }
        return trimLow(root.right, low);
    }

    //修剪高位节点,并返回修剪后的子节点
    public TreeNode trimHigh(TreeNode root, int high) {
        if (root == null) {
            return null;
        }
        if (root.val <= high) {
            root.right = trimHigh(root.right, high);
            return root;
        }
        return trimHigh(root.left, high);
    }
}
