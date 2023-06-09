package com.study.algorithm.algorithms.algorithmsKT

import com.study.algorithm.algorithms.structure.TreeNode
import org.junit.Test

//给你二叉树的根节点 root 和一个整数 limit ，请你同时删除树中所有 不足节点 ，并返回最终二叉树的根节点。
//
//假如通过节点 node 的每种可能的 “根-叶” 路径上值的总和全都小于给定的 limit，则该节点被称之为 不足节点 ，需要被删除。
//
//叶子节点，就是没有子节点的节点。
//
// 
//
//示例 1：
//
//
//输入：root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
//输出：[1,2,3,4,null,null,7,8,9,null,14]
//示例 2：
//
//
//输入：root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
//输出：[5,4,8,11,null,17,4,7,null,null,null,5]
//示例 3：
//
//
//输入：root = [1,2,-3,-5,null,4,null], limit = -1
//输出：[1,null,-3,4]
// 
//
//提示：
//
//树中节点数目在范围 [1, 5000] 内
//-105 <= Node.val <= 105
//-109 <= limit <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/insufficient-nodes-in-root-to-leaf-paths
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 根到叶路径上的不足节点 {

    @Test
    fun 根到叶路径上的不足节点() {
        println(
            "根到叶路径上的不足节点:${
                sufficientSubset(
                    TreeNode(1).apply {
                        left = TreeNode(2).apply {
                            left = TreeNode(4)
                        }
                        right = TreeNode(7)
                    }, 8
                )
            }"
        )
    }

    fun sufficientSubset(root: TreeNode?, limit: Int): TreeNode? {
        //深度优先搜索
        //用dfs遍历二叉树,更新维护从根节点到叶子节点的路径和
        //如果到达某个叶子节点时的路径和sum<limit,则删除此叶子节点
        //否则继续遍历左右字节的
        //如果某节点左子节点和右子节点都删除(说明左右子节点都不符合条件),则删除此节点
        val dummy = TreeNode(0).apply {
            left = root
        }
        return if (dfsAndDelete(dummy, 0, limit)) null else dummy.left
    }

    fun dfsAndDelete(root: TreeNode?, sum: Int, limit: Int): Boolean {
        return root?.let {
            return if (root.left == null && root.right == null) {
                sum + it.`val` < limit
            } else {
                val l = dfsAndDelete(it.left, sum + it.`val`, limit)
                val r = dfsAndDelete(it.right, sum + it.`val`, limit)
                if (l) {
                    it.left = null
                }
                if (r) {
                    it.right = null
                }
                return l && r
            }
        } ?: true
    }
}