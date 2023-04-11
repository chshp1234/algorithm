package com.study.algorithm.algorithms.algorithmsJAVA;

//设计实现双端队列。
//
//实现 MyCircularDeque 类:
//
//MyCircularDeque(int k) ：构造函数,双端队列最大为 k 。
//boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true ，否则返回 false 。
//boolean insertLast() ：将一个元素添加到双端队列尾部。如果操作成功返回 true ，否则返回 false 。
//boolean deleteFront() ：从双端队列头部删除一个元素。 如果操作成功返回 true ，否则返回 false 。
//boolean deleteLast() ：从双端队列尾部删除一个元素。如果操作成功返回 true ，否则返回 false 。
//int getFront() )：从双端队列头部获得一个元素。如果双端队列为空，返回 -1 。
//int getRear() ：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1 。
//boolean isEmpty() ：若双端队列为空，则返回 true ，否则返回 false  。
//boolean isFull() ：若双端队列满了，则返回 true ，否则返回 false 。
// 
//
//示例 1：
//
//输入
//["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
//[[3], [1], [2], [3], [4], [], [], [], [4], []]
//输出
//[null, true, true, true, false, 2, true, true, true, 4]
//
//解释
//MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
//circularDeque.insertLast(1);			        // 返回 true
//circularDeque.insertLast(2);			        // 返回 true
//circularDeque.insertFront(3);			        // 返回 true
//circularDeque.insertFront(4);			        // 已经满了，返回 false
//circularDeque.getRear();  				// 返回 2
//circularDeque.isFull();				        // 返回 true
//circularDeque.deleteLast();			        // 返回 true
//circularDeque.insertFront(4);			        // 返回 true
//circularDeque.getFront();				// 返回 4
// 
// 
//
//提示：
//
//1 <= k <= 1000
//0 <= value <= 1000
//insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull  调用次数不大于 2000 次
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/design-circular-deque
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 设计循环双端队列 {
    //数组，双指针
    //用数组实现双端队列，一个指针head指向队列头部，tail指向队列尾部
    //添加数据时先判断队列是否已满
    //向头部添加数据时，应先把头部指针左移，若溢出，则指向size-1
    //向尾部添加数据时，应先把尾部指针右移，若溢出，则指向0
    //删除时同理，头部指针右移，尾部指针左移
    class MyCircularDeque {

        int[] elements;
        int size;

        int head;
        int tail;

        public MyCircularDeque(int k) {
            size = 0;

            head = 1;
            tail = 0;
            elements = new int[k];
        }

        public boolean insertFront(int value) {
            if (size == elements.length) {
                return false;
            }

            //指针左移，+elements.length防止左移后值为-1，加上大小即可让指针指向elements.length-1处
            head = (head - 1 + elements.length) % elements.length;
            elements[head] = value;

            size++;

            return true;
        }

        public boolean insertLast(int value) {
            if (size == elements.length) {
                return false;
            }

            //指针右移
            tail = (tail + 1) % elements.length;
            elements[tail] = value;

            size++;

            return true;
        }

        public boolean deleteFront() {
            if (size == 0) {
                return false;
            }

            size--;

            elements[head] = 0;
            head = (head + 1) % elements.length;
            return true;
        }

        public boolean deleteLast() {
            if (size == 0) {
                return false;
            }

            size--;

            elements[tail] = 0;
            tail = (tail - 1 + elements.length) % elements.length;
            return true;
        }

        public int getFront() {
            if (size == 0) {
                return -1;
            }

            return elements[head];
        }

        public int getRear() {
            if (size == 0) {
                return -1;
            }

            return elements[tail];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == elements.length;
        }
    }
}