package com.study.algorithm.algorithms.algorithmsKT

import java.util.*

//我们有一个 n 项的集合。给出两个整数数组 values 和 labels ，第 i 个元素的值和标签分别是 values[i] 和 labels[i]。还会给出两个整数 numWanted 和 useLimit 。
//
//从 n 个元素中选择一个子集 s :
//
//子集 s 的大小 小于或等于 numWanted 。
//s 中 最多 有相同标签的 useLimit 项。
//一个子集的 分数 是该子集的值之和。
//
//返回子集 s 的最大 分数 。
//
// 
//
//示例 1：
//
//输入：values = [5,4,3,2,1], labels = [1,1,2,2,3], numWanted = 3, useLimit = 1
//输出：9
//解释：选出的子集是第一项，第三项和第五项。
//示例 2：
//
//输入：values = [5,4,3,2,1], labels = [1,3,3,3,2], numWanted = 3, useLimit = 2
//输出：12
//解释：选出的子集是第一项，第二项和第三项。
//示例 3：
//
//输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], numWanted = 3, useLimit = 1
//输出：16
//解释：选出的子集是第一项和第四项。
// 
//
//提示：
//
//n == values.length == labels.length
//1 <= n <= 2 * 104
//0 <= values[i], labels[i] <= 2 * 104
//1 <= numWanted, useLimit <= n
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/largest-values-from-labels
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 受标签影响的最大值 {
    fun largestValsFromLabels(values: IntArray, labels: IntArray, numWanted: Int, useLimit: Int): Int {
        //哈希表,大顶堆
        //用哈希表统计每个标签遍历的次数,并用大顶堆将值按最大进行排序
        //每次从堆中选出当前最大值,并判断该值对应的标签是否已经超过使用次数
        //如果超过了,则继续选出下雨给最大值,如果没超过则将该值相加入结果中
        //直到队列为空,或者达到想要的数字个数为止
        val counter = HashMap<Int, Int>()
        val queue = PriorityQueue(object : Comparator<Int> {
            override fun compare(o1: Int, o2: Int): Int {
                return values[o2] - values[o1]
            }
        })
        repeat(values.size) {
            queue.offer(it)
        }
        var res = 0
        var num = 0
        while (num < numWanted && !queue.isEmpty()) {
            val top = queue.poll()
            val count = counter[labels[top]]
            (count ?: 0).let {
                if (it < useLimit) {
                    counter[labels[top]] = it + 1
                    res += values[top]
                    num++
                }
            }
        }
        return res
    }
}