package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test
import java.util.*
import kotlin.math.max

class 删除并获得点数 {
    @Test
    fun 删除并获得点数() {
        val nums = intArrayOf(2, 2, 3, 3, 3, 4)
        println("删除并获得点数:${deleteAndEarn(nums)}")
    }

    fun deleteAndEarn(nums: IntArray): Int {
        //排序+动态规划
        //突然发现，有点像“打家劫舍”，那么用动态规划来解决
        //定义select，代表当前数字num选中情况下获得的最大点数，unSelect代表当前数字不选中的情况下的最大点数
        //因为选中后的数字，要删除num-1或num+1的所有数字，那么为了方便判断，先对数组进行排序，这样相邻的数字可以很方便进行判断

        val len = nums.size
        if (len == 1) {
            return nums[0]
        }
        Arrays.sort(nums)
        var select = nums[0]
        var unSelect = 0
        var temp: Int
        for (i in 1 until len) {
            when {
                //如果当前数字和上一个数字相同，那么直接选中
                nums[i] == nums[i - 1] -> {
                    select += nums[i]
                }
                //如果当前数字比上一个数字大1，那么：
                //不管上一个数字选中与否，都不影响不选中当前数字，unSelect=max(select, unSelect)；
                //而若要选中当前数字，那么上一个数字必须不选中，select=unSelect=num[i]，（注意unSelect赋值）
                (nums[i] - nums[i - 1]) == 1 -> {
                    temp = unSelect
                    unSelect = max(select, unSelect)
                    select = temp + nums[i]
                }
                //当前数字远大于上一个数字（相差不止1）
                //那么，不管上一个数字选中与否，都不影响选中与不选中当前数字的情况
                //unSelect=max(select, unSelect)
                //select=max(select, unSelect) + nums[i]
                else -> {
                    temp = max(select, unSelect)
                    select = temp + nums[i]
                    unSelect = temp
                }
            }
        }
        //最后返回遍历到结尾时，两者的最大值
        return max(select, unSelect)
    }
}