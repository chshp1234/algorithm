package com.study.algorithm.algorithms.algorithmsKT

//给你一个整数数组 nums （下标从 0 开始）。每一次操作中，你可以选择数组中一个元素，并将它增加 1 。
//
//比方说，如果 nums = [1,2,3] ，你可以选择增加 nums[1] 得到 nums = [1,3,3] 。
//请你返回使 nums 严格递增 的 最少 操作次数。
//
//我们称数组 nums 是 严格递增的 ，当它满足对于所有的 0 <= i < nums.length - 1 都有 nums[i] < nums[i+1] 。一个长度为 1 的数组是严格递增的一种特殊情况。
//
// 
//
//示例 1：
//
//输入：nums = [1,1,1]
//输出：3
//解释：你可以进行如下操作：
//1) 增加 nums[2] ，数组变为 [1,1,2] 。
//2) 增加 nums[1] ，数组变为 [1,2,2] 。
//3) 增加 nums[2] ，数组变为 [1,2,3] 。
//示例 2：
//
//输入：nums = [1,5,2,4,1]
//输出：14
//示例 3：
//
//输入：nums = [8]
//输出：0
// 
//
//提示：
//
//1 <= nums.length <= 5000
//1 <= nums[i] <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-operations-to-make-the-array-increasing
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 最少操作使数组递增 {
    fun minOperations(nums: IntArray): Int {
        //模拟
        //根据题意要求，数组严格递增，那么数组中每个元素都必须比上一个元素大
        //而要是操作数最少，那么如果当前元素比上一个元素小，只需要将当前元素设为上个元素的值+1即可
        //遍历时记录上一个元素，以及当前的操作数
        //判断当前元素是否大于上一个元素
        var last = nums[0]
        var count = 0
        for (i in 1 until nums.size) {
            if (nums[i] <= last) {
                count += (last - nums[i] + 1)
                last++
            } else {
                last = nums[i]
            }

        }
        return count
    }
}