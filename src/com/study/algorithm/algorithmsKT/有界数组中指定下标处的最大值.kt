package com.study.algorithm.algorithmsKT

import org.junit.Test

//给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：
//
//nums.length == n
//nums[i] 是 正整数 ，其中 0 <= i < n
//abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1
//nums 中所有元素之和不超过 maxSum
//nums[index] 的值被 最大化
//返回你所构造的数组中的 nums[index] 。
//
//注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。
//
// 
//
//示例 1：
//
//输入：n = 4, index = 2,  maxSum = 6
//输出：2
//解释：数组 [1,1,2,1] 和 [1,2,2,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
//示例 2：
//
//输入：n = 6, index = 1,  maxSum = 10
//输出：3
// 
//
//提示：
//
//1 <= n <= maxSum <= 109
//0 <= index < n
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-value-at-a-given-index-in-a-bounded-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 有界数组中指定下标处的最大值 {

    @Test
    fun 有界数组中指定下标处的最大值() {
        println(
            "有界数组中指定下标处的最大值:${
                maxValue(
                    6,
                    2,
                    931384943
                )
            }"
        )
    }

    fun maxValue(n: Int, index: Int, maxSum: Int): Int {
        //二分查找，贪心
        //index处的值尽量最大，并且左右两侧的值单调递减1
        //当左侧到头，右侧到头时，将这些数加起来，并保证其小于等于maxSum
        //因为是单调递减1，所以，可以用等差数列求和公式算出总的值
        //左右最小值，注意边界，左边界0，右边界n-1
        //又因为元素的值最小为1，所以为了方便计算我们可以先令最大值maxSum-n，这样，相当于每个元素的值最小为0了
        var l = 1
        val target = maxSum - n
        var r = target
        while (l <= r) {
            val mid = l + ((r - l) shr 1)
            if (check(mid, index, n - 1, target)) {
                l = mid + 1
            } else {
                r = mid - 1
            }
        }
        return l
    }

    fun check(n: Int, i: Int, limit: Int, target: Int): Boolean {
        var sum = 0L
        if (n - i >= 0) {
            sum += (n - i + n).toLong() * (i + 1) / 2
        } else {
            sum += n.toLong() * (n + 1) / 2
        }

        if (limit - i <= n) {
            sum += (n - limit + i + n - 1).toLong() * (limit - i) / 2
        } else {
            sum += (n - 1).toLong() * n / 2
        }

        return sum <= target
    }
}