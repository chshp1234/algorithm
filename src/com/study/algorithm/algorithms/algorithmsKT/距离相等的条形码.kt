package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test
import java.util.*

//在一个仓库里，有一排条形码，其中第 i 个条形码为 barcodes[i]。
//
//请你重新排列这些条形码，使其中任意两个相邻的条形码不能相等。 你可以返回任何满足该要求的答案，此题保证存在答案。
//
// 
//
//示例 1：
//
//输入：barcodes = [1,1,1,2,2,2]
//输出：[2,1,2,1,2,1]
//示例 2：
//
//输入：barcodes = [1,1,1,1,2,2,3,3]
//输出：[1,3,1,3,2,1,2,1]
// 
//
//提示：
//
//1 <= barcodes.length <= 10000
//1 <= barcodes[i] <= 10000
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/distant-barcodes
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 距离相等的条形码 {
    @Test
    fun 距离相等的条形码() {
        System.out.println(
            "距离相等的条形码:${
                rearrangeBarcodes(
                    intArrayOf(1, 2, 3, 4)
                )
            }"
        )
    }

    fun rearrangeBarcodes(barcodes: IntArray): IntArray {
        //哈希表,贪心,优先队列(大顶堆)
        //1.使用哈希表记录每个数字出现的频次
        //2.将每个数及其对应的频次加入大顶堆中
        //3.从堆中取出频次最大的两个数,分别加入结果数组中,并将频次都-1
        //4.如果-1后频次还大于0,说明还有此数字没处理,则继续将更新后的数字及其对应的频次加入大顶堆中
        //5.重复操作,直到所有数字都加入结果中(堆中没数据)
        if (barcodes.size == 1) {
            return barcodes
        }
        val res = IntArray(barcodes.size)

        val counter = TreeMap<Int, Int>()
        for (num in barcodes) {
            counter[num] = (counter[num] ?: 0) + 1
        }
        val queue = PriorityQueue(object : Comparator<MutableMap.MutableEntry<Int, Int>> {
            override fun compare(o1: MutableMap.MutableEntry<Int, Int>, o2: MutableMap.MutableEntry<Int, Int>): Int {
                return o2.value - o1.value
            }

        })
        for (entry in counter) {
            queue.offer(entry)
        }
        var f = queue.poll()
        var s = queue.poll()
        var index = 0
        while (true) {
            res[index++] = f.key
            res[index++] = s.key
            if (f.value == 1 && s.value == 1 && queue.isEmpty()) {
                break
            }
            if (f.value == 2 && s.value == 1 && queue.isEmpty()) {
                res[index] = f.key
                break
            }
            if (f.value > 1) {
                f.setValue(f.value - 1)
                queue.offer(f)
            }
            if (s.value > 1) {
                s.setValue(s.value - 1)
                queue.offer(s)
            }
            f = queue.poll()
            s = queue.poll()
        }
        return res
    }
}