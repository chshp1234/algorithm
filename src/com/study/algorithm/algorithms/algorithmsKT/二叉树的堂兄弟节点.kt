package com.study.algorithm.algorithms.algorithmsKT

import com.study.algorithm.algorithms.structure.TreeNode
import org.junit.Test
import java.util.*

//在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
//
//如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
//
//我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
//
//只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
//
// 
//
//示例 1：
//
//
//输入：root = [1,2,3,4], x = 4, y = 3
//输出：false
//示例 2：
//
//
//输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
//输出：true
//示例 3：
//
//
//
//输入：root = [1,2,3,null,4], x = 2, y = 3
//输出：false
// 
//
//提示：
//
//二叉树的节点数介于 2 到 100 之间。
//每个节点的值都是唯一的、范围为 1 到 100 的整数。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/cousins-in-binary-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 二叉树的堂兄弟节点 {
    @Test
    fun 二叉树的堂兄弟节点() {
        val treeNode = TreeNode(1)
        treeNode.left = TreeNode(2)
        treeNode.right = TreeNode(3)
        treeNode.left.left = TreeNode(4)
        treeNode.left.right = TreeNode(5)
        treeNode.right.left = TreeNode(6)
        treeNode.right.right = TreeNode(7)
        println("二叉树的堂兄弟节点:${isCousins(treeNode, 2, 3)}")
    }

    fun isCousins(root: TreeNode?, x: Int, y: Int): Boolean {
        return root?.let {
            val queue = LinkedList<TreeNode>()

            if (root.left != null) {
                queue.offer(root.left)
            }
            if (root.right != null) {
                queue.offer(root.right)
            }

            var temp: TreeNode

            var getFirst = false

            var size: Int

            while (queue.isNotEmpty()) {
                size = queue.size
                for (i in 0 until size) {
                    temp = queue.poll()
                    if (temp.left?.`val` == x || temp.left?.`val` == y) {
                        if (getFirst) {
                            return true
                        }
                        getFirst = true
                    } else if (temp.right?.`val` == x || temp.right?.`val` == y) {
                        if (getFirst) {
                            return true
                        }
                        getFirst = true
                    }
                    if (temp.left != null) {
                        queue.offer(temp.left)
                    }
                    if (temp.right != null) {
                        queue.offer(temp.right)
                    }
                }
                if (getFirst) {
                    return false
                }
            }

            false
        } ?: false

    }

}