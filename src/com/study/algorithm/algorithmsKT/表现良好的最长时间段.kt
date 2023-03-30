package com.study.algorithm.algorithmsKT

import org.junit.Test
import java.util.*

//给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
//
//我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
//
//所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
//
//请你返回「表现良好时间段」的最大长度。
//
// 
//
//示例 1：
//
//输入：hours = [9,9,6,0,6,6,9]
//输出：3
//解释：最长的表现良好时间段是 [9,9,6]。
//示例 2：
//
//输入：hours = [6,6,6]
//输出：0
// 
//
//提示：
//
//1 <= hours.length <= 104
//0 <= hours[i] <= 16
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/longest-well-performing-interval
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 表现良好的最长时间段 {
    @Test
    fun 表现良好的最长时间段() {
        println(
            "表现良好的最长时间段:${longestWPI2(intArrayOf(9, 9, 6, 0, 6, 6, 9))}"
        )
    }

    @Deprecated("解答出错")
    fun longestWPI(hours: IntArray): Int {
        var res = 0
        val stack: Deque<IntArray> = LinkedList()
        val sum = intArrayOf(0, 0)

        for (h in hours) {
            if (h <= 8) {
                val current = intArrayOf(sum[0], sum[1])
                while (!stack.isEmpty()) {
                    val top = stack.pop()
                    current[0] += top[0]
                    current[1] += top[1]
                    if (current[0] + top[0] > 0) {
                        res = Math.max(res, current[1])
                    } else {
                        stack.push(current)
                        break
                    }
                }
                stack.push(intArrayOf(-1, 1))
                sum[0] = 0
                sum[1] = 0
            } else {
                sum[0] += 1
                sum[1] += 1
            }
        }

        return res
    }

    fun longestWPI2(hours: IntArray): Int {
        //前缀和
        //首先，令>8的元素为+1，<=8的元素为-1
        //根据上述规则,记录每个下标的缀和
        //之后开始遍历前缀和,当某个区间的和大于0时,即可判断并更新最大长度
        //剪枝:如果剩余元素的数量小于等于已经获取的子区间的数量,则可推出判断
        var res = 0

        val preSum = IntArray(hours.size + 1)
        for (h in 1..hours.size) {
            preSum[h] = (h - 1).let {
                preSum[it - 1] + if (hours[it] > 8) +1 else -1
            }
            preSum[h] = preSum[h - 1] + hours[h - 1]
        }

        for (i in preSum.indices) {
            if (preSum.size - i <= res) {
                break
            }
            for (j in i + 1 until preSum.size) {
                preSum[j] -= preSum[i]
                if (preSum[j] > 0) {
                    res = Math.max(res, j - i)
                }
            }
        }

        return res
    }
}