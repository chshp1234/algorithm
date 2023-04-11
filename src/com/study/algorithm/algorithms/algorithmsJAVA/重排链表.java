package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

import java.util.LinkedList;

public class 重排链表 {

    @Test
    public void 重排链表() {
        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(2);
        listNode1.next.next = new ListNode(3);
        listNode1.next.next.next = new ListNode(4);
        //        listNode1.next.next.next.next = new ListNode(5);
        reorderList(listNode1);
        System.out.println("重排链表:" + listNode1);
    }

    // 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
    // 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
    //
    // 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
    //
    // 示例 1:
    //
    // 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
    // 示例 2:
    //
    // 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/reorder-list
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public void reorderListByDequeue(ListNode head) {
        // 通过一个辅助双向队列存储单向链表的所有节点
        // 再依次从双向队列头尾中取出节点进行拼接即可

        LinkedList<ListNode> dequeue = new LinkedList<>();
        ListNode temp = head;
        //        ListNode pre = head;
        while (temp != null) {
            dequeue.offer(temp);
            temp = temp.next;
            //            pre.next = null;
            //            pre = temp;
        }

        ListNode dummy = new ListNode(0);

        while (!dequeue.isEmpty()) {
            dummy.next = dequeue.pollFirst();
            dummy = dummy.next;
            dummy.next = dequeue.pollLast();
            dummy = dummy.next;
        }
    }

    public void reorderList(ListNode head) {
        // 方法二：寻找链表中点 + 链表逆序 + 合并链表
        // 注意到目标链表即为将原链表的左半端和反转后的右半端合并后的结果。
        //
        // 这样我们的任务即可划分为三步：
        //
        // 找到原链表的中点（参考「876. 链表的中间结点」）。
        //
        // 我们可以使用快慢指针来 O(N) 地找到链表的中间节点。
        // 将原链表的右半端反转（参考「206. 反转链表」）。
        //
        // 我们可以使用迭代法实现链表的反转。
        // 将原链表的两端合并。
        //
        // 因为两链表长度相差不超过 1，因此直接合并即可。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/reorder-list/solution/zhong-pai-lian-biao-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        if (head == null) {
            return;
        }
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    public ListNode middleNode(ListNode head) {
        // 快慢指针，寻找中间节点
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
        // 双指针翻转链表
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public void mergeList(ListNode l1, ListNode l2) {
        // 合并两个链表
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }
}
