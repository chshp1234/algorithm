package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。
//
// 
//
//示例 1：
//
//
//
//输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
//输出：15
//示例 2：
//
//输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
//输出：19
// 
//
//提示：
//
//树中节点数目在范围 [1, 104] 之间。
//1 <= Node.val <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/deepest-leaves-sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 层数最深叶子节点的和 {
    //广度优先遍历
    //层次遍历，更新每一层的节点的和
    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int count = root.val;
        while (!queue.isEmpty()) {
            count = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                count += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return count;
    }
}
