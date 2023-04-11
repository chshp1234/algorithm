package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class 二叉树展开为链表 {

    @Test
    public void 二叉树展开为链表() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(4);
        treeNode.right.left = new TreeNode(6);

        flatten(treeNode);
        System.out.println("二叉树展开为链表:" + treeNode);
    }

    // 给定一个二叉树，原地将它展开为一个单链表。
    //
    //
    //
    // 例如，给定二叉树
    //
    //     1
    //    / \
    //   2   5
    //  / \   \
    // 3   4   6
    // 将其展开为：
    //
    // 1
    //  \
    //   2
    //    \
    //     3
    //      \
    //       4
    //        \
    //         5
    //          \
    //           6
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        // 深度优先遍历，使用栈保存待添加的元素
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode temp = root;

        while (true) {
            // 如果节点左右都不为空
            if (temp.left != null && temp.right != null) {

                // 右子节点加入栈
                deque.push(temp.right);

                // 左子节点挂在右子节点上
                temp.right = temp.left;

                // 左子节点设为空
                temp.left = null;

                // 遍历右子节点（原左子节点）
                temp = temp.right;
            }
            // 如果左子节点不为空
            else if (temp.left != null) {

                // 左子节点挂在右子节点上
                temp.right = temp.left;

                // 左子节点设为空
                temp.left = null;

                // 遍历右子节点（原左子节点）
                temp = temp.right;
            }
            // 如果右子节点不为空
            else if (temp.right != null) {
                // 遍历右子节点
                temp = temp.right;
            }
            // 如果左右子节点都为空
            else {

                // 并且栈也为空，则退出
                if (deque.isEmpty()) {
                    break;
                }

                // 右子节点为出栈元素
                temp.right = deque.pop();

                // 遍历右子节点（原栈顶元素）
                temp = temp.right;
            }
        }
    }

    public void flattenByJoint(TreeNode root) {
        // 可以发现展开的顺序其实就是二叉树的先序遍历。算法和 94 题中序遍历的 Morris 算法有些神似，我们需要两步完成这道题。
        //
        // 将左子树插入到右子树的地方
        // 将原来的右子树接到左子树的最右边节点
        // 考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
        // 可以看图理解下这个过程。
        //    1
        //   / \
        //  2   5
        // / \   \
        // 3   4  6
        //
        // 将 1 的左子树插入到右子树的地方
        //    1
        //     \
        //      2         5
        //     / \         \
        //    3   4         6
        // 将原来的右子树接到左子树的最右边节点
        //    1
        //     \
        //      2
        //     / \
        //    3   4
        //         \
        //          5
        //           \
        //            6
        //
        // 将 2 的左子树插入到右子树的地方
        //    1
        //     \
        //      2
        //       \
        //        3       4
        //                 \
        //                  5
        //                   \
        //                    6
        //
        // 将原来的右子树接到左子树的最右边节点
        //    1
        //     \
        //      2
        //       \
        //        3
        //         \
        //          4
        //           \
        //            5
        //             \
        //              6
        //
        // 作者：windliang
        // 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        while (root != null) {
            // 左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
                root = root.right;
            }
        }
    }
}
