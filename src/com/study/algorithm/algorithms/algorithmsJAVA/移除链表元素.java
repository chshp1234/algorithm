package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.ListNode;

//给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
// 
//
//示例 1：
//
//
//输入：head = [1,2,6,3,4,5,6], val = 6
//输出：[1,2,3,4,5]
//示例 2：
//
//输入：head = [], val = 1
//输出：[]
//示例 3：
//
//输入：head = [7,7,7,7], val = 7
//输出：[]
// 
//
//提示：
//
//列表中的节点在范围 [0, 104] 内
//1 <= Node.val <= 50
//0 <= k <= 50
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/remove-linked-list-elements
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 移除链表元素 {
    public ListNode removeElements(ListNode head, int val) {
        //迭代，双指针
        //指定一个前节点和当前节点
        //遍历时注双指针向右移动，但要注意，删除指定节点后，前指针不移动
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;

        while (head != null) {
            if (head.val == val) {
                pre.next = head.next;
            } else {
                pre = head;
            }
            head = head.next;
        }
        return dummy.next;
    }

    public ListNode removeElementsByRecursion(ListNode head, int val) {
        //链表的定义具有递归的性质，因此链表题目常可以用递归的方法求解。这道题要求删除链表中所有节点值等于特定值的节点，可以用递归实现。
        //
        //对于给定的链表，首先对除了头节点 head 以外的节点进行删除操作，然后判断 head 的节点值是否等于给定的 val。
        //如果 head 的节点值等于 val，则 head 需要被删除，因此删除操作后的头节点为 head.next；
        //如果 head 的节点值不等于 val，则 head 保留，因此删除操作后的头节点还是 head。
        //上述过程是一个递归的过程。
        //
        //递归的终止条件是 head 为空，此时直接返回 head。
        //当 head 不为空时，递归地进行删除操作，然后判断 head 的节点值是否等于 val 并决定是否要删除 head。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/remove-linked-list-elements/solution/yi-chu-lian-biao-yuan-su-by-leetcode-sol-654m/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        if (head == null) {
            return head;
        }
        head.next = removeElementsByRecursion(head.next, val);
        return head.val == val ? head.next : head;
    }
}
