package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

//给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
//
//树的 最大宽度 是所有层中最大的 宽度 。
//
//每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
//
//题目数据保证答案将会在  32 位 带符号整数范围内。
//
// 
//
//示例 1：
//
//
//输入：root = [1,3,2,5,3,null,9]
//输出：4
//解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
//示例 2：
//
//
//输入：root = [1,3,2,5,null,null,9,6,null,7]
//输出：7
//解释：最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。
//示例 3：
//
//
//输入：root = [1,3,2,5]
//输出：2
//解释：最大宽度出现在树的第 2 层，宽度为 2 (3,2) 。
// 
//
//提示：
//
//树中节点的数目范围是 [1, 3000]
//-100 <= Node.val <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-width-of-binary-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 二叉树最大宽度 {
    @Test
    public void 二叉树最大宽度() {

        TreeNode treeNode = new TreeNode(0);
        treeNode.left = new TreeNode(10);
        treeNode.right = new TreeNode(10);
        treeNode.left.left = new TreeNode(5);
        treeNode.left.right = new TreeNode(14);
        treeNode.right.right = new TreeNode(2);

        System.out.println("二叉树最大宽度：" + widthOfBinaryTree(treeNode));
    }

    public int widthOfBinaryTree(TreeNode root) {
        //广度优先遍历
        //层次遍历，额外需要一个队列，存储每个元素所在的位置index，其中左子树位置为2*index-1，右子树位置为2*index
        //这样就计算每一层的宽度，然后更新最大宽度既可
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> place = new LinkedList<>();

        nodeQueue.offer(root);
        place.offer(1);

        int maxWidth = 0;
        TreeNode node;
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            int left = place.peek();

            int width = 0;

            for (int i = 0; i < size; i++) {
                node = nodeQueue.poll();
                int index = place.poll();
                width = index - left;
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    place.offer((index * 2) - 1);
                }
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    place.offer((index * 2));
                }
            }
            maxWidth = Math.max(maxWidth, width + 1);
        }

        return maxWidth;
    }
}
