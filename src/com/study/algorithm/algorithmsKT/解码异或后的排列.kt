package com.study.algorithm.algorithmsKT

import org.junit.Test

class 解码异或后的排列 {
    @Test
    fun 解码异或后的排列() {
        val nums = intArrayOf(3, 1)
        println("解码异或后的排列:${decode(nums)}")
    }

    fun decode(encoded: IntArray): IntArray {
        //位运算，异或操作满足交换律
        //根据规律（大量运算）可得：result[0]^encoded[1]^encoded[3]^.(step 2).^encoded[n-2]=1^2^3^..^n
        //那么我们可以得出原数组第一个数result[0]
        //最后就是数组填空了，根据题“解码异或后的数组”
        val len = encoded.size + 1
        var stub = 0
        for (i in 1..len) {
            stub = stub xor i
        }

        var start = stub
        for (i in 1 until len step 2) {
            start = start xor encoded[i]
        }

        val result = IntArray(len)
        result[0] = start
        for (i in 1 until len) {
            result[i] = encoded[i - 1] xor result[i - 1]
        }
        return result
    }

}