package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test

//给你一个下标从 0 开始的整数数组 nums 。如果 nums 中长度为 m 的子数组 s 满足以下条件，我们称它是一个 交替子数组 ：
//
//m 大于 1 。
//s1 = s0 + 1 。
//下标从 0 开始的子数组 s 与数组 [s0, s1, s0, s1,...,s(m-1) % 2] 一样。也就是说，s1 - s0 = 1 ，s2 - s1 = -1 ，s3 - s2 = 1 ，s4 - s3 = -1 ，以此类推，直到 s[m - 1] - s[m - 2] = (-1)m 。
//请你返回 nums 中所有 交替 子数组中，最长的长度，如果不存在交替子数组，请你返回 -1 。
//
//子数组是一个数组中一段连续 非空 的元素序列。
//
//
//
//示例 1：
//
//输入：nums = [2,3,4,3,4]
//输出：4
//解释：交替子数组有 [3,4] ，[3,4,3] 和 [3,4,3,4] 。最长的子数组为 [3,4,3,4] ，长度为4 。
//示例 2：
//
//输入：nums = [4,5,6]
//输出：2
//解释：[4,5] 和 [5,6] 是仅有的两个交替子数组。它们长度都为 2 。
//
//
//提示：
//
//2 <= nums.length <= 100
//1 <= nums[i] <= 104
class 最长交替子数组 {

    @Test
    fun 最长交替子数组() {
        println(
            "最长交替子数组:${
                alternatingSubarray(intArrayOf(3, 3, 3, 2, 3, 3, 2, 1, 1))
            }"
        )
    }

    fun alternatingSubarray(nums: IntArray): Int {
        //模拟，一趟遍历
        //要按题意交替出现元素，那么(nums[i-1]-nums[i-2])*(nums[i]-nums[i-1])=-1，而子数组的起始两个元素nums[s]=nums[s+1]-1;
        //初始化结果数量res=-1,当前连续子数组数量count=-1，前两个元素差值pre=0。
        //按题意遍历数组：
        // 1.在出现交替时，令count++，并且pre=当前俩元素差值
        // 2.当元素不满足交替时，更新结果res大小。并继续判断当前俩元素差值是否为1
        // 3.如果差值为1，则count=2，pre=1
        // 4.否则，令pre=0，count=-1
        // 最后返回Math.max(res, count)
        var res = -1
        var count = -1
        var pre = 0
        for (i in 1 until nums.size) {
            (nums[i] - nums[i - 1]).let {
                count = if (it * pre == -1) {
                    pre = it
                    count + 1
                } else {
                    res = Math.max(res, count)
                    if (it == 1) {
                        pre = 1
                        2
                    } else {
                        pre = 0
                        -1
                    }
                }
            }
        }
        return Math.max(res, count)
    }
}