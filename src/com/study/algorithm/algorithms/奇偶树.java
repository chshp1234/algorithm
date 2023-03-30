package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：
//
//二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
//偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
//奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
//给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。
//
// 
//
//示例 1：
//
//
//
//输入：root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
//输出：true
//解释：每一层的节点值分别是：
//0 层：[1]
//1 层：[10,4]
//2 层：[3,7,9]
//3 层：[12,8,6,2]
//由于 0 层和 2 层上的节点值都是奇数且严格递增，而 1 层和 3 层上的节点值都是偶数且严格递减，因此这是一棵奇偶树。
//示例 2：
//
//
//
//输入：root = [5,4,2,3,3,7]
//输出：false
//解释：每一层的节点值分别是：
//0 层：[5]
//1 层：[4,2]
//2 层：[3,3,7]
//2 层上的节点值不满足严格递增的条件，所以这不是一棵奇偶树。
//示例 3：
//
//
//
//输入：root = [5,9,1,3,5,7]
//输出：false
//解释：1 层上的节点值应为偶数。
//示例 4：
//
//输入：root = [1]
//输出：true
//示例 5：
//
//输入：root = [11,8,6,1,3,9,11,30,20,18,16,12,10,4,2,17]
//输出：true
// 
//
//提示：
//
//树中节点数在范围 [1, 105] 内
//1 <= Node.val <= 106
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/even-odd-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 奇偶树 {
    public boolean isEvenOddTree(TreeNode root) {
        //二叉树层次遍历
        //安装层次遍历，只要根据题目要求，偶数层节点都是奇数，且从左到右严格递增，奇数层都是偶数，且从左到右严格递减，即可。

        //使用队列进行广度优先搜索
        Queue<TreeNode> queue = new LinkedList<>();
        //加入根节点
        queue.offer(root);

        boolean even = false;
        TreeNode last;
        TreeNode current;
        while (!queue.isEmpty()) {
            //当前层取反
            even = !even;
            //当前层有的节点数
            int size = queue.size();
            //先取出当前的第一个节点
            last = queue.poll();

            //根据奇偶层，判断当前节点是否满足偶奇数
            if (even) {
                if (last.val % 2 == 0) {
                    return false;
                }
            } else {
                if (last.val % 2 != 0) {
                    return false;
                }
            }
            //加入子节点
            if (last.left != null) {
                queue.offer(last.left);
            }
            if (last.right != null) {
                queue.offer(last.right);
            }

            //遍历当前层
            for (int i = 1; i < size; i++) {
                current = queue.poll();
                //如果是偶数层
                if (even) {
                    if (current.val % 2 == 0 || current.val <= last.val) {
                        //如果当前节点是偶数，或者当前节点不是严格递增，则不满足条件，返回fasle
                        return false;
                    }
                }
                //如果是奇数层
                else {
                    if (current.val % 2 != 0 || current.val >= last.val) {
                        //如果当前节点是奇数，或者当前节点不是严格递减，则不满足条件，返回fasle
                        return false;
                    }
                }
                last = current;
                if (last.left != null) {
                    queue.offer(last.left);
                }
                if (last.right != null) {
                    queue.offer(last.right);
                }
            }
        }

        //遍历结束，满足条件
        return true;
    }
}
