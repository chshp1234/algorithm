package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test

//给你一个长度为 n 的整数数组 nums ，表示由范围 [0, n - 1] 内所有整数组成的一个排列。
//
//全局倒置 的数目等于满足下述条件不同下标对 (i, j) 的数目：
//
//0 <= i < j < n
//nums[i] > nums[j]
//局部倒置 的数目等于满足下述条件的下标 i 的数目：
//
//0 <= i < n - 1
//nums[i] > nums[i + 1]
//当数组 nums 中 全局倒置 的数量等于 局部倒置 的数量时，返回 true ；否则，返回 false 。
//
// 
//
//示例 1：
//
//输入：nums = [1,0,2]
//输出：true
//解释：有 1 个全局倒置，和 1 个局部倒置。
//示例 2：
//
//输入：nums = [1,2,0]
//输出：false
//解释：有 2 个全局倒置，和 1 个局部倒置。
// 
//提示：
//
//n == nums.length
//1 <= n <= 105
//0 <= nums[i] < n
//nums 中的所有整数 互不相同
//nums 是范围 [0, n - 1] 内所有数字组成的一个排列
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/global-and-local-inversions
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 全局倒置与局部倒置 {
    @Test
    fun 全局倒置与局部倒置() {
        //因为局部倒置肯定属于一个全局倒置
        //所以，全局倒置的数量只可能大于等于局部倒置的数量
        //那么也就是一个数，其右侧中小于这个数的数量最多只能有一个，且在其右侧下一个位置
        //由此，题目要求可以简化成：下标0、1处的数字只能是0和1；下标2、3处的数字只能是2和3；下标4、5处的数字只能是4和5......
        //根据简化后的题意就可以尽情发挥了
        println("全局倒置与局部倒置:${isIdealPermutation2(intArrayOf(1, 2, 0))}")
    }

    fun isIdealPermutation(nums: IntArray): Boolean {
        var counter = 0

        for (element in nums) {
            (element - counter).let {
                if (it > 1 || it < -1) {
                    return false
                }
            }
            counter++
        }

        return true
    }

    fun isIdealPermutation2(nums: IntArray): Boolean {
        //计数
        //记录数字和下标的差值
        //将差值累加
        //如果差值大于1，说明已不符合题意，返回false
        var counter = 0

        for ((index, element) in nums.withIndex()) {
            counter += element - index
            if (counter > 1) {
                return false
            }
        }

        return true
    }

}