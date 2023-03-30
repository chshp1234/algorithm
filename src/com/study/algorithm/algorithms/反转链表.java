package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

// 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
//
//
//
// 示例:
//
// 输入: 1->2->3->4->5->NULL
// 输出: 5->4->3->2->1->NULL
//
//
// 限制：
//
// 0 <= 节点个数 <= 5000
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 反转链表 {

    @Test
    public void 反转链表() {

        ListNode listNode3 = new ListNode(5);
        listNode3.next = new ListNode(4);
        listNode3.next.next = new ListNode(3);
        listNode3.next.next.next = new ListNode(2);
        listNode3.next.next.next.next = new ListNode(1);
        System.out.print("反转链表:翻转前=" + listNode3 + "翻转后=" + reverseList(listNode3));
    }

    public ListNode reverseList(ListNode head) {
        // 双指针
        // 维护头尾两个指针head、next。head代表翻转后的头指针，next代表下一个待翻转的节点。
        // 每次使得尾指针下一个节点（若不为空）成为头结点。
        // 不断翻转，直到next节点为空，即遍历到链表尾部，结束翻转。

        if (head == null) {
            return null;
        }

        ListNode next = head.next;

        // 翻转后头指针将成为尾指针。
        head.next = null;
        ListNode temp;

        // 判断尾指针之前的next节点是否为空，为空则说明遍历到链表尾部。
        while (next != null) {
            temp = next.next;
            // next节点的下一个节点指向头结点head，此时next节点成为head。
            next.next = head;
            head = next;
            next = temp;
        }

        return head;
    }

    public ListNode reverseByRecursion(ListNode head) {
        // 递归
        // 利用栈的后进先出的性质，将链表节点依次入栈（也可以使用数据结构‘栈’）从链表尾部开始，依次翻转两个节点的顺序。直到递归结束，返回头结点。
        // 头结点，即为未翻转时的链表尾节点。

        // 应该单独开辟一个递归方法，将此判断抛出
        if (head == null) {
            return null;
        }

        // 遍历到链表尾部时（head.next == null），此时head节点将成为翻转后的头结点，返回head
        if (head.next == null) {
            return head;
        }

        // 获取并翻转链表
        ListNode reverseHead = reverseByRecursion(head.next);

        // 翻转head和head.next节点的指向顺序
        head.next.next = head;
        head.next = null;
        return reverseHead;
    }
}
