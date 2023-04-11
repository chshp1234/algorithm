package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；你需要返回可能的方案总数。
//
//让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
//
//由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。
//
// 
//
//示例 1：
//
//输入：n = 5
//输出：12
//解释：举个例子，[1,2,5,4,3] 是一个有效的排列，但 [5,2,3,4,1] 不是，因为在第二种情况里质数 5 被错误地放在索引为 1 的位置上。
//示例 2：
//
//输入：n = 100
//输出：682289015
// 
//
//提示：
//
//1 <= n <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/prime-arrangements
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 质数排列 {
    @Test
    public void 质数排列() {
        System.out.println("质数排列:" + numPrimeArrangements(100));
    }

    public int numPrimeArrangements(int n) {
        //计数
        //首先确定n以内的数的质数的数量
        //那么总排列数量就是（质数的全排列的数量）*（非质数的全排列的数量）
        if (n == 1) {
            return 1;
        }

        int prime = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrimes(i)) {
                prime++;
            }
        }


        return (int) ((factorial(prime) * factorial(n - prime)) % 1000000007);
    }

    public long factorial(int n) {
        if (n < 3) {
            return n;
        }
        long result = 2;
        for (int i = 3; i <= n; i++) {
            result = (result * i) % 1000000007;
        }

        return result;
    }

    public boolean isPrimes(int n) {
        for (int i = 2, l = (int) Math.sqrt(n); i <= l; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
