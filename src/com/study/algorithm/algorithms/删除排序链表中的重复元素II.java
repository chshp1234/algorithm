package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

//存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
//
//返回同样按升序排列的结果链表。
//
// 
//
//示例 1：
//
//
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
//示例 2：
//
//
//输入：head = [1,1,1,2,3]
//输出：[2,3]
// 
//
//提示：
//
//链表中节点数目在范围 [0, 300] 内
//-100 <= Node.val <= 100
//题目数据保证链表已经按升序排列
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 删除排序链表中的重复元素II {

    @Test
    public void 删除排序链表中的重复元素II() {

        ListNode listNode3 = new ListNode(1);
        listNode3.next = new ListNode(2);
        listNode3.next.next = new ListNode(3);
        listNode3.next.next.next = new ListNode(3);
        listNode3.next.next.next.next = new ListNode(4);
        listNode3.next.next.next.next.next = new ListNode(4);
        listNode3.next.next.next.next.next.next = new ListNode(5);

        System.out.println("删除排序链表中的重复元素II：" + deleteDuplicates(listNode3));
    }

    public ListNode deleteDuplicates(ListNode head) {
        //由于给定的链表是排好序的，因此重复的元素在链表中出现的位置是连续的
        //我们只要找的重复节点的头尾，将头结点的前一个连上重复节点中尾节点的下一个节点
        //因此我们只需要对链表进行一次遍历，就可以删除重复的元素。由于链表的头节点可能会被删除，因此我们需要额外使用一个哑节点（dummy node）指向链表的头节点。
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        //重复节点的前驱节点
        ListNode pre = dummy;
        dummy.next = head;
        while (pre.next.next != null) {
            if (pre.next.val == pre.next.next.val) {
                //若出现重复节点
                ListNode temp = pre.next.next;
                //找到重复节点的尾节点
                while (temp.next != null) {
                    if (temp.val == temp.next.val) {
                        temp = temp.next;
                    } else {
                        break;
                    }
                }
                //将前驱节点的next指向重复尾节点的next
                pre.next = temp.next;
                if (pre.next == null) {
                    break;
                }
            } else {
                //不是重复节点，则更新前驱结点
                pre = pre.next;
            }
        }
        return dummy.next;
    }

}
