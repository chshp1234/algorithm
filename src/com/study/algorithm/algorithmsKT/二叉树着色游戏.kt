package com.study.algorithm.algorithmsKT

import com.study.algorithm.algorithms.structure.TreeNode

//有两位极客玩家参与了一场「二叉树着色」的游戏。游戏中，给出二叉树的根节点 root，树上总共有 n 个节点，且 n 为奇数，其中每个节点上的值从 1 到 n 各不相同。
//
//最开始时：
//
//「一号」玩家从 [1, n] 中取一个值 x（1 <= x <= n）；
//「二号」玩家也从 [1, n] 中取一个值 y（1 <= y <= n）且 y != x。
//「一号」玩家给值为 x 的节点染上红色，而「二号」玩家给值为 y 的节点染上蓝色。
//
//之后两位玩家轮流进行操作，「一号」玩家先手。每一回合，玩家选择一个被他染过色的节点，将所选节点一个 未着色 的邻节点（即左右子节点、或父节点）进行染色（「一号」玩家染红色，「二号」玩家染蓝色）。
//
//如果（且仅在此种情况下）当前玩家无法找到这样的节点来染色时，其回合就会被跳过。
//
//若两个玩家都没有可以染色的节点时，游戏结束。着色节点最多的那位玩家获得胜利 ✌️。
//
//现在，假设你是「二号」玩家，根据所给出的输入，假如存在一个 y 值可以确保你赢得这场游戏，则返回 true ；若无法获胜，就请返回 false 。
//
// 
//示例 1 ：
//
//
//输入：root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
//输出：true
//解释：第二个玩家可以选择值为 2 的节点。
//示例 2 ：
//
//输入：root = [1,2,3], n = 3, x = 1
//输出：false
// 
//
//提示：
//
//树中节点数目为 n
//1 <= x <= n <= 100
//n 是奇数
//1 <= Node.val <= n
//树中所有值 互不相同
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/binary-tree-coloring-game
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 二叉树着色游戏 {

    fun btreeGameWinningMove(root: TreeNode, n: Int, x: Int): Boolean {
        //贪心，深度优先搜索
        //先手x确定位置后，y可以贪心的在xNode的父节点，xNode左子节点，或右子节点进行着色，以达到最后着色的数量最大化
        //那么首先用深度优先搜索（或广度优先搜索）确定xNode节点
        //然后计算xNode的左子节点数量leftCount，右子节点数量rightCount，最后其父节点及后面的所有节点数量parentCount就为n - leftCount - rightCount - 1
        //如果y要获胜，那么在三个方向上的节点数量必须有一个大于等于target = (n + 1) / 2，也就是大于一半的数量，这样x中另外两边加起来的数量就会小于y节点染色的数量

        val xNode = findX(root, x)!!
        val leftCount = childCount(xNode.left)
        val rightCount = childCount(xNode.right)
        val parentCount = n - leftCount - rightCount - 1
        val target = (n + 1) / 2
        return leftCount >= target || rightCount >= target || parentCount >= target
    }

    fun childCount(node: TreeNode?): Int {
        if (node == null) {
            return 0
        }
        val leftCount = childCount(node.left)
        val rightCount = childCount(node.right)
        return leftCount + rightCount + 1
    }

    fun findX(root: TreeNode?, x: Int): TreeNode? {
        if (root == null) {
            return null
        }
        if (root.`val` == x) {
            return root
        }
        var res = findX(root.left, x)
        if (res != null) {
            return res
        }
        res = findX(root.right, x)
        return res
    }
}