package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test
import kotlin.math.pow

class 复写零 {

    @Test
    fun test() {
        println(
            "复写零:${
                duplicateZeros(
                    intArrayOf(1, 1, 0)
                )
            }"
        )
    }

    fun duplicateZeros(arr: IntArray): Unit {
        //方法一：双指针
        //使用一个额外的数组记录数字，遇原数组到0时，额外的数组拷贝两份0，否则拷贝一份原数组中的值
        //最后将额外的数组的值拷贝到原数组中

        //方法二：计数，双指针
        //我们可以统计并记录数组中可统计的元素的最大下标
        //维护一个最大值max，遍历数组，当遇到0时，数量+2，否则数量+1.当max==arr.size时，说明到此下标i为止，更新max=i，数组统计完毕，后面的数将不会在统计遍历到。
        //那么，后续我们可以反向遍历数组，令遍历的最大下标从max开始直到0，再用一个值r=arr.size-1，代表当前待更新的数组的下标
        //当遇到0时，数组元素填充两个0，并且r-=2
        //当遇到非0时，直接更新元素，r-=1
        //注意：当第一步统计max时，每次都要max+1，并且都要判断max是否等于arr.size，如果最后一个数组是0并且还没统计到+1的情况下，max就等于arr.size，
        //说明这最后一个数0将不会再有额外的复写0，
        var max = 0
        var skip = false
        for (i in arr.indices) {
            max++
            if (max == arr.size)
            if (arr[i] == 0) {
                max++
            }
            if (max == arr.size) {
                max = i
                break
            }
        }

        var r = if (skip) {
            //跳过最后一个0（不复写）
            arr[arr.size - 1] = 0
            max--
            arr.size - 2
        } else {
            arr.size - 1
        }
        while (max >= 0) {
            arr[r--] = arr[max]
            if (arr[max] == 0) {
                arr[r--] = 0
            }
            max--
        }
    }
}