package com.study.algorithm.algorithms.algorithmsKT

import com.study.algorithm.algorithms.structure.ListNode

//给你一个链表的头节点 head 。
//
//移除每个右侧有一个更大数值的节点。
//
//返回修改后链表的头节点 head 。
//
//
//
//示例 1：
//
//
//
//输入：head = [5,2,13,3,8]
//输出：[13,8]
//解释：需要移除的节点是 5 ，2 和 3 。
//- 节点 13 在节点 5 右侧。
//- 节点 13 在节点 2 右侧。
//- 节点 8 在节点 3 右侧。
//示例 2：
//
//输入：head = [1,1,1,1]
//输出：[1,1,1,1]
//解释：每个节点的值都是 1 ，所以没有需要移除的节点。
//
//
//提示：
//
//给定列表中的节点数目在范围 [1, 105] 内
//1 <= Node.val <= 105
class 从链表中移除节点 {

    //单调栈
    //根据题意，当节点右侧有更大的节点时，将该节点删除。那么删除节点后，整个链表将会单调递减。
    //1.将节点依次加入栈中；
    //2.取出栈顶节点（反向遍历），如果该节点值大于上一个取出的节点，则将该节点加入链表中。否则抛弃该节点。
    //当然，也可以在将节点加入栈的过程中就维护节点，如果加入的节点值大于栈中的节点值，则将栈中的节点出栈，直到遇到大于该节点时，再将该节点入栈，最后返回栈中元素即可。
    fun removeNodes(head: ListNode): ListNode? {
        val dummy = ListNode(Int.MAX_VALUE)
        dummy.next = head
        rmNodes(dummy)
        return dummy.next
    }

    //递归
    //判断返回的节点值是否大于该节点值
    //如果大于：则，该节点抛弃，继续返回之前返回的节点值
    //如果小于等于：则将该节点的next设为返回的节点，并返回该节点
    fun rmNodes(node: ListNode): ListNode {
        return node.next?.let {
            val next = rmNodes(it)
            if (next.`val` > node.`val`) {
                node.next = null
                next
            } else {
                node.next = next
                node
            }
        } ?: node
    }
}