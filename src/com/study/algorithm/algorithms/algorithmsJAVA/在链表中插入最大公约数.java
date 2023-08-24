package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.ListNode;

//给你一个链表的头 head ，每个结点包含一个整数值。
//
//在相邻结点之间，请你插入一个新的结点，结点值为这两个相邻结点值的 最大公约数 。
//
//请你返回插入之后的链表。
//
//两个数的 最大公约数 是可以被两个数字整除的最大正整数。
//
//
//
//示例 1：
//
//
//
//输入：head = [18,6,10,3]
//输出：[18,6,6,2,10,1,3]
//解释：第一幅图是一开始的链表，第二幅图是插入新结点后的图（蓝色结点为新插入结点）。
//- 18 和 6 的最大公约数为 6 ，插入第一和第二个结点之间。
//- 6 和 10 的最大公约数为 2 ，插入第二和第三个结点之间。
//- 10 和 3 的最大公约数为 1 ，插入第三和第四个结点之间。
//所有相邻结点之间都插入完毕，返回链表。
//示例 2：
//
//
//
//输入：head = [7]
//输出：[7]
//解释：第一幅图是一开始的链表，第二幅图是插入新结点后的图（蓝色结点为新插入结点）。
//没有相邻结点，所以返回初始链表。
//
//
//提示：
//
//链表中结点数目在 [1, 5000] 之间。
//1 <= Node.val <= 1000
public class 在链表中插入最大公约数 {
    //模拟
    //用辗转相除法求两个整数的最大公约数
    //遍历时，前驱节点和next节点不为空，则计算两个节点的值的最大公约数，并插入其两个节点中
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode pre = head;
        ListNode next;
        while (pre.next != null) {
            next = new ListNode(GCD(pre.val, pre.next.val));
            next.next = pre.next;
            pre.next = next;
            pre = pre.next.next;
        }

        return head;
    }

    public int GCD(int a, int b) {
        if (a < b) {
            return GCD(b, a);
        }
        int gcd = a % b;
        while (gcd != 0) {
            a = b;
            b = gcd;
            gcd = a % b;
        }
        return b;
    }
}
