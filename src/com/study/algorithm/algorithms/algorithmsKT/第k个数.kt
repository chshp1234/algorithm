package com.study.algorithm.algorithms.algorithmsKT

import java.util.*

//有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
//
//示例 1:
//
//输入: k = 5
//
//输出: 9
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/get-kth-magic-number-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 第k个数_丑数 {
    fun getKthMagicNumber(k: Int): Int {
        //动态规划
        //设dp[i]:数字i是否是符合条件的数
        //那么如果(num % 3 == 0 && dp[num / 3]) || (num % 5 == 0 && dp[num / 5]) || (num % 7 == 0 && dp[num / 7]),dp[num]=true;否则dp[num]=false
        if (k == 1) {
            return 1
        }
        val dp = ArrayList<Boolean>()
        dp.add(false)
        dp.add(true)

        var count = 1
        var num = 2

        while (true) {
            if ((num % 3 == 0 && dp[num / 3]) || (num % 5 == 0 && dp[num / 5]) || (num % 7 == 0 && dp[num / 7])) {
                if (++count == k) {
                    return num
                }
                dp.add(true)
            } else {
                dp.add(false)
            }
            num++
        }
    }

    fun getKthMagicNumberByChouShu(k: Int): Int {
        //优先队列(同"丑数")
        if (k == 1) {
            return 1
        }
        val queue = PriorityQueue<Long>()
        val set = mutableSetOf<Long>()
        queue.offer(1)
        set.add(1)

        var count = 0
        while (true) {
            val top = queue.poll()!!
            count++
            if (count == k) {
                return top.toInt()
            }
            var num = top * 3
            if (set.add(num)) {
                queue.offer(num)
            }
            num = top * 5
            if (set.add(num)) {
                queue.offer(num)
            }
            num = top * 7
            if (set.add(num)) {
                queue.offer(num)
            }
        }
    }
}