package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 最小栈 {
    @Test
    public void 最小栈() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
        minStack.pop();
        minStack.top();
        minStack.getMin();
    }

    public static class MinStack {

        MyNode head;

        MyNode min;
        PriorityQueue<MyNode> minStack;

        Comparator<MyNode> comparator =
                new Comparator<MyNode>() {
                    @Override
                    public int compare(MyNode o1, MyNode o2) {
                        if (o1.val > 0 && o2.val < 0) {
                            return 1;
                        } else if (o1.val < 0 && o2.val > 0) {
                            return -1;
                        } else {
                            return o1.val - o2.val;
                        }
                    }
                };

        /** initialize your data structure here. */
        public MinStack() {
            minStack = new PriorityQueue<>(comparator);
        }

        public void push(int x) {
            if (head == null) {
                head = new MyNode(x);
            } else {
                MyNode next = head;
                head = new MyNode(x);
                head.next = next;
            }
            minStack.offer(head);
        }

        public void pop() {
            MyNode next = head.next;
            minStack.remove(head);
            head.next = null;
            head = next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return minStack.peek().val;
        }

        public static class MyNode {
            public int val;
            public MyNode next;

            public MyNode(int val) {
                this.val = val;
            }
        }
    }
}
