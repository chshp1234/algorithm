package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

//输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
//
// 
//
//示例 1：
//
//输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
//输出：true
//解释：我们可以按以下顺序执行：
//push(1), push(2), push(3), push(4), pop() -> 4,
//push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
//示例 2：
//
//输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
//输出：false
//解释：1 不能在 2 之前弹出。
// 
//
//提示：
//
//0 <= pushed.length == popped.length <= 1000
//0 <= pushed[i], popped[i] < 1000
//pushed 是 popped 的排列。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 栈的压入和弹出序列 {
    @Test
    public void 栈的压入和弹出序列() {

        int[] pushed = {4, 0, 3, 1, 2};
        int[] popped = {3, 1, 0, 2, 4};

        System.out.println("栈的压入和弹出序列:" + validateStackSequences(pushed, popped));
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        //栈
        //用一个辅助栈stack，模拟入栈的操作
        //遍历过程中，记录出栈栈顶坐标popTop，若遍历到入栈元素和出栈栈顶元素相同（pushed[i] == popped[popTop]）
        //则领popTop+1,并依次判断stack中的元素是否和popped[popTop]相同，若相同，则stack元素出栈
        //若遍历过程中，准备入栈的元素和出栈栈顶元素不想同，则将当前入栈的元素加入辅助栈中
        //注意：遍历结束时，还需要对辅助栈stack和出栈中的元素是否相同，若遍历到不相同的元素，则说明此出栈顺序不是入栈的顺序，返回false，直至辅助栈为空时，返回true。
        int len = pushed.length;
        if (len == 0) {
            return true;
        }


        Deque<Integer> stack = new LinkedList<>();
        int popTop = 0;

        for (int i = 0; i < len; i++) {
            if (pushed[i] == popped[popTop]) {
                popTop++;
                while (!stack.isEmpty()) {
                    if (stack.peekLast() == popped[popTop]) {
                        stack.pollLast();
                        popTop++;
                    } else {
                        break;
                    }
                }
            } else {
                stack.offer(pushed[i]);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peekLast() == popped[popTop]) {
                stack.pollLast();
                popTop++;
            } else {
                return false;
            }
        }

        return true;
    }
}
