package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
//
//请返回层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。
//
// 
//
//示例 1：
//
//
//
//输入：root = [1,7,0,7,-8,null,null]
//输出：2
//解释：
//第 1 层各元素之和为 1，
//第 2 层各元素之和为 7 + 0 = 7，
//第 3 层各元素之和为 7 + -8 = -1，
//所以我们返回第 2 层的层号，它的层内元素之和最大。
//示例 2：
//
//输入：root = [989,null,10250,98693,-89388,null,null,null,-32127]
//输出：2
// 
//
//提示：
//
//树中的节点数在 [1, 104]范围内
//-105 <= Node.val <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-level-sum-of-a-binary-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最大层内元素和 {
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int tier = 0;
        int max = Integer.MIN_VALUE;
        int result = 0;

        while (!queue.isEmpty()) {
            tier++;
            int size = queue.size();
            int sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (sum > max) {
                max = sum;
                result = tier;
            }
        }

        return result;

    }
}
