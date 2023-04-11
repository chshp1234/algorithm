package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

public class 删除中间节点 {

    @Test
    public void 删除中间节点() {
        ListNode listNode1 = new ListNode(1);
        //        listNode1.next = new ListNode(5);

        ListNode listNode2 = new ListNode(0);
        //        listNode2.next = new ListNode(4);
        //        listNode2.next.next = new ListNode(6);

        ListNode listNode3 = new ListNode(4);
        listNode3.next = new ListNode(5);
        listNode3.next.next = new ListNode(6);

        deleteNode(listNode3.next);
        System.out.print("删除链表中间节点:" + listNode3);
    }

    // 实现一种算法，删除单向链表中间的某个节点（即不是第一个或最后一个节点），假定你只能访问该节点。
    //
    //
    //
    // 示例：
    //
    // 输入：单向链表a->b->c->d->e->f中的节点c
    // 结果：不返回任何数据，但该链表变为a->b->d->e->f
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/delete-middle-node-lcci
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public void deleteNode(ListNode node) {
        ListNode temp = node.next;
        node.val = temp.val;
        node.next = temp.next;
    }
}
