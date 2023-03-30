package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

//给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
//
//创建一个根节点，其值为 nums 中的最大值。
//递归地在最大值 左边 的 子数组前缀上 构建左子树。
//递归地在最大值 右边 的 子数组后缀上 构建右子树。
//返回 nums 构建的 最大二叉树 。
//
// 
//
//示例 1：
//
//
//输入：nums = [3,2,1,6,0,5]
//输出：[6,3,5,null,2,0,null,null,1]
//解释：递归调用如下所示：
//- [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
//    - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
//        - 空数组，无子节点。
//        - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
//            - 空数组，无子节点。
//            - 只有一个元素，所以子节点是一个值为 1 的节点。
//    - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
//        - 只有一个元素，所以子节点是一个值为 0 的节点。
//        - 空数组，无子节点。
//示例 2：
//
//
//输入：nums = [3,2,1]
//输出：[3,null,2,null,1]
// 
//
//提示：
//
//1 <= nums.length <= 1000
//0 <= nums[i] <= 1000
//nums 中的所有整数 互不相同
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-binary-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最大二叉树 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> stack = new ArrayDeque<>(nums.length);

        TreeNode dummy = new TreeNode(10001);
        stack.push(dummy);

        TreeNode top = dummy;
        for (int n : nums) {

            if (n < top.val) {
                //如果当前值小于栈顶元素，说明比数组左侧的值小，那么就为左侧元素的右子树
                top.right = new TreeNode(n);
            } else {

                //否则，找到比当前值大的值，那么就为其更大值的节点的右子树
                do {
                    stack.pop();
                    top = stack.peekFirst();
                } while (n > top.val);


                TreeNode r = top.right;
                top.right = new TreeNode(n);
                //同时原右子树，将是新右子树的左子树
                top.right.left = r;
            }
            stack.push(top.right);
            top = top.right;
        }
        return dummy.right;
    }
}
