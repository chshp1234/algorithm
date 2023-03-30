package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 二叉树的前序遍历 {
    @Test
    public void 二叉树的前序遍历() {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        TreeNode treeNode = new TreeNode(3);
        treeNode.setLeft(new TreeNode(5));
        treeNode.setRight(new TreeNode(1));
        treeNode.getLeft().setLeft(new TreeNode(4));
        treeNode.getLeft().setRight(new TreeNode(2));
        treeNode.getLeft().getLeft().setLeft(new TreeNode(7));
        treeNode.getLeft().getLeft().setRight(new TreeNode(6));
        //        int[] b = {5, 2, 6, 2, 3, 2};
        System.out.println("二叉树的前序遍历：" + preorderTraversal(treeNode));
    }
    /**
     * 给定一个二叉树，返回它的 前序 遍历。
     *
     * <p> 示例:
     *
     * <p>输入: [1,null,2,3] 1 \ 2 / 3
     *
     * <p>输出: [1,2,3] 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root the root
     * @return the list
     */
    public List<Integer> preorderTraversal(TreeNode root) {

        // 递推，模拟栈
        // 每次加入左右子树都不为空的节点到栈中
        // 并依次遍历判断左子树、右子树是否为空，若不为空，则加入子树节点的值，并设置此时的子树为空（防止重复遍历）
        // 若遍历到叶子节点，则取出栈顶元素，接着上一步操作。

        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        list.add(root.val);

        Deque<TreeNode> rootQueue = new LinkedList<>();
        rootQueue.offer(root);

        TreeNode cur = root;
        TreeNode temp;

        for (; ; ) {

            if (cur.right != null && cur.left != null) {
                rootQueue.push(cur);
            }

            if (cur.left != null) {
                temp = cur;
                cur = cur.left;
                temp.left = null;
                list.add(cur.val);
            } else if (cur.right != null) {
                temp = cur;
                cur = cur.right;
                temp.right = null;
                list.add(cur.val);
            } else {
                cur = rootQueue.pop();
            }

            if (rootQueue.peek() == null) {
                break;
            }
        }

        return list;
    }

    public List<Integer> preorderTraversalByStack(TreeNode root) {
        // 思路与算法
        //
        // 我们也可以用迭代的方式实现方法一的递归函数，两种方式是等价的，区别在于递归的时候隐式地维护了一个栈，
        // 而我们在迭代的时候需要显式地将这个栈模拟出来，其余的实现与细节都相同，具体可以参考下面的代码。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/er-cha-shu-de-qian-xu-bian-li-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }
}
