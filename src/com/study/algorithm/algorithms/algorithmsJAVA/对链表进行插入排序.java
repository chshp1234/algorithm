package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

// 插入排序算法：
//
// 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
// 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
// 重复直到所有输入数据插入完为止。
//
//
// 示例 1：
//
// 输入: 4->2->1->3
// 输出: 1->2->3->4
// 示例 2：
//
// 输入: -1->5->3->4->0
// 输出: -1->0->3->4->5
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/insertion-sort-list
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 对链表进行插入排序 {

    @Test
    public void 对链表进行插入排序() {

        ListNode listNode3 = new ListNode(8);
        listNode3.next = new ListNode(2);
        listNode3.next.next = new ListNode(5);
        listNode3.next.next.next = new ListNode(4);
        listNode3.next.next.next.next = new ListNode(3);
        listNode3.next.next.next.next.next = new ListNode(7);

        System.out.print("对链表进行插入排序:" + insertionSortList(listNode3));
    }

    public ListNode insertionSortList(ListNode head) {

        // 头插
        // 由于是单链表，只有指向后一个节点的指针，不方便做从后往前插入，那就换个方向，从前往后插入，时间复杂度都是一样的。
        // 1.判断链表是否为空，判断链表是否为空，判断链表是否为空，重点~
        // 2.创建一个dummy节点，dummy.next=head，方便后续的插入操作。
        // 3.循环遍历每个节点
        // 4.其中可优先判断head.val和head.next.val的大小，只有当head.val较大时，才进行后续的查找插入操作。
        // 5.查找插入节点

        // 注：这里不管遍历链表还是寻找插入点时，都是用的list.next进行判断，
        // 这样做为了找到节点时，可方便的将list.next从链表中断开，并重新接上前后节点
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode temp;
        // 每次判断下一个节点head.next是否为空，不为空才进行插入操作
        while (head.next != null) {
            // 若head.val较小，则此节点已在正确的位置，无需后续查找插入
            if (head.val <= head.next.val) {
                head = head.next;
                continue;
            }
            temp = head.next;
            head.next = temp.next;
            temp.next = null;

            insertion(dummy, temp);
        }
        return dummy.next;
    }

    // 查找位置并插入
    public void insertion(ListNode head, ListNode insert) {
        
        // 当head.next.val > insert.val时，insert的位置就在head和head.next之间
        while (head.next.val < insert.val) {
            head = head.next;
        }
        insert.next = head.next;
        head.next = insert;
    }
}
