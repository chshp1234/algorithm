package com.study.algorithm.algorithmsKT

//给你一个下标从 0 开始、严格递增 的整数数组 nums 和一个正整数 diff 。如果满足下述全部条件，则三元组 (i, j, k) 就是一个 算术三元组 ：
//
//i < j < k ，
//nums[j] - nums[i] == diff 且
//nums[k] - nums[j] == diff
//返回不同 算术三元组 的数目。
//
// 
//
//示例 1：
//
//输入：nums = [0,1,4,6,7,10], diff = 3
//输出：2
//解释：
//(1, 2, 4) 是算术三元组：7 - 4 == 3 且 4 - 1 == 3 。
//(2, 4, 5) 是算术三元组：10 - 7 == 3 且 7 - 4 == 3 。
//示例 2：
//
//输入：nums = [4,5,6,7,8,9], diff = 2
//输出：2
//解释：
//(0, 2, 4) 是算术三元组：8 - 6 == 2 且 6 - 4 == 2 。
//(1, 3, 5) 是算术三元组：9 - 7 == 2 且 7 - 5 == 2 。
// 
//
//提示：
//
//3 <= nums.length <= 200
//0 <= nums[i] <= 200
//1 <= diff <= 50
//nums 严格 递增
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/number-of-arithmetic-triplets
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 算术三元组的数目 {
    fun arithmeticTriplets(nums: IntArray, diff: Int): Int {
        //哈希表
        //先用哈希表记录每个元素
        //然后遍历以每个元素i为中心,判断nums[i] - diff和nums[i] + diff是否在哈希表中,如果存在,则结果+1

        //优化:为了充分利用"单调递增"的条件,可以不用先遍历加入哈希表,
        //可以边判断边加入.对每个数num直接求num-diff和num-2*diff是否存在于哈希表中即可,之后在将num加入哈希表中。
        var count = 0
        val set = nums.toMutableSet()
        for (i in 1 until nums.size - 1) {
            if (set.contains(nums[i] - diff) && set.contains(nums[i] + diff)) {
                count++
            }
        }
        return count
    }
}