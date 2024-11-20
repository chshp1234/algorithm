package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test

//给你一个长度为 n 下标从 0 开始的整数数组 maxHeights 。
//
//你的任务是在坐标轴上建 n 座塔。第 i 座塔的下标为 i ，高度为 heights[i] 。
//
//如果以下条件满足，我们称这些塔是 美丽 的：
//
//1 <= heights[i] <= maxHeights[i]
//heights 是一个 山脉 数组。
//如果存在下标 i 满足以下条件，那么我们称数组 heights 是一个 山脉 数组：
//
//对于所有 0 < j <= i ，都有 heights[j - 1] <= heights[j]
//对于所有 i <= k < n - 1 ，都有 heights[k + 1] <= heights[k]
//请你返回满足 美丽塔 要求的方案中，高度和的最大值 。
//
//
//
//示例 1：
//
//输入：maxHeights = [5,3,4,1,1]
//输出：13
//解释：和最大的美丽塔方案为 heights = [5,3,3,1,1] ，这是一个美丽塔方案，因为：
//- 1 <= heights[i] <= maxHeights[i]
//- heights 是个山脉数组，峰值在 i = 0 处。
//13 是所有美丽塔方案中的最大高度和。
//示例 2：
//
//输入：maxHeights = [6,5,3,9,2,7]
//输出：22
//解释： 和最大的美丽塔方案为 heights = [3,3,3,9,2,2] ，这是一个美丽塔方案，因为：
//- 1 <= heights[i] <= maxHeights[i]
//- heights 是个山脉数组，峰值在 i = 3 处。
//22 是所有美丽塔方案中的最大高度和。
//示例 3：
//
//输入：maxHeights = [3,2,5,5,2,3]
//输出：18
//解释：和最大的美丽塔方案为 heights = [2,2,5,5,2,2] ，这是一个美丽塔方案，因为：
//- 1 <= heights[i] <= maxHeights[i]
//- heights 是个山脉数组，最大值在 i = 2 处。
//注意，在这个方案中，i = 3 也是一个峰值。
//18 是所有美丽塔方案中的最大高度和。
//
//
//提示：
//
//1 <= n == maxHeights <= 103
//1 <= maxHeights[i] <= 109
class `美丽塔 I` {
    @Test
    fun 美丽塔() {
        println(
            "美丽塔:${
                maximumSumOfHeights(listOf(999999999, 1000000000, 999999999))
            }"
        )
    }

    fun maximumSumOfHeights(maxHeights: List<Long>): Long {
        //枚举，单调栈
        //因为塔的形状要保持山脉数组
        //所以我们可以枚举每个数组下标i，当以下标i为山脉数组的山顶时，塔的最大高度为多少。
        //当枚举完所有下标后，得出一个最大的高度即可
        //方法一：枚举
        //当以下标i为山顶时，向两边进行“扩散”，分别遍历到0和size-1
        //向左遍历时，只需要令当前塔的高度heights[j]<=heights[j+1];向右遍历时，保持heights[j]<=heights[j-1]
        //那么遍历时每次加上塔的高度，即可得出以i为下标的山顶，塔的最大高度。
        //
        //方法二：单调栈
        //我们用两个数组l和r，分别表示以下标i为山顶时，其左侧塔的最大高度，和其右侧塔的最大高度。
        //那么当遍历到i时，塔的总共的高度就为l[i]+r[i]-maxHeights[i]
        //至于l和r怎么获得，如下：
        //1. if (maxHeights[i] >= maxHeights[i - 1])，那么l[i]=l[i - 1] + maxHeights[i]
        //2.1 否则令当前塔的高度和前一个塔的高度为maxHeights[i]，这两个塔的高度就为sum=2*maxHeights[i]。
        //2.2 同理，依次将和前一个塔的最大高度进行比较，若maxHeights[i] >= maxHeights[j]（0<=j<i-2）,那么令sum再加上l[j]
        //2.3 否则就再加一个maxHeights[i]
        //2.4 l[i]=sum
        //数组r的解法同理，只是比较的对象变为依次和其右侧的值相比较。
        //最返回最大的塔的高度即可
        val limit = maxHeights.size - 1
        val left = Array(maxHeights.size) { 0L }.also { it[0] = maxHeights[0].toLong() }
        val right = Array(maxHeights.size) { 0L }.also { it[limit] = maxHeights[limit].toLong() }
        for (i in 1..limit) {
            left[i] = if (maxHeights[i] >= maxHeights[i - 1]) {
                left[i - 1] + maxHeights[i].toLong()
            } else {
                var sum = 2 * maxHeights[i].toLong()
                for (j in i - 2 downTo 0) {
                    if (maxHeights[i] >= maxHeights[j]) {
                        sum += left[j]
                        break
                    } else {
                        sum += maxHeights[i]
                    }
                }
                sum
            }

            val ri = limit - i
            right[ri] = if (maxHeights[ri] >= maxHeights[ri + 1]) {
                right[ri + 1] + maxHeights[ri].toLong()
            } else {
                var sum = 2 * maxHeights[ri].toLong()
                for (j in 2..i) {
                    if (maxHeights[ri] >= maxHeights[ri + j]) {
                        sum += right[ri + j]
                        break
                    } else {
                        sum += maxHeights[ri]
                    }
                }
                sum
            }
        }

        var res = 0L
        for (i in maxHeights.indices) {
            res = Math.max(res, left[i] + right[i] - maxHeights[i])
        }

        return res

    }
}