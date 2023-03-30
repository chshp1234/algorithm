package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

//给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
//
// 
//
//示例 1：
//
//
//输入：head = [1,2,3,4,5], k = 2
//输出：[4,5,1,2,3]
//示例 2：
//
//
//输入：head = [0,1,2], k = 4
//输出：[2,0,1]
// 
//
//提示：
//
//链表中节点的数目在范围 [0, 500] 内
//-100 <= Node.val <= 100
//0 <= k <= 2 * 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/rotate-list
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 旋转链表 {

    @Test
    public void 旋转链表() {

        ListNode listNode3 = new ListNode(1);
        listNode3.next = new ListNode(2);
        listNode3.next.next = new ListNode(3);
        listNode3.next.next.next = new ListNode(4);
        listNode3.next.next.next.next = new ListNode(5);
        listNode3.next.next.next.next.next = new ListNode(6);
        listNode3.next.next.next.next.next.next = new ListNode(7);

        System.out.println("旋转链表：" + rotateRight(listNode3, 15));
    }

    public ListNode rotateRight(ListNode head, int k) {
        //首先计算链表长度len，并记录链表尾节点tail
        //右移k次后，相当于头结点head右移k%len个位置，那么此时的尾节点newTail就为下标为(len-k%len)处的节点
        //另tail与head相连
        //新的头结点newHead= newTail.next
        //最后令newTail.next=null，使其成为新的尾节点

        //方法二：直接使尾节点tail与头结点head相连，tail.next=head，使其成为环形链表
        //找出新的尾节点newTail，新的头结点newHead=newTail.next，最后newTail.next=null
        //与方法一一样，但这里先收尾相连后，查找newTail时，可以腾出一个引用的空间
        if (head == null || head.next == null) {
            return head;
        }
        int      len  = 2;
        ListNode tail = head.next;
        while (tail.next != null) {
            len++;
            tail = tail.next;
        }
        int      index = len - k % len;
        ListNode temp  = head;
        while (index > 1) {
            temp = temp.next;
            index--;
        }
        tail.next = head;
        head = temp.next;
        temp.next = null;
        return head;
    }
}
