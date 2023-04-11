package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

//给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
//
//你应当保留两个分区中每个节点的初始相对位置。
//
// 
//
//示例：
//
//输入：head = 1->4->3->2->5->2, x = 3
//输出：1->2->2->4->3->5
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/partition-list
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 分隔链表 {
    @Test
    public void 分隔链表() {

        ListNode listNode1 = new ListNode(5);
        listNode1.next = new ListNode(4);
        listNode1.next.next = new ListNode(7);
        listNode1.next.next.next = new ListNode(2);
        listNode1.next.next.next.next = new ListNode(10);
        System.out.println("分隔链表:" + partition(listNode1, 4));
    }

    public ListNode partition(ListNode head, int x) {
        //方法一：模拟
        //遍历链表，分别拼接小于x的数和大于等于x的数到两个链表中
        //再把小于x数的链表尾部拼接上大于等于x的链表的头部即可

        //方法二：双指针
        //维护两个指针，head指向小于x数的链表尾部，h指向大于等于x数的链表尾部
        //对链表进行遍历，维护两个指针，并找到下一个小于x的数的链表尾部
        //将后面的小于x的链表拼接到前面一个小于x的链表的后面，继续遍历链表重复以上步骤直到遍历完毕
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while (head.next != null && head.next.val < x) {
            head = head.next;
        }

        ListNode t = head.next;
        ListNode h;
        ListNode temp;

        while (t != null) {
            while (t.next != null && t.next.val >= x) {
                t = t.next;
            }
            h = t;
            while (t.next != null && t.next.val < x) {
                t = t.next;
            }
            temp = t.next;
            t.next = head.next;
            head.next = h.next;
            h.next = temp;
            head = t;
            t = temp;
        }

        return dummy.next;
    }
}
