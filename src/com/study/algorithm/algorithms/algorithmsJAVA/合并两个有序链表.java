package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

public class 合并两个有序链表 {

    @Test
    public void 合并K个排序链表() {
        ListNode listNode0 = null;

        ListNode listNode1 = new ListNode(1);
        //        listNode1.next = new ListNode(5);

        ListNode listNode2 = new ListNode(0);
        //        listNode2.next = new ListNode(4);
        //        listNode2.next.next = new ListNode(6);

        ListNode listNode3 = new ListNode(4);
        listNode3.next = new ListNode(5);
        listNode3.next.next = new ListNode(6);

        System.out.println("合并K个排序链表：" + mergeTwoLists(listNode1, listNode2));
    }

    /**
     * 合并两个有序链表 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * <p>示例：
     *
     * <p>输入：1->2->4, 1->3->4 输出：1->1->2->3->4->4
     */
    public ListNode mergeTwoLists(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        }

        ListNode listNode;
        if (left.val < right.val) {
            listNode = left;
            left = left.next;
        } else {
            listNode = right;
            right = right.next;
        }
        listNode.next = null;

        ListNode temp = listNode;
        while (left != null && right != null) {
            if (left.val < right.val) {
                temp.next = left;
                left = left.next;
            } else {
                temp.next = right;
                right = right.next;
            }
            temp = temp.next;
        }

        if (left == null) {
            temp.next = right;
        } else {
            temp.next = left;
        }

        return listNode;
    }
}
