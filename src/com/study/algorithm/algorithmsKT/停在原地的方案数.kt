package com.study.algorithm.algorithmsKT

import org.junit.Test

//有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
//
//每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
//
//给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
//
//由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。
//
// 
//
//示例 1：
//
//输入：steps = 3, arrLen = 2
//输出：4
//解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
//向右，向左，不动
//不动，向右，向左
//向右，不动，向左
//不动，不动，不动
//示例  2：
//
//输入：steps = 2, arrLen = 4
//输出：2
//解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
//向右，向左
//不动，不动
//示例 3：
//
//输入：steps = 4, arrLen = 2
//输出：8
// 
//
//提示：
//
//1 <= steps <= 500
//1 <= arrLen <= 10^6
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 停在原地的方案数 {
    @Test
    fun 停在原地的方案数() {
        println("停在原地的方案数:${numWays(5, 2)}")
    }

    fun numWays(steps: Int, arrLen: Int): Int {
        //动态规划（跳楼梯的青蛙升级版）
        //因为每次只能移动一步（向左，向右，不动）
        //设状态方程dp[index][steps]:表示移动steps步后，移动到数组index处，可以得到的方案数
        //因为是一步步跳的，所以跳steps步，得先跳steps-1步（不是费话么），
        //状态转移方程为：dp[index][steps]=dp[index-1][steps-1]+dp[index][steps-1]+dp[index+1][steps-1]（index-1<0或者index+1>arrLen,dp=0）
        //此方程表示，青蛙跳了steps-1步时：
        //1.到index-1处，那么往右走跳一步，就是steps步跳到index处；
        //2.到index处，那么不动，就是steps步跳到index处；
        //3.到index+1处，那么往左跳一步，就是steps步跳到index处；
        //所以计算好状态方程后，只需要返回dp[1][steps]即可

        //初始数组长度len+2，第一位和最后一位为0，方便计算
        val dpArray = LongArray(arrLen + 2)
        dpArray[1] = 1
        for (i in 1 until steps) {
            //第i步最多可以跳到哪
            val len = Math.min(i + 1, arrLen)
            //滚动数组，因为每次更新状态，只跟上一行的dp[i-1]有关
            var last = dpArray[0]
            var now: Long
            for (j in 1..len) {
                now = dpArray[j]
                dpArray[j] = (last + dpArray[j] + dpArray[j + 1]) % 1000000007
                last = now
            }
        }
        //最后一次不用计算全部（不过据此还可以进一步优化，不是每一次计算steps都需要算出全部状态值）
        return ((dpArray[0] + dpArray[1] + dpArray[2]) % 1000000007).toInt()
    }
}