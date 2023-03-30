package com.study.algorithm.algorithms;


import org.junit.Test;

public class 扁平化多级双向链表 {
    @Test
    public void 扁平化多级双向链表() {
        Node head = new Node(1);
        head.child = new Node(2);
        head.child.child = new Node(3);


        System.out.println("扁平化多级双向链表:" + flatten(head));
    }


    public Node flatten(Node head) {
        //DFS
        //一趟深度优先遍历
        //如果node没有子节点和下一个节点，说明遍历到尾部，返回此节点
        //如果node只有next节点，则处理next节点
        //如果node只有子节点，则处理子节点，注意处理后node.child=null
        //如果node有next节点和child节点，则优先处理子节点，之后把处理的尾节点tail指向node.next，并且node.next.pre节点指向tail，注意处理后node.child=null

        if (head == null) {
            return null;
        }

        revers(head);

        //翻转，返回首节点
        //因为是双向链表，其实并不用
        /*while (head.prev != null) {
            head = head.prev;
        }*/

        return head;
    }

    //递归处理head，返回此层级的尾节点
    public Node revers(Node head) {
        if (head.child == null && head.next == null) {
            //子节点和next节点都为null，此节点即为尾节点，返回
            return head;
        } else if (head.child == null) {
            //只有next节点
            return revers(head.next);
        } else if (head.next == null) {
            //只有child节点
            //令node.next连上child，child.pre连上node
            Node child = head.child;
            //注意处理child=null
            head.child = null;
            head.next = child;
            child.prev = head;
            return revers(child);
        } else {
            //如果既有next节点又有child节点
            //那么优先处理child节点，并找到child层级处的尾节点tail
            Node next = head.next;
            head.next = head.child;
            head.child.prev = head;
            Node tail = revers(head.child);
            //令尾节点tail.next连上node.next，node.next.pre连上tail
            //注意处理child=null
            head.child = null;
            tail.next = next;
            next.prev = tail;
            return revers(next);
        }
    }

    private class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val) {
            this.val = val;
        }
    }

}


