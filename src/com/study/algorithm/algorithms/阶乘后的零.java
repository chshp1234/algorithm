package com.study.algorithm.algorithms;

import org.junit.Test;

//给定一个整数 n ，返回 n! 结果中尾随零的数量。
//
//提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
//
// 
//
//示例 1：
//
//输入：n = 3
//输出：0
//解释：3! = 6 ，不含尾随 0
//示例 2：
//
//输入：n = 5
//输出：1
//解释：5! = 120 ，有一个尾随 0
//示例 3：
//
//输入：n = 0
//输出：0
// 
//
//提示：
//
//0 <= n <= 104
// 
//
//进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 阶乘后的零 {

    @Test
    public void 阶乘后的零() {
        System.out.println("阶乘后的零:" + trailingZeroes(3125));
    }

    public int trailingZeroes(int n) {
        //遍历
        //对阶乘值num对100000取余，并计算每次得到的尾0数量
        long num = 1;

        int result = 0;
        for (int i = 1; i <= n; i++) {
            num *= i;
            while (num % 10 == 0) {
                result++;
                num /= 10;
            }
            num %= 100000;
        }

        return result;
    }

    //n! 尾零的数量即为 n! 中因子 10 的个数，而 10=2×5，因此转换成求 n! 中质因子 2 的个数和质因子 5 的个数的较小值。
    //
    //由于质因子 5 的个数不会大于质因子 2 的个数，我们可以仅考虑质因子 5 的个数。
    //
    //而 n! 中质因子 5 的个数等于 [1,n] 的每个数的质因子 5 的个数之和，我们可以通过遍历 [1,n] 的所有 5 的倍数求出。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes/solution/jie-cheng-hou-de-ling-by-leetcode-soluti-1egk/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int trailingZeroesByLeetcode(int n) {
        int ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
