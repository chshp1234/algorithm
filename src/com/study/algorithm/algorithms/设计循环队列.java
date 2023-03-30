package com.study.algorithm.algorithms;

import org.junit.Test;

//设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
//
//循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
//
//你的实现应该支持如下操作：
//
//MyCircularQueue(k): 构造器，设置队列长度为 k 。
//Front: 从队首获取元素。如果队列为空，返回 -1 。
//Rear: 获取队尾元素。如果队列为空，返回 -1 。
//enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
//deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
//isEmpty(): 检查循环队列是否为空。
//isFull(): 检查循环队列是否已满。
// 
//
//示例：
//
//MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
//circularQueue.enQueue(1);  // 返回 true
//circularQueue.enQueue(2);  // 返回 true
//circularQueue.enQueue(3);  // 返回 true
//circularQueue.enQueue(4);  // 返回 false，队列已满
//circularQueue.Rear();  // 返回 3
//circularQueue.isFull();  // 返回 true
//circularQueue.deQueue();  // 返回 true
//circularQueue.enQueue(4);  // 返回 true
//circularQueue.Rear();  // 返回 4
// 
//
//提示：
//
//所有的值都在 0 至 1000 的范围内；
//操作数将在 1 至 1000 的范围内；
//请不要使用内置的队列库。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/design-circular-queue
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 设计循环队列 {
    @Test
    public void 设计循环队列() {
        MyCircularQueue q = new MyCircularQueue(3);
        System.out.println("设计循环队列:" + q.enQueue(2));
        System.out.println("设计循环队列:" + q.Rear());
        System.out.println("设计循环队列:" + q.Front());
    }

    class MyCircularQueue {
        //双指针
        //双指针一个指向头部，一个指向尾部，再用一个值存储队列大小
        //每次入队时，尾指针+1，当到头时，重新加入数组头部；出队时，头部出队，头指针右移，同样指针到达数组尾部时，指针指向数组头部。
        //队列是否为空以及是否满，只需要判断size即可。
        int[] queue;
        int front;
        int rear = -1;
        int size;
        int limit;

        public MyCircularQueue(int k) {
            queue = new int[k];
            limit = k;
        }

        public boolean enQueue(int value) {
            if (size == limit) {
                return false;
            }
            size++;
            rear = (rear + 1) % limit;
            queue[rear] = value;
            return true;
        }

        public boolean deQueue() {
            if (size == 0) {
                return false;
            }
            size--;
            queue[front] = 0;
            front = (front + 1) % limit;
            return true;
        }

        public int Front() {
            if (size == 0) {
                return -1;
            }
            return queue[front];
        }

        public int Rear() {
            if (size == 0) {
                return -1;
            }
            return queue[rear];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }
    }
}
