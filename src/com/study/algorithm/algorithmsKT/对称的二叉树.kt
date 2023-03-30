package com.study.algorithm.algorithmsKT

import com.study.algorithm.algorithms.structure.TreeNode
import org.junit.Test

class 对称的二叉树 {
    @Test
    fun 对称的二叉树() {
        val treeNode = TreeNode(1)
        treeNode.left = TreeNode(2)
        treeNode.right = TreeNode(2)
        treeNode.left.left = TreeNode(3)
        treeNode.left.right = TreeNode(4)
        treeNode.right.left = TreeNode(4)
        treeNode.right.right = TreeNode(3)
        println("对称的二叉树:${isSymmetric(treeNode)}")
    }

    fun isSymmetric(root: TreeNode?): Boolean {
        // 递归
        return isSymmetric(root?.left, root?.right)
    }

    fun isSymmetric(left: TreeNode?, right: TreeNode?): Boolean {
        return left?.let {
            right?.let {
                left.`val` == right.`val` &&
                        isSymmetric(left.left, right.right) &&
                        isSymmetric(left.right, right.left)
            } ?: false
        } ?: (right == null)
    }
}