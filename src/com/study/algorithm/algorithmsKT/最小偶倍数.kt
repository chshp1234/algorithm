package com.study.algorithm.algorithmsKT

import java.util.concurrent.ConcurrentHashMap

//给你一个正整数 n ，返回 2 和 n 的最小公倍数（正整数）。
// 
//
//示例 1：
//
//输入：n = 5
//输出：10
//解释：5 和 2 的最小公倍数是 10 。
//示例 2：
//
//输入：n = 6
//输出：6
//解释：6 和 2 的最小公倍数是 6 。注意数字会是它自身的倍数。
// 
//
//提示：
//
//1 <= n <= 150
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/smallest-even-multiple
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 最小偶倍数 {
    fun smallestEvenMultiple(n: Int): Int {
        //因为另一个数只有2,所以只需判断n的奇偶性就行
        return if (n % 2 == 0) n else n * 2
    }

    fun smallestEvenMultipleByGCD(n: Int): Int {
        if (n < 3) {
            return 2
        }

        //求出最大公约数gcd
        val gcd = GCD(2, n)

        //最小公倍数就为(n / gcd) * 2
        return (n / gcd) * 2
    }

    fun GCD(a: Int, b: Int): Int {
        //辗转相除法求两个数的最大公约数
        var l = a
        var h = b
        var t = h % l
        while (t > 0) {
            h = l
            l = t
            t = h % l
        }
        return l
    }
}