package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

//反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
//
//说明:
//1 ≤ m ≤ n ≤ 链表长度。
//
//示例:
//
//输入: 1->2->3->4->5->NULL, m = 2, n = 4
//输出: 1->4->3->2->5->NULL
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 反转链表II {
    int anInt;

    @Test
    public void 反转链表II() {

        ListNode listNode3 = new ListNode(5);
        listNode3.next = new ListNode(4);
        listNode3.next.next = new ListNode(3);
        listNode3.next.next.next = new ListNode(2);
        listNode3.next.next.next.next = new ListNode(1);
        System.out.print("反转链表II:翻转前=" + listNode3 + "翻转后=" + reverseBetween(listNode3, 3, 3));
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        ListNode tail;
        int      index = 1;
        while (index < left) {
            head = head.next;
            index++;
        }
        tail = head.next;
        ListNode temp;
        while (index < right) {
            temp = tail.next;
            tail.next = tail.next.next;
            temp.next = head.next;
            head.next = temp;
            index++;
        }
        return dummy.next;
    }

}