package com.study.algorithm.algorithmsKT

import com.study.algorithm.algorithms.structure.TreeNode

class 二叉搜索树的范围和 {
    fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {

        return root?.let {
            when {
                root.`val` < low -> rangeSumBST(root.right, low, high)
                root.`val` > high -> rangeSumBST(root.left, low, high)
                root.`val` == low -> rangeSumBST(root.right, low, high) + root.`val`
                root.`val` == high -> rangeSumBST(root.left, low, high) + root.`val`
                else -> rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high) + root.`val`
            }
        } ?: 0
    }
}