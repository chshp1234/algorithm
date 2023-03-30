package com.study.algorithm.algorithmsKT

import org.junit.Test
import java.util.*


//给你一个非负整数数组 nums 。在一步操作中，你必须：
//
//选出一个正整数 x ，x 需要小于或等于 nums 中 最小 的 非零 元素。
//nums 中的每个正整数都减去 x。
//返回使 nums 中所有元素都等于 0 需要的 最少 操作数。
//
// 
//
//示例 1：
//
//输入：nums = [1,5,0,3,5]
//输出：3
//解释：
//第一步操作：选出 x = 1 ，之后 nums = [0,4,0,2,4] 。
//第二步操作：选出 x = 2 ，之后 nums = [0,2,0,0,2] 。
//第三步操作：选出 x = 2 ，之后 nums = [0,0,0,0,0] 。
//示例 2：
//
//输入：nums = [0]
//输出：0
//解释：nums 中的每个元素都已经是 0 ，所以不需要执行任何操作。
// 
//
//提示：
//
//1 <= nums.length <= 100
//0 <= nums[i] <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/make-array-zero-by-subtracting-equal-amounts
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 使数组中所有元素都等于零 {

    @Test
    fun 使数组中所有元素都等于零() {
        println(
            "使数组中所有元素都等于零:${
                minimumOperations(
                    intArrayOf(1, 1, 3, 2, 2, 3)
                )
            }"
        )
    }

    fun minimumOperations(nums: IntArray): Int {
        //堆
        //将所有大于0的数加入优先队列中,并找出最大数max
        //每次从堆中选出一个最小的数,如果当前最小数min和上一个最小数last不相同,则max=max-(min-last)
        //直到max=0时,所有数都将为0
        if (nums.size == 1) {
            return if (nums[0] == 0) 0 else 1
        }

        val queue = PriorityQueue<Int>()
        var max = 0
        for (n in nums.indices) {
            if (nums[n] > 0) {
                queue.offer(n)
            }
            max = Math.max(max, n)
        }

        if (max == 0) {
            return 0
        }

        var res = 0
        var last = 0
        while (true) {
            val min = queue.poll()
            if (min != last) {
                max -= (min - last)
                res++
                if (max == 0) {
                    break
                }
            }
            last = min
        }

        return res

    }

    //方法二：哈希集合
    //由于每次操作都将数组中的所有非零元素减少一个相同的值，因此数组中的相等元素减少到 0 的操作数相等，数组中的不相等元素减少到 0 的操作数不相等。
    //
    //又由于使用贪心策略操作时，每次操作都会将数组中的最小非零元素减少到 0，因此最少操作数等于数组中的不同非零元素的个数。
    //
    //使用哈希集合存储数组中的所有非零元素，则哈希集合的大小等于数组中的不同非零元素的个数，即为最少操作数。
    //
    //需要注意的是，由于目标是将数组中的所有元素减少 0，如果数组中的一个元素已经是 0 则不需要对该元素执行操作，因此只需要考虑数组中的不同非零元素的个数。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode.cn/problems/make-array-zero-by-subtracting-equal-amounts/solution/shi-shu-zu-zhong-suo-you-yuan-su-du-deng-ix12/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    fun minimumOperationsByLeetCode(nums: IntArray): Int {
        val set: MutableSet<Int> = HashSet()
        for (num in nums) {
            if (num > 0) {
                set.add(num)
            }
        }
        return set.size
    }

}