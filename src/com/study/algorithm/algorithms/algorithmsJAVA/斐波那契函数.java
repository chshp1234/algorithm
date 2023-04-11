package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 斐波那契函数 {
    @Test
    public void 斐波那契函数() {
        int n = 45;

        System.out.printf("斐波那契数列第%d=" + Fibonacci_Recursion(n), n);
    }
    /** 斐波那契函数,递归法 */
    private int Fibonacci_Recursion(int i) {

        if (i < 0) {
            return -1;
        }
        if (i == 0) {
            return 0;
        }
        if (i == 1 || i == 2) {
            return 1;
        }
        return Fibonacci_Recursion(i - 1) + Fibonacci_Recursion(i - 2);
    }

    /** 斐波那契函数,迭代法 */
    private long Fibonacci_Iteration(int count) {

        // DP:f(i)=f(i-2)+f(i-1),边界条件为i>2
        if (count == 0) {
            return 0;
        }

        if (count == 1 || count == 2) {
            return 1;
        }

        // 定义第一个数
        long first = 0;
        // 定义第二个数
        long second = 1;
        for (int i = 0; i < count - 1; i++) {
            // 替换操作
            long sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    // 面试题10- I. 斐波那契数列
    // 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
    //
    // F(0) = 0,   F(1) = 1
    // F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
    // 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
    //
    // 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
    //
    //
    //
    // 示例 1：
    //
    // 输入：n = 2
    // 输出：1
    // 示例 2：
    //
    // 输入：n = 5
    // 输出：5
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int fib(int count) {
        if (count == 0) {
            return 0;
        }

        if (count == 1 || count == 2) {
            return 1;
        }

        // 定义第一个数
        long first = 0;
        // 定义第二个数
        long second = 1;
        for (int i = 0; i < count - 1; i++) {
            // 替换操作
            long sum = (first + second) % 1000000007;
            first = second;
            second = sum;
        }
        return (int) second;
    }
}
