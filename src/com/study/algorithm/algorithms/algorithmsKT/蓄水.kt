package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test
import java.util.*

//给定 N 个无限容量且初始均空的水缸，每个水缸配有一个水桶用来打水，第 i 个水缸配备的水桶容量记作 bucket[i]。小扣有以下两种操作：
//
//升级水桶：选择任意一个水桶，使其容量增加为 bucket[i]+1
//蓄水：将全部水桶接满水，倒入各自对应的水缸
//每个水缸对应最低蓄水量记作 vat[i]，返回小扣至少需要多少次操作可以完成所有水缸蓄水要求。
//
//注意：实际蓄水量 达到或超过 最低蓄水量，即完成蓄水要求。
//
//示例 1：
//
//输入：bucket = [1,3], vat = [6,8]
//
//输出：4
//
//解释：
//第 1 次操作升级 bucket[0]；
//第 2 ~ 4 次操作均选择蓄水，即可完成蓄水要求。
//
//
//示例 2：
//
//输入：bucket = [9,0,1], vat = [0,2,2]
//
//输出：3
//
//解释：
//第 1 次操作均选择升级 bucket[1]
//第 2~3 次操作选择蓄水，即可完成蓄水要求。
//
//提示：
//
//1 <= bucket.length == vat.length <= 100
//0 <= bucket[i], vat[i] <= 10^4
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/o8SXZn
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 蓄水 {

    @Test
    fun 蓄水() {
        println(
            "蓄水:${
                storeWater(
                    intArrayOf(
                        21, 56
                    ),
                    intArrayOf(
                        3230, 8299
                    )
                )
            }"
        )
    }

    fun storeWater(bucket: IntArray, vat: IntArray): Int {
        //贪心/优先队列:
        //
        //优先队列按vat[i] / bucket[i]进行排序
        //令最大添加(循环)max次(最大除数)
        //每次将最大次数的下标处的水桶+1,并重新计算更新最大次数
        val queue = PriorityQueue(object : Comparator<Int> {
            override fun compare(o1: Int, o2: Int): Int {
                    return (vat[o2] / bucket[o2]) - (vat[o1] / bucket[o1])
            }
        })
        var up = 0

        for (i in bucket.indices) {
            if (bucket[i] == 0) {
                bucket[i] = 1
                up++
            }
            queue.offer(i)
        }
        var max = up + queue.peek().let {
            if (vat[it] == 0) {
                return 0
            }
            (vat[it] + bucket[it] - 1) / bucket[it]
        }

        repeat(max) {
            val i = queue.poll()
            val count = (up + (vat[i] + bucket[i] - 1) / bucket[i])
            if (count < max) {
                max = count
            }
            bucket[i]++
            up++
            queue.offer(i)
        }

        return max
    }
}