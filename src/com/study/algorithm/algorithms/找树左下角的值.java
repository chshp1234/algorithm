package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
//
//假设二叉树中至少有一个节点。
//
// 
//
//示例 1:
//
//
//
//输入: root = [2,1,3]
//输出: 1
//示例 2:
//
//
//
//输入: [1,2,3,4,null,5,6,null,null,7]
//输出: 7
// 
//
//提示:
//
//二叉树的节点个数的范围是 [1,104]
//-231 <= Node.val <= 231 - 1 
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/find-bottom-left-tree-value
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 找树左下角的值 {
    public int findBottomLeftValue(TreeNode root) {
        //广度优先搜索
        //用队列实现广度优先搜索去搜索二叉树
        //获取每层中最左边的节点作result，并将不为空的左右子树加入队列中
        //最后返回result即可找到最底层最左边的节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        TreeNode temp;
        TreeNode result = root;
        while (!queue.isEmpty()) {
            int size = queue.size();
            result = queue.poll();
            if (result.left != null) {
                queue.offer(result.left);
            }
            if (result.right != null) {
                queue.offer(result.right);
            }
            for (int i = 1; i < size; i++) {
                temp = queue.poll();
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
        }

        return result.val;
    }
}
