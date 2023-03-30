package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

//存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
//
//返回同样按升序排列的结果链表。
//
// 
//
//示例 1：
//
//
//输入：head = [1,1,2]
//输出：[1,2]
//示例 2：
//
//
//输入：head = [1,1,2,3,3]
//输出：[1,2,3]
// 
//
//提示：
//
//链表中节点数目在范围 [0, 300] 内
//-100 <= Node.val <= 100
//题目数据保证链表已经按升序排列
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 删除排序链表中的重复元素 {
    @Test
    public void 删除排序链表中的重复元素() {

        ListNode listNode3 = new ListNode(2);
        listNode3.next = new ListNode(2);
        listNode3.next.next = new ListNode(3);
        listNode3.next.next.next = new ListNode(3);
        listNode3.next.next.next.next = new ListNode(4);
        listNode3.next.next.next.next.next = new ListNode(4);
        listNode3.next.next.next.next.next.next = new ListNode(4);

        System.out.println("删除排序链表中的重复元素：" + deleteDuplicates(listNode3));
    }

    public ListNode deleteDuplicates(ListNode head) {
        //由于给定的链表是排好序的，因此重复的元素在链表中出现的位置是连续的，因此我们只需要对链表进行一次遍历，就可以删除重复的元素。
        //解答跟‘Ⅱ’差不多，但简单在不用考虑前驱结点，稍微少一点点计算步骤
        if (head == null || head.next == null) {
            return head;
        }

        ListNode current = head;
        while (current.next != null) {
            if (current.val == current.next.val) {
                ListNode temp = current.next;
                while (temp.next != null) {
                    if (temp.val == temp.next.val) {
                        temp = temp.next;
                    } else {
                        break;
                    }
                }
                current.next = temp.next;
            } else {
                current = current.next;
            }
        }

        //由于重复元素可以存在一个值，所以直接将重复元素的头指向next.next（相当于删除next元素）
        /*while (current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }*/
        return head;
    }

}
