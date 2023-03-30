package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class 用两个栈实现队列 {

    // 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead
    // ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
    //
    //
    //
    // 示例 1：
    //
    // 输入：
    // ["CQueue","appendTail","deleteHead","deleteHead"]
    // [[],[3],[],[]]
    // 输出：[null,null,3,-1]
    // 示例 2：
    //
    // 输入：
    // ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
    // [[],[],[5],[2],[],[]]
    // 输出：[null,-1,null,null,5,2]
    // 提示：
    //
    // 1 <= values <= 10000
    // 最多会对 appendTail、deleteHead 进行 10000 次调用
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    @Test
    public void 用两个栈实现队列() {

        CQueue obj = new CQueue();
        obj.appendTail(1);
        int param_2 = obj.deleteHead();
    }

    class CQueue {

        Queue<Integer> queue = new LinkedList<>();

        public CQueue() {}

        public void appendTail(int value) {
            queue.offer(value);
        }

        public int deleteHead() {
            Integer integer;
            return (integer = queue.poll()) == null ? -1 : integer;
        }
    }

    class TwoStackQueue {
        Deque<Integer> addStack;
        Deque<Integer> deleteStack;

        // 思路和算法
        //
        // 维护两个栈，第一个栈支持插入操作，第二个栈支持删除操作。
        //
        // 根据栈先进后出的特性，我们每次往第一个栈里插入元素后，第一个栈的底部元素是最后插入的元素，第一个栈的顶部元素是下一个待删除的元素。
        // 为了维护队列先进先出的特性，我们引入第二个栈，用第二个栈维护待删除的元素，在执行删除操作的时候我们首先看下第二个栈是否为空。
        // 如果为空，我们将第一个栈里的元素一个个弹出插入到第二个栈里，这样第二个栈里元素的顺序就是待删除的元素的顺序，
        // 要执行删除操作的时候我们直接弹出第二个栈的元素返回即可。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/solution/mian-shi-ti-09-yong-liang-ge-zhan-shi-xian-dui-l-3/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        public TwoStackQueue() {
            addStack = new LinkedList<>();
            deleteStack = new LinkedList<>();
        }

        public void appendTail(int value) {
            addStack.push(value);
        }

        public int deleteHead() {
            // 如果第二个栈为空
            if (deleteStack.isEmpty()) {
                while (!addStack.isEmpty()) {
                    deleteStack.push(addStack.pop());
                }
            }
            if (deleteStack.isEmpty()) {
                return -1;
            } else {
                return deleteStack.pop();
            }
        }
    }
}
