package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

import java.util.Stack;

@Deprecated
public class 两数相加II {

    @Test
    public void 两数相加II() {
        ListNode listNode1 = new ListNode(7);
        listNode1.next = new ListNode(2);
        listNode1.next.next = new ListNode(4);
        listNode1.next.next.next = new ListNode(3);

        ListNode listNode2 = new ListNode(5);
        listNode2.next = new ListNode(6);
        listNode2.next.next = new ListNode(4);
        System.out.println("两数相加II:" + addTwoNumbers(listNode1, listNode2));
    }
    /**
     * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
     *
     * <p>你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     *
     * <p>
     *
     * <p>进阶：
     *
     * <p>如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
     *
     * <p>
     *
     * <p>示例：
     *
     * <p>输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4) 输出：7 -> 8 -> 0 -> 7
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        ListNode res = null;
        int c = 0;
        while (!s1.isEmpty() || !s2.isEmpty() || c > 0) {
            int sum = (s1.isEmpty() ? 0 : s1.pop()) + (s2.isEmpty() ? 0 : s2.pop()) + c;
            ListNode n = new ListNode(sum % 10);
            c = sum / 10;
            n.next = res;
            res = n;
        }
        return res;
    }
    /*todo 未完成（使用递归完成）*/
    public boolean add(ListNode l1, ListNode l2) {
        if (l1.next == null && l2.next == null) {
            l1.val = l1.val + l2.val;
            if (l1.val > 10) {
                l1.val = l1.val % 10;
                return true;
            }
        } else if (l1.next == null) {
            if (add(l1, l2.next)) {}

        } else if (l2.next == null) {
            if (add(l1.next, l2)) {}

        } else {
            add(l1.next, l2.next);
        }
        return false;
    }
}
