package com.study.algorithm.algorithms.algorithmsKT

import com.study.algorithm.algorithms.structure.TreeNode

class 统计二叉树中好节点的数目 {
    fun goodNodes(root: TreeNode): Int {
        //深度优先搜索
        //DFS搜素过程中，维护从根节点到子节点的最大值
        //如果当前子节点值比最大值大，则结果+1
        //继续判断维护最大值
        //根据新的最大值，再递归判断此节点的左子节点和右子节点
        return goodNodes(root, -10000)
    }

    fun goodNodes(root: TreeNode?, preMax: Int): Int {
        if (root == null) {
            return 0
        }
        val max = Math.max(preMax, root.`val`)
        return goodNodes(root.left, max) + goodNodes(root.right, max) + if (root.`val` >= preMax) 1 else 0
    }
}