package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

public class 删除链表的倒数第N个节点 {
    @Test
    public void 删除链表的倒数第N个节点() {
        ListNode listNode1 = new ListNode(1);
        //        listNode1.next = new ListNode(5);

        ListNode listNode2 = new ListNode(0);
        //        listNode2.next = new ListNode(4);
        //        listNode2.next.next = new ListNode(6);

        ListNode listNode3 = new ListNode(4);
        listNode3.next = new ListNode(5);
        listNode3.next.next = new ListNode(6);

        removeNthFromEnd(listNode3, 3);
        System.out.print("删除链表的倒数第N个节点:" + listNode3);
    }

    // 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
    //
    // 示例：
    //
    // 给定一个链表: 1->2->3->4->5, 和 n = 2.
    //
    // 当删除了倒数第二个节点后，链表变为 1->2->3->5.
    // 说明：
    //
    // 给定的 n 保证是有效的。
    //
    // 进阶：
    //
    // 你能尝试使用一趟扫描实现吗？
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 递归
        // 借用栈的先进后出，最后一个进入栈的节点，因为没有后续节点，所以将其弹出栈顶，当弹出倒数第n个节点时，
        // 即为将要删除的节点

        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        removeNthFromEnd(dummyNode, head, n);
        return dummyNode.next;
    }

    public int removeNthFromEnd(ListNode pre, ListNode root, int n) {
        // 空节点，出栈，序号为0
        if (root == null) {
            return 0;
        }

        // 计算当前节点为第几个节点（从后往前数）
        int s = removeNthFromEnd(root, root.next, n) + 1;
        if (s == n) {
            pre.next = root.next;
        }
        return s;
    }

    public ListNode removeNthFromEndRecursion(ListNode head, int n) {
        // 迭代，双指针
        // 由于题目给定n是有意义的，所以一开始可以定义pre和end节点，经过一次长度为n的循环，定位end节点，使pre节点和
        // end节点之间的间隔相差n。
        // 再对pre和end进行后移，当end指向最后一个节点时，便利到链表尾部，跳出循环，此时的pre.next节点即为要删除的节点。
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        ListNode end = dummyNode;

        for (int i = 0; i < n; i++) {
            end = end.next;
        }

        while (end.next != null) {
            pre = pre.next;
            end = end.next;
        }

        pre.next = pre.next.next;

        return dummyNode.next;
    }
}
