package com.study.algorithm.algorithms;

import org.junit.Test;

public class 爬楼梯 {

    @Test
    public void 爬楼梯() {
        int n = 10;

        System.out.printf("爬楼梯，有%d种方式可以爬到%d层:", climbStairs(n), n);
    }

    // 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    //
    // 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
    //
    // 注意：给定 n 是一个正整数。
    //
    // 示例 1：
    //
    // 输入： 2
    // 输出： 2
    // 解释： 有两种方法可以爬到楼顶。
    // 1.  1 阶 + 1 阶
    // 2.  2 阶
    // 示例 2：
    //
    // 输入： 3
    // 输出： 3
    // 解释： 有三种方法可以爬到楼顶。
    // 1.  1 阶 + 1 阶 + 1 阶
    // 2.  1 阶 + 2 阶
    // 3.  2 阶 + 1 阶
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/climbing-stairs
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int climbStairs(int n) {

        // 由于一次能爬一层或者两层，所以爬到第n层可从第n-2层和n-1层爬上来
        // 则可得：f(n)=f(n-2)+f(n-1)（从第n-2层爬到第n层有两种方式，从n-1层爬到第n层有一种方式
        // 则：f(n)=f(n-2)+1+f(n-1)-1,其中+1是因为有额外的方式可以爬到，-1是因为从n-2往上爬1层时到了n-1层与从n-1层开始重复了一次）
        // 很明显，这是一个斐波那契数列的计算，可用递归、记忆化搜索，和滚动数组的方式进行计算。

        if (n <= 3) {
            return n;
        }

        // 定义前两层（n-2）和前一层（n-1）的次数
        int a = 2;
        int b = 3;

        int sum = 0;
        for (int i = 3; i < n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }

        return sum;
    }
}
