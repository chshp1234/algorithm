package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

public class K个一组翻转链表 {

    @Test
    public void K个一组翻转链表() {

        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(2);
        listNode1.next.next = new ListNode(3);
        listNode1.next.next.next = new ListNode(4);
        listNode1.next.next.next.next = new ListNode(5);
        System.out.println("K个一组翻转链表:" + reverseKGroup(listNode1, 2));
    }

    // 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
    //
    // k 是一个正整数，它的值小于或等于链表的长度。
    //
    // 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
    //
    //
    //
    // 示例：
    //
    // 给你这个链表：1->2->3->4->5
    //
    // 当 k = 2 时，应当返回: 2->1->4->3->5
    //
    // 当 k = 3 时，应当返回: 3->2->1->4->5
    //
    //
    //
    // 说明：
    //
    // 你的算法只能使用常数的额外空间。
    // 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public ListNode reverseKGroup(ListNode root, int k) {

        ListNode tail = root;
        ListNode head = root;

        ListNode temp1;
        ListNode temp2;

        int i = 0;
        k = k - 1;

        // 翻转链表（K次一组）
        // 两两翻转，当前链表头翻转后为链表尾部，链表尾部翻转后为链表头
        // 如1->2->3->4链表
        // 链表头和尾为1
        // 第一次翻转：(头)2->1（尾）->3->4
        // 第二次翻转：3->2->1->4
        // 第三次翻转：4->3->2->1，翻转结束
        for (; i < k; i++) {
            temp2 = tail.next;

            if (temp2 == null) {
                tail = head;
                while (tail.next != null) {
                    temp2 = tail.next;
                    tail.next = temp2.next;
                    temp2.next = head;
                    head = temp2;
                }
                return root;
            }

            tail.next = temp2.next;
            temp2.next = head;
            head = temp2;
        }

        root = head;

        temp1 = tail;

        head = tail.next;
        tail = tail.next;

        // 翻转思路如上
        while (tail != null) {
            i = 0;
            for (; i < k; i++) {
                temp2 = tail.next;

                // 边界条件，当前数目不为K，则把之前翻转的再重新翻转回来
                if (temp2 == null) {
                    tail = head;
                    while (tail.next != null) {
                        temp2 = tail.next;
                        tail.next = temp2.next;
                        temp2.next = head;
                        head = temp2;
                    }
                    temp1.next = head;
                    return root;
                }

                tail.next = temp2.next;
                temp2.next = head;
                head = temp2;
            }

            temp1.next = head;

            temp1 = tail;

            head = tail.next;
            tail = tail.next;
        }

        return root;
    }
}
