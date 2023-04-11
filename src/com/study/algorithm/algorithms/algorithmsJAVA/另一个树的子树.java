package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class 另一个树的子树 {

    @Test
    public void 相同的树() {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(4);
        treeNode.right = new TreeNode(5);
        treeNode.left.left = new TreeNode(1);
        treeNode.left.right = new TreeNode(2);
        treeNode.left.left.left = new TreeNode(0);
        TreeNode treeNode1 = new TreeNode(4);
        treeNode1.left = new TreeNode(1);
        treeNode1.right = new TreeNode(2);
        System.out.println("相同的树:" + isSubtree(treeNode, treeNode1));
    }

    // 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
    //
    // 示例 1:
    // 给定的树 s:
    //
    //     3
    //    / \
    //   4   5
    //  / \
    // 1   2
    // 给定的树 t：
    //
    //   4
    //  / \
    // 1   2
    // 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
    //
    // 示例 2:
    // 给定的树 s：
    //
    //     3
    //    / \
    //   4   5
    //  / \
    // 1   2
    //    /
    //   0
    // 给定的树 t：
    //
    //   4
    //  / \
    // 1   2
    // 返回 false。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean isSubtree(TreeNode s, TreeNode t) {
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();

        treeNodeQueue.offer(s);

        while (!treeNodeQueue.isEmpty()) {
            s = treeNodeQueue.poll();
            if (!isSame(s, t)) {
                if (s != null) {
                    treeNodeQueue.offer(s.left);
                    treeNodeQueue.offer(s.right);
                }
            } else {
                return true;
            }
        }

        return false;
    }

    private boolean isSame(TreeNode s, TreeNode t) {

        if (s == null && t == null) {
            return true;
        }

        if (s != null && t != null && s.val == t.val) {
            return isSame(s.left, t.left) && isSame(s.right, t.right);
        } else {
            return false;
        }
    }
}
