package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 合并K个排序链表 {
    @Test
    public void 合并K个排序链表() {
        ListNode listNode0 = null;

        ListNode listNode1 = new ListNode(1);
        //        listNode1.next = new ListNode(5);

        ListNode listNode2 = new ListNode(0);
        //        listNode2.next = new ListNode(4);
        //        listNode2.next.next = new ListNode(6);

        ListNode listNode3 = new ListNode(4);
        listNode3.next = new ListNode(5);
        listNode3.next.next = new ListNode(6);

        System.out.println("合并K个排序链表：" + mergeKLists(new ListNode[] {listNode1, listNode2}));
    }

    /**
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     *
     * <p>示例:
     *
     * <p>输入: [   1->4->5,   1->3->4,   2->6 ] 输出: 1->1->2->3->4->4->5->6
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    // 分治，两两归并
    /*public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKLists(lists, 0, lists.length - 1);
    }*/

    public ListNode mergeKLists(ListNode[] listNodes, int left, int right) {
        if (left == right) {
            return listNodes[left];
        }
        //        int mid = (right - left) >> 1;

        ListNode leftNode = mergeKLists(listNodes, left, left + ((right - left) >> 1));
        ListNode rightNode = mergeKLists(listNodes, left + ((right - left) >> 1) + 1, right);
        return merge(leftNode, rightNode);
    }

    public ListNode merge(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        }

        ListNode listNode;
        if (left.val < right.val) {
            listNode = left;
            left = left.next;
        } else {
            listNode = right;
            right = right.next;
        }
        listNode.next = null;

        ListNode temp = listNode;
        while (left != null && right != null) {
            if (left.val < right.val) {
                temp.next = left;
                left = left.next;
            } else {
                temp.next = right;
                right = right.next;
            }
            temp = temp.next;
        }

        if (left == null) {
            temp.next = right;
        } else {
            temp.next = left;
        }

        return listNode;
    }

    // 优先队列
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue =
                new PriorityQueue<>(
                        lists.length,
                        new Comparator<ListNode>() {
                            @Override
                            public int compare(ListNode o1, ListNode o2) {
                                return o1.val - o2.val;
                            }
                        });

        for (ListNode node : lists) {
            if (node != null) queue.add(node);
        }

        ListNode result = queue.poll();
        ListNode temp = result;

        if (temp != null && temp.next != null) {
            queue.offer(temp.next);
            result.next = null;
            //            temp = temp.next;
        }
        while (!queue.isEmpty()) {

            temp.next = queue.poll();
            temp = temp.next;
            if (temp.next != null) {
                queue.offer(temp.next);
            }
        }

        return result;
    }
}
