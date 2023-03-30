package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class 复杂链表的复制 {
    @Test
    public void 复杂链表的复制() {
        Node node = new Node(2);
        node.next = new Node(3);
        node.random = null;
        node.next.next = new Node(4);
        node.next.random = node;
        System.out.println("外观数列:" + copyRandomList(node));
    }

    public Node copyRandomList(Node head) {

        //哈希表
        //用哈希表存储原节点和新节点的映射关系
        //复制时从哈希表中取，若没有，重新创建新节点（复制），并将原节点和新节点加入哈希表建立映射关系
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node dummy = new Node(0);

        //哑巴节点
        Node next = dummy;
        Node temp;
        while (head != null) {
            temp = map.get(head);
            //构建复制next节点
            if (temp == null) {
                //建立映射关系
                temp = new Node(head.val);
                map.put(head, temp);
            }
            next.next = temp;
            //构建复制random节点
            if (head.random == null) {
                next.next.random = null;
            } else if ((temp = map.get(head.random)) == null) {
                //建立映射关系
                temp = new Node(head.random.val);
                map.put(head.random, temp);
                next.next.random = temp;
            } else {
                next.next.random = temp;
            }

            head = head.next;
            next = next.next;
        }
        return dummy.next;
    }

    public class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
