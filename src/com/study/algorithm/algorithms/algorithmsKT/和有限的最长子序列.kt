package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test

//给你一个长度为 n 的整数数组 nums ，和一个长度为 m 的整数数组 queries 。
//
//返回一个长度为 m 的数组 answer ，其中 answer[i] 是 nums 中 元素之和小于等于 queries[i] 的 子序列 的 最大 长度  。
//
//子序列 是由一个数组删除某些元素（也可以不删除）但不改变剩余元素顺序得到的一个数组。
//
// 
//
//示例 1：
//
//输入：nums = [4,5,2,1], queries = [3,10,21]
//输出：[2,3,4]
//解释：queries 对应的 answer 如下：
//- 子序列 [2,1] 的和小于或等于 3 。可以证明满足题目要求的子序列的最大长度是 2 ，所以 answer[0] = 2 。
//- 子序列 [4,5,1] 的和小于或等于 10 。可以证明满足题目要求的子序列的最大长度是 3 ，所以 answer[1] = 3 。
//- 子序列 [4,5,2,1] 的和小于或等于 21 。可以证明满足题目要求的子序列的最大长度是 4 ，所以 answer[2] = 4 。
//示例 2：
//
//输入：nums = [2,3,4,5], queries = [1]
//输出：[0]
//解释：空子序列是唯一一个满足元素和小于或等于 1 的子序列，所以 answer[0] = 0 。
// 
//
//提示：
//
//n == nums.length
//m == queries.length
//1 <= n, m <= 1000
//1 <= nums[i], queries[i] <= 106
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/longest-subsequence-with-limited-sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 和有限的最长子序列 {
    @Test
    fun 和有限的最长子序列() {
        println(
            "和有限的最长子序列:${
                answerQueries(
                    intArrayOf(2, 3, 4, 5), intArrayOf(1)
                )
            }"
        )
    }

    fun answerQueries(nums: IntArray, queries: IntArray): IntArray {
        //贪心,排序,双指针
        //对nums和queries进行排序
        //并使记录nums的前缀和preSum
        //如果preSum小于等于queries的值,则下标+1;否则记录queries中则个数对应的最大元素个数
        val patten = Array(queries.size) {
            intArrayOf(queries[it], it)
        }
        patten.sortBy {
            it[0]
        }
        nums.sort()
        var index = -1
        var preSum = 0

        val res = IntArray(queries.size)

        for (i in patten.indices) {
            if (index != nums.size) {
                while (preSum <= patten[i][0]) {
                    index++
                    if (index < nums.size) {
                        preSum += nums[index]
                    } else {
                        break
                    }
                }
            }

            res[patten[i][1]] = index
        }

        return res
    }
}