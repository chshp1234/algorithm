package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test
import kotlin.math.pow

//给定三个整数 x 、 y 和 bound ，返回 值小于或等于 bound 的所有 强整数 组成的列表 。
//
//如果某一整数可以表示为 xi + yj ，其中整数 i >= 0 且 j >= 0，那么我们认为该整数是一个 强整数 。
//
//你可以按 任何顺序 返回答案。在你的回答中，每个值 最多 出现一次。
//
// 
//
//示例 1：
//
//输入：x = 2, y = 3, bound = 10
//输出：[2,3,4,5,7,9,10]
//解释：
//2 = 20 + 30
//3 = 21 + 30
//4 = 20 + 31
//5 = 21 + 31
//7 = 22 + 31
//9 = 23 + 30
//10 = 20 + 32
//示例 2：
//
//输入：x = 3, y = 5, bound = 15
//输出：[2,4,6,8,10,14]
// 
//
//提示：
//
//1 <= x, y <= 100
//0 <= bound <= 106
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/powerful-integers
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 强整数 {

    @Test
    fun 强整数() {
        println(
            "强整数:${
                powerfulIntegers(
                    2, 1, 10
                )
            }"
        )

    }

    fun powerfulIntegers(x: Int, y: Int, bound: Int): List<Int> {
        //枚举,哈希表
        //枚举x和y的所有可能的幂,并将结果加入set集合中
        //注意当x或y为1时,对于任何幂i,1^i都为1
        val res = HashSet<Int>()
        val xPowMap = HashMap<Int, Int>()
        val yPowMap = HashMap<Int, Int>()
        var xPow = 0
        while (true) {
            var yPow = 0
            while (true) {
                val xPowNum = xPowMap[xPow] ?: x.toDouble().pow(xPow.toDouble()).toInt().also { xPowMap[xPow] = it }
                val yPowNum = yPowMap[yPow] ?: y.toDouble().pow(yPow.toDouble()).toInt().also { yPowMap[yPow] = it }
                val sum = xPowNum + yPowNum
                if (sum <= bound) {
                    res.add(sum)
                    if (y > 1) {
                        yPow++
                    } else {
                        break
                    }
                } else if (yPow == 0) {
                    return res.toList()
                } else {
                    break
                }
            }
            if (x > 1) {
                xPow++
            } else {
                return res.toList()
            }
        }
    }
}