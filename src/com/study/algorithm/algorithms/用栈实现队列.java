package com.study.algorithm.algorithms;

import java.util.Deque;
import java.util.LinkedList;

//请你仅使用两个栈实现先入先出队列。队列应当支持一般队列的支持的所有操作（push、pop、peek、empty）：
//
//实现 MyQueue 类：
//
//void push(int x) 将元素 x 推到队列的末尾
//int pop() 从队列的开头移除并返回元素
//int peek() 返回队列开头的元素
//boolean empty() 如果队列为空，返回 true ；否则，返回 false
// 
//
//说明：
//
//你只能使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
//你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
// 
//
//进阶：
//
//你能否实现每个操作均摊时间复杂度为 O(1) 的队列？换句话说，执行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费较长时间。
// 
//
//示例：
//
//输入：
//["MyQueue", "push", "push", "peek", "pop", "empty"]
//[[], [1], [2], [], [], []]
//输出：
//[null, null, null, 1, 1, false]
//
//解释：
//MyQueue myQueue = new MyQueue();
//myQueue.push(1); // queue is: [1]
//myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
//myQueue.peek(); // return 1
//myQueue.pop(); // return 1, queue is [2]
//myQueue.empty(); // return false
// 
//
//提示：
//
//1 <= x <= 9
//最多调用 100 次 push、pop、peek 和 empty
//假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/implement-queue-using-stacks
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 用栈实现队列 {

    //两个栈实现一个队列
    //一个栈用于入队操作，一个栈用于出队操作
    //1.入队栈left，出队栈right
    //2.每次入队时，把元素push进left栈
    //3.出队时，从right出队栈中取元素，若栈为空，则把left栈中元素全部取出，放入right栈中，
    //由于栈的先进后出性质，此时right栈中的栈顶元素就为最先进入left栈的元素，直接取出right栈顶元素即可。若right栈不为空，也可直接取出栈顶元素即可。
    //4.isEmpty方法，判断left和right栈都是否为空。


    Deque<Integer> left;
    Deque<Integer> right;

    /** Initialize your data structure here. */
    public 用栈实现队列() {
        left = new LinkedList<>();
        right = new LinkedList<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        left.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        place();
        return right.pop();
    }

    /** Get the front element. */
    public int peek() {
        place();
        return right.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return left.isEmpty() && right.isEmpty();
    }

    public void place() {
        if (right.isEmpty()) {
            while (!left.isEmpty()) {
                right.push(left.pop());
            }
        }
    }
}
