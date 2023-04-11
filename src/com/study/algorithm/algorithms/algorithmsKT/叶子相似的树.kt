package com.study.algorithm.algorithms.algorithmsKT

import com.study.algorithm.algorithms.structure.TreeNode

//请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
//
//
//
//举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
//
//如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
//
//如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
//
// 
//
//示例 1：
//
//
//
//输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
//输出：true
//示例 2：
//
//输入：root1 = [1], root2 = [1]
//输出：true
//示例 3：
//
//输入：root1 = [1], root2 = [2]
//输出：false
//示例 4：
//
//输入：root1 = [1,2], root2 = [2,2]
//输出：true
//示例 5：
//
//
//
//输入：root1 = [1,2,3], root2 = [1,3,2]
//输出：false
// 
//
//提示：
//
//给定的两棵树可能会有 1 到 200 个结点。
//给定的两棵树上的值介于 0 到 200 之间。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/leaf-similar-trees
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 叶子相似的树 {
    fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
        //深度优先遍历
        //按树的前序遍历，将两棵树的叶子节点分别加入两个列表中
        //最后依次比较两个列表中的叶子节点是否相同
        val list1 = arrayListOf<TreeNode>()
        val list2 = arrayListOf<TreeNode>()

        if (root1 != null) {
            putLeaf(root1, list1)
        }
        if (root2 != null) {
            putLeaf(root2, list2)
        }

        var len = list1.size
        if (len != list2.size) {
            return false
        }

        for (i in 0 until len) {
            if (list1[i].`val` != list2[i].`val`) {
                return false
            }
        }

        return true
    }

    fun putLeaf(root: TreeNode, list: ArrayList<TreeNode>) {
        if (root.left == null && root.right == null) {
            list.add(root)
            return
        }
        if (root.left != null) {
            putLeaf(root.left, list)
        }
        if (root.right != null) {
            putLeaf(root.right, list)
        }
    }
}