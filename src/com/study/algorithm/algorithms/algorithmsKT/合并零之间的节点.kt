package com.study.algorithm.algorithms.algorithmsKT

import com.study.algorithm.algorithms.structure.ListNode

//给你一个链表的头节点 head ，该链表包含由 0 分隔开的一连串整数。链表的 开端 和 末尾 的节点都满足 Node.val == 0 。
//
//对于每两个相邻的 0 ，请你将它们之间的所有节点合并成一个节点，其值是所有已合并节点的值之和。然后将所有 0 移除，修改后的链表不应该含有任何 0 。
//
// 返回修改后链表的头节点 head 。
//
// 
//
//示例 1：
//
//
//输入：head = [0,3,1,0,4,5,2,0]
//输出：[4,11]
//解释：
//上图表示输入的链表。修改后的链表包含：
//- 标记为绿色的节点之和：3 + 1 = 4
//- 标记为红色的节点之和：4 + 5 + 2 = 11
//示例 2：
//
//
//输入：head = [0,1,0,3,0,2,2,0]
//输出：[1,3,4]
//解释：
//上图表示输入的链表。修改后的链表包含：
//- 标记为绿色的节点之和：1 = 1
//- 标记为红色的节点之和：3 = 3
//- 标记为黄色的节点之和：2 + 2 = 4
// 
//
//提示：
//
//列表中的节点数目在范围 [3, 2 * 105] 内
//0 <= Node.val <= 1000
//不 存在连续两个 Node.val == 0 的节点
//链表的 开端 和 末尾 节点都满足 Node.val == 0
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/merge-nodes-in-between-zeros
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 合并零之间的节点 {
    fun mergeNodes(head: ListNode): ListNode? {
        //一趟遍历,模拟
        //从头节点一趟遍历,维护更新'0'节点直接的节点的值的和sum ,和前驱结点pre
        //当遇到'0'节点时,说明到达一个分界点,新建一个节点,将值设为当前的和,并将前一个节点pre.next指向当前的节点,更新pre=pre.next
        //继续遍历节点,直到节点为空
        val dummy = ListNode(0).apply {
            next = head
        }
        var sum = 0
        var pre = dummy
        var node = head.next
        while (node != null) {
            if (node.`val` == 0) {
                pre.next = ListNode(sum)
                pre = pre.next
                sum = 0
            } else {
                sum += node.`val`
            }
            node = node.next
        }
        return dummy.next
    }
}