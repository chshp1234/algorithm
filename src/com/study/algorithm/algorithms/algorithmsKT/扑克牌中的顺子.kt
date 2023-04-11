package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test
import java.util.*

class 扑克牌中的顺子 {
    @Test
    fun 扑克牌中的顺子() {
        System.out.printf("扑克牌中的顺子:${isStraight(intArrayOf(12, 1, 8, 12, 12))}")
    }

    fun isStraight(nums: IntArray): Boolean {
        //排序
        //统计：
        //1.0（大小王）的数量zeroSize
        //2.其他牌中每相邻牌的差值和lostSize，若相邻差为1，则说明可以连起来，否则连不起来，那么lostSize加上差值-1
        //若lostSize>zeroSize，则不是顺子
        Arrays.sort(nums)
        val len = nums.size
        var index = 0

        var zeroSize = 0
        var lostSize = 0

        while (index < len) {
            if (nums[index++] == 0) {
                zeroSize++
            } else {
                break
            }
        }

        while (index < len) {
            if (nums[index] == nums[index - 1]) {
                return false
            }
            lostSize += (nums[index] - nums[index - 1] - 1)
            index++
        }

        return zeroSize >= lostSize
    }
}