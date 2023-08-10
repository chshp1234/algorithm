package com.study.algorithm.algorithms.algorithmsKT

//给你一个整数数组 nums 。一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。
//
//请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。
//
//abs(x) 定义如下：
//
//如果 x 是负整数，那么 abs(x) = -x 。
//如果 x 是非负整数，那么 abs(x) = x 。
//
//
//示例 1：
//
//输入：nums = [1,-3,2,3,-4]
//输出：5
//解释：子数组 [2,3] 和的绝对值最大，为 abs(2+3) = abs(5) = 5 。
//示例 2：
//
//输入：nums = [2,-5,1,-4,3,-2]
//输出：8
//解释：子数组 [-5,1,-4] 和的绝对值最大，为 abs(-5+1-4) = abs(-8) = 8 。
//
//
//提示：
//
//1 <= nums.length <= 105
//-104 <= nums[i] <= 104
class 任意子数组和的绝对值的最大值 {
    fun maxAbsoluteSum(nums: IntArray): Int {
        //贪心
        //因为要求最大绝对值的,那么可以求出最大子数组和max,和最小子数组和min
        //最后返回Math.max(-min, max)即可
        //这两个值的求解过程是相同的,以最大子数组和为例
        //当以i为结尾时,最大子数组和为以i-1为结尾时,的最大子数组和+num[i],其中当和为负数时,其最大和为0,即一个空数组
        //也可以这么理解,以i为结尾时的连续子数组和为正数时,那么这个连续的子数组都可以加入后续的计算当中,当和为负数时,就不必加入后续的计算当中
        //那么此时令最大和为0,并且最大子数组是个空数组.
        //这样做,可以保证在后续的子数组和的计算中,加入的前面的子数组不会是个负数,即可使得子数组的和的最大化.
        var max = 0
        var min = 0
        var pos = 0
        var neg = 0
        for (n in nums) {
            if (pos + n > 0) {
                pos += n
            } else {
                pos = 0
            }

            if (neg + n < 0) {
                neg += n
            } else {
                neg = 0
            }

            max = Math.max(max, pos)
            min = Math.min(min, neg)
        }

        return Math.max(-min, max)
    }
}