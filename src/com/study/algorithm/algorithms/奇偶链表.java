package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

// 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
//
// 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
//
// 示例 1:
//
// 输入: 1->2->3->4->5->NULL
// 输出: 1->3->5->2->4->NULL
// 示例 2:
//
// 输入: 2->1->3->5->6->4->7->NULL
// 输出: 2->3->6->7->1->5->4->NULL
// 说明:
//
// 应当保持奇数节点和偶数节点的相对顺序。
// 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/odd-even-linked-list
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 奇偶链表 {

    @Test
    public void 奇偶链表() {

        ListNode listNode3 = new ListNode(1);
        listNode3.next = new ListNode(2);
        listNode3.next.next = new ListNode(3);
        listNode3.next.next.next = new ListNode(4);
        listNode3.next.next.next.next = new ListNode(5);
        listNode3.next.next.next.next.next = new ListNode(6);

        System.out.print("奇偶链表:" + oddEvenList(listNode3));
    }

    public ListNode oddEvenList(ListNode head) {
        // 分离后合并
        // 将原链表按奇偶顺序分离成两个链表，最后在合并
        // 按照规律，奇数下标的链表的next节点，指向的是偶数下标的链表；偶数下标的链表的next节点，指向的是奇数下标的链表
        // 所以使用两个指针，分别指向奇偶链表，并且每次从其把next节点赋值给另一个的next
        // 直到其中有一个指针指向null时，则遍历到链表尾部
        // 最后把奇数链表的尾部的next节点指向偶数链表的头部即可

        if (head == null) {
            return null;
        }

        // 奇偶链表指针
        ListNode odd = head;
        ListNode even = head.next;

        // 奇数链表尾部
        ListNode oddTail = odd;
        // 偶数链表头部
        ListNode evenHead = even;

        while (true) {
            if (even != null) {
                odd.next = even.next;
                oddTail = odd;
                odd = odd.next;
            } else {
                break;
            }

            if (odd != null) {
                even.next = odd.next;
                even = even.next;
            } else {
                break;
            }
        }

        // 把奇数链表的尾部的next节点指向偶数链表的头部
        if (oddTail.next != null) {
            oddTail.next.next = evenHead;
        } else {
            oddTail.next = evenHead;
        }

        return head;
    }
}
