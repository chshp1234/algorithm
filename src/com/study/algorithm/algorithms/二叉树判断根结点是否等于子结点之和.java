package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

//给你一个 二叉树 的根结点 root，该二叉树由恰好 3 个结点组成：根结点、左子结点和右子结点。
//
//如果根结点值等于两个子结点值之和，返回 true ，否则返回 false 。
//
// 
//
//示例 1：
//
//
//输入：root = [10,4,6]
//输出：true
//解释：根结点、左子结点和右子结点的值分别是 10 、4 和 6 。
//由于 10 等于 4 + 6 ，因此返回 true 。
//示例 2：
//
//
//输入：root = [5,3,1]
//输出：false
//解释：根结点、左子结点和右子结点的值分别是 5 、3 和 1 。
//由于 5 不等于 3 + 1 ，因此返回 false 。
// 
//
//提示：
//
//树只包含根结点、左子结点和右子结点
//-100 <= Node.val <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/root-equals-sum-of-children
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 二叉树判断根结点是否等于子结点之和 {
    public boolean checkTree(TreeNode root) {
        return root.val == (root.left.val + root.right.val);
    }
}
