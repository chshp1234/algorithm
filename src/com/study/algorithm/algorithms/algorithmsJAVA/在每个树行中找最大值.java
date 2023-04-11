package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
//
// 
//
//示例1：
//
//
//
//输入: root = [1,3,2,5,3,null,9]
//输出: [1,3,9]
//示例2：
//
//输入: root = [1,2,3]
//输出: [1,3]
// 
//
//提示：
//
//二叉树的节点个数的范围是 [0,104]
//-231 <= Node.val <= 231 - 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/find-largest-value-in-each-tree-row
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 在每个树行中找最大值 {
    public List<Integer> largestValues(TreeNode root) {
        //广度优先搜索
        //广度优先搜索可以方便的对树进行层次遍历
        //在遍历过程中，更新每层的最大值，并在每层遍历结束时，加入结果列表中
        //当然也可以使用深度优先搜索，但是递归过程中，需要手动维护一个值，代表树的深度，使能够正确的更新树的每一层的最大值
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        TreeNode temp;
        int size;
        int max;
        while (!queue.isEmpty()) {
            size = queue.size();
            max = Integer.MIN_VALUE;

            for (int i = 0; i < size; i++) {
                temp = queue.poll();
                max = Math.max(max, temp.val);
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }

            result.add(max);
        }

        return result;
    }
}
