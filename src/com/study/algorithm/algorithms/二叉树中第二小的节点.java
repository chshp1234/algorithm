package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

//给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
//
//更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
//
//给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
//
// 
//
//示例 1：
//
//
//输入：root = [2,2,5,null,null,5,7]
//输出：5
//解释：最小的值是 2 ，第二小的值是 5 。
//示例 2：
//
//
//输入：root = [2,2,2]
//输出：-1
//解释：最小的值是 2, 但是不存在第二小的值。
// 
//
//提示：
//
//树中节点数目在范围 [1, 25] 内
//1 <= Node.val <= 231 - 1
//对于树中每个节点 root.val == min(root.left.val, root.right.val)
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 二叉树中第二小的节点 {
    public int findSecondMinimumValue(TreeNode root) {
        //如果没有子树，返回-1
        if (root.left == null) {
            return -1;
        }

        //如果左子树值大于右子树值
        if (root.left.val > root.right.val) {
            //只需要递归右子树，左子树值已经是“此时”第二小的值
            int rsm = findSecondMinimumValue(root.right);
            //如果右子树得出值是-1（说明整颗右子树值都相同），则可直接返回做子树值
            return rsm == -1 ? root.left.val : Math.min(root.left.val, rsm);
        }
        //同上
        if (root.right.val > root.left.val) {
            int lsm = findSecondMinimumValue(root.left);
            return lsm == -1 ? root.right.val : Math.min(lsm, root.right.val);
        }

        //如果左右子树值相同，那么左右子树都需进行递归
        int leftSMin = findSecondMinimumValue(root.left);
        int rightSMin = findSecondMinimumValue(root.right);

        //如果左子树为-1（说明整颗左子树都是相同的值），那么之间返回右子树
        if (leftSMin == -1) {
            return rightSMin;
        }
        //同上
        if (rightSMin == -1) {
            return leftSMin;
        }
        //左右子树都有“第二小”的值，那么返回其中最小的
        return Math.min(leftSMin, rightSMin);
    }
}
