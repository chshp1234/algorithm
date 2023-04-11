package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

public class 回文链表 {

    @Test
    public void 回文链表() {
        ListNode listNode1 = new ListNode(1);
        //        listNode1.next = new ListNode(5);

        ListNode listNode2 = new ListNode(0);
        //        listNode2.next = new ListNode(4);
        //        listNode2.next.next = new ListNode(6);

        ListNode listNode3 = new ListNode(4);
        listNode3.next = new ListNode(5);
        listNode3.next.next = new ListNode(4);
        listNode3.next.next.next = new ListNode(4);

        System.out.print("回文链表:" + isPalindrome(listNode3));
    }

    ListNode dummy = new ListNode(0);
    int count = 0;

    // 请判断一个链表是否为回文链表。
    //
    // 示例 1:
    //
    // 输入: 1->2
    // 输出: false
    // 示例 2:
    //
    // 输入: 1->2->2->1
    // 输出: true
    // 进阶：
    // 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/palindrome-linked-list
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean isPalindrome(ListNode head) {

        // 方法一：递归
        // 设置全局变量，计算层数（链表节点数）count，当到尾部时，令count = count >> 1，表示需要判断回文的次数。
        // 设置全局变量dummy，dummy.next = head，每次递归返回之前，令dummy = dummy.next，用于上一层判断时头结点指向下一个节点。

        // 方法二：双指针
        // 避免使用 O(n) 额外空间的方法就是改变输入。
        //
        // 我们可以将链表的后半部分反转（修改链表结构），然后将前半部分和后半部分进行比较。比较完成后我们应该将链表恢复原样。
        // 虽然不需要恢复也能通过测试用例，但是使用该函数的人通常不希望链表结构被更改。
        //
        // 该方法虽然可以将空间复杂度降到 O(1)，但是在并发环境下，该方法也有缺点。
        // 在并发环境下，函数运行时需要锁定其他线程或进程对链表的访问，因为在函数执行过程中链表会被修改。
        //
        // 算法
        //
        // 整个流程可以分为以下五个步骤：
        //
        // 找到前半部分链表的尾节点。
        // 反转后半部分链表。
        // 判断是否回文。
        // 恢复链表。
        // 返回结果。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/palindrome-linked-list/solution/hui-wen-lian-biao-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        // 思路三：将值复制到数组中后用双指针法
        // 复制链表值到数组列表中。
        // 使用双指针法判断是否为回文。

        dummy.next = head;
        return isPalindromeListNode(head);
    }

    public boolean isPalindromeListNode(ListNode end) {

        if (end == null) {
            count = count >> 1;
            return true;
        }

        count++;

        if (isPalindromeListNode(end.next)) {
            if (count == 0) {
                return true;
            }

            if (dummy.next.val == end.val) {
                count--;
                dummy = dummy.next;
                return true;
            }
        }
        return false;
    }
}
