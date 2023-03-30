package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

public class 是否为对称二叉树 {
    @Test
    public void 对称二叉树() {
        TreeNode treeNode = new TreeNode(2);

        treeNode.setLeft(new TreeNode(3));
        treeNode.setRight(new TreeNode(3));

        treeNode.getLeft().setLeft(new TreeNode(4));
        treeNode.getLeft().setRight(new TreeNode(5));

        treeNode.getRight().setLeft(new TreeNode(5));
        treeNode.getRight().setRight(new TreeNode(4));

        System.out.println("二叉树是否为镜像：" + isSymmetric(treeNode));
    }
    /**
     * 给定一个二叉树，检查它是否是镜像对称的。
     *
     * <p>例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     *
     * <p>1 / \ 2 2 / \ / \ 3 4 4 3 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     *
     * <p>1 / \ 2 2 \ \ 3 3 说明:
     *
     * <p>如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/symmetric-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        if (root.getLeft() != null
                && root.getRight() != null
                && root.getLeft().val == root.getRight().val) {
            return isSymmetric(root.getLeft(), root.getRight());
        }

        return false;
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (((left.getLeft() == null && right.getRight() == null)
                        || (left.getLeft() != null
                                && right.getRight() != null
                                && left.getLeft().val == right.getRight().val))
                && ((left.getRight() == null && right.getLeft() == null)
                        || (left.getRight() != null
                                && right.getLeft() != null
                                && left.getRight().val == right.getLeft().val))) {
            if (left.getLeft() != null && !isSymmetric(left.getLeft(), right.getRight())) {
                return false;
            }
            if (left.getRight() != null && !isSymmetric(left.getRight(), right.getLeft())) {
                return false;
            }
            return true;
        }

        return false;
    }
}
