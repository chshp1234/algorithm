package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

public class 两数相加 {

    @Test
    public void 两数相加() {
        ListNode listNode1 = new ListNode(1);
        //        listNode1.next = new ListNode(2);
        //        listNode1.next.next = new ListNode(4);
        //        listNode1.next.next.next = new ListNode(3);

        ListNode listNode2 = new ListNode(9);
        listNode2.next = new ListNode(9);
        //        listNode2.next.next = new ListNode(4);
        System.out.println("两数相加:" + addTwoNumbers(listNode1, listNode2));
    }

    // 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
    //
    // 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
    //
    // 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
    //
    // 示例：
    //
    // 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    // 输出：7 -> 0 -> 8
    // 原因：342 + 465 = 807
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/add-two-numbers
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 根据题意，当前链表和结果链表都表示数字的逆序，所以可以直接对当前两链表的val值进行相加（注意若为空则应取0），
        // 需要注意当前相加的值大于9的情况，因为每个节点只能存储一位数，所以需要进一位操作。
        // 这里先创建一个dummyNode（哑巴节点）作为起始节点，对dummyNode.next进行操作，结果也返回dummyNode.next（如果没有哑结点，则必须编写额外的条件语句来初始化表头的值。）
        int left = 0, right = 0;
        ListNode dummyNode = new ListNode(0);
        int carry = 0;
        ListNode tempNode = dummyNode;
        int sum = 0;

        while (l1 != null || l2 != null) {
            if (l1 != null) {
                left = l1.val;
                l1 = l1.next;
            } else {
                left = 0;
            }

            if (l2 != null) {
                right = l2.val;
                l2 = l2.next;
            } else {
                right = 0;
            }

            sum = left + right + carry;
            tempNode.next = new ListNode(sum % 10);
            carry = sum / 10;
            tempNode = tempNode.next;
        }

        if (carry > 0) {
            tempNode.next = new ListNode(1);
        }

        return dummyNode.next;
    }
}
