package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

public class 两两交换链表中的节点 {
    @Test
    public void 两两交换链表中的节点() {
        ListNode listNode1 = new ListNode(1);
        //        listNode1.next = new ListNode(5);

        ListNode listNode2 = new ListNode(0);
        //        listNode2.next = new ListNode(4);
        //        listNode2.next.next = new ListNode(6);

        ListNode listNode3 = new ListNode(4);
        listNode3.next = new ListNode(5);
        listNode3.next.next = new ListNode(6);
        listNode3.next.next.next = new ListNode(7);
        listNode3.next.next.next.next = new ListNode(8);
        listNode3.next.next.next.next.next = new ListNode(9);

        System.out.print("两两交换链表中的节点:" + swapPairs(listNode3));
    }

    // 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
    //
    // 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
    //
    //
    //
    // 示例:
    //
    // 给定 1->2->3->4, 你应该返回 2->1->4->3.
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }

        // 迭代，双指针两两交换节点

        ListNode placeNode = head;

        ListNode tempHead = head;
        ListNode preNode = head;
        ListNode next = head.next;

        if (next != null) {
            tempHead.next = next.next;
            next.next = tempHead;

            preNode = tempHead;
            placeNode = next;
        }

        while (preNode.next != null && preNode.next.next != null) {
            tempHead = preNode.next;
            next = tempHead.next;
            tempHead.next = next.next;
            next.next = tempHead;
            preNode.next = next;
            preNode = tempHead;
        }

        return placeNode;
    }

    public ListNode swapList(ListNode head) {
        // 可以通过递归的方式实现两两交换链表中的节点。
        //
        // 递归的终止条件是链表中没有节点，或者链表中只有一个节点，此时无法进行交换。
        //
        // 如果链表中至少有两个节点，则在两两交换链表中的节点之后，原始链表的头节点变成新的链表的第二个节点，原始链表的第二个节点变成新的链表的头节点。链表中的其余节点的两两交换可以递归地实现。在对链表中的其余节点递归地两两交换之后，更新节点之间的指针关系，即可完成整个链表的两两交换。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/liang-liang-jiao-huan-lian-biao-zhong-de-jie-di-91/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapList(newHead.next);
        newHead.next = head;
        return newHead;
    }
}
