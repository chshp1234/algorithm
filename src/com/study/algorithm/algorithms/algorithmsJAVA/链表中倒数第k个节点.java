package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

// 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
//
//
//
// 示例：
//
// 给定一个链表: 1->2->3->4->5, 和 k = 2.
//
// 返回链表 4->5.
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 链表中倒数第k个节点 {

    @Test
    public void 链表中倒数第k个节点() {

        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(2);
        listNode1.next.next = new ListNode(3);
        listNode1.next.next.next = new ListNode(4);
        listNode1.next.next.next.next = new ListNode(5);
        System.out.println("链表中倒数第k个节点:" + getKthFromEnd(listNode1, 2));
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        // 双指针：
        // 初始化，前指针 former 、后指针 latter ，双指针都指向头节点 head
        // 先遍历k次，每次后指针指向节点下一位，找到后指针结束位置
        // 然后前后指正一起向后移动，当后指针遍历到链表尾部时，返回前指针节点
        ListNode tail = head;
        for (int i = 0; i < k - 1; i++) {
            tail = tail.next;
        }

        while (tail.next != null) {
            tail = tail.next;
            head = head.next;
        }
        return head;
    }
}
