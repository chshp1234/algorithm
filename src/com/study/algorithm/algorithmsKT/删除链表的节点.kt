package com.study.algorithm.algorithmsKT

import com.study.algorithm.algorithms.structure.ListNode
import org.junit.Test

class 删除链表的节点 {
    @Test
    fun 删除链表的节点() {
        val list = ListNode(1)
        list.next = ListNode(2)
        list.next.next = ListNode(3)
        list.next.next.next = ListNode(4)
        list.next.next.next.next = ListNode(5)
        println("删除链表的节点:${deleteNode(null, 3)}")
    }

    fun deleteNode(head: ListNode?, `val`: Int): ListNode? {
        //双指针
        //一趟遍历链表，当找到要删除的节点时，将当前节点的前一个节点指向当前节点的next节点
        //所以需要两个指针，记录当前节点和当前节点的上一个节点
        //可以使用一个临时节点dummy，将dummy.next指向head
        return head?.let {
            val dummy = ListNode(0)
            dummy.next = head
            var pre = dummy
            var current = head

            while (current != null) {
                if (current.`val` == `val`) {
                    pre.next = current.next
                    break
                }
                pre = current
                current = current.next
            }

            dummy.next
        } ?: head


    }
}