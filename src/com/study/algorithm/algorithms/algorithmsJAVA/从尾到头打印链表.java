package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 从尾到头打印链表 {

    @Test
    public void 从尾到头打印链表() {
        ListNode listNode1 = new ListNode(1);
        //        listNode1.next = new ListNode(5);

        ListNode listNode2 = new ListNode(0);
        //        listNode2.next = new ListNode(4);
        //        listNode2.next.next = new ListNode(6);

        ListNode listNode3 = new ListNode(4);
//        listNode3.next = new ListNode(5);
//        listNode3.next.next = new ListNode(6);

        System.out.print("从尾到头打印链表:" + Arrays.toString(reversePrint(listNode3)));
    }

    public int[] reversePrint(ListNode head) {
        // 栈（递归、或直接使用LinckedList），后进先出，保证输出的元素为倒置的顺序
        // 也可先获取链表长度，再创建int[]数组,最后遍历链表时，从尾到头的填充数组即可
        List<Integer> list = new ArrayList<>();
        recursionPrint(head, list);
        return list.stream().mapToInt(value -> value).toArray();

    }

    public void recursionPrint(ListNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        recursionPrint(root.next, list);
        list.add(root.val);

    }
}
