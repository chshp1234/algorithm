package com.study.algorithm.algorithmsKT

//给你一个下标从 0 开始的整数数组 nums ，请你找出一个下标从 0 开始的整数数组 answer ，其中：
//
//answer.length == nums.length
//answer[i] = |leftSum[i] - rightSum[i]|
//其中：
//
//leftSum[i] 是数组 nums 中下标 i 左侧元素之和。如果不存在对应的元素，leftSum[i] = 0 。
//rightSum[i] 是数组 nums 中下标 i 右侧元素之和。如果不存在对应的元素，rightSum[i] = 0 。
//返回数组 answer 。
//
// 
//
//示例 1：
//
//输入：nums = [10,4,8,3]
//输出：[15,1,11,22]
//解释：数组 leftSum 为 [0,10,14,22] 且数组 rightSum 为 [15,11,3,0] 。
//数组 answer 为 [|0 - 15|,|10 - 11|,|14 - 3|,|22 - 0|] = [15,1,11,22] 。
//示例 2：
//
//输入：nums = [1]
//输出：[0]
//解释：数组 leftSum 为 [0] 且数组 rightSum 为 [0] 。
//数组 answer 为 [|0 - 0|] = [0] 。
// 
//
//提示：
//
//1 <= nums.length <= 1000
//1 <= nums[i] <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/left-and-right-sum-differences
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 左右元素和的差值 {
    fun leftRigthDifference(nums: IntArray): IntArray {
        //前缀和
        //先求出数组总和当作rightSum,在用一个前缀和当作leftSum
        //遍历时,rightSum -= nums[i],然后计算结果,之后leftSum += nums[i]

        val res = IntArray(nums.size)
        var rightSum = nums.sum()
        var leftSum = 0

        for (i in nums.indices) {
            rightSum -= nums[i]
            res[i] = Math.abs(leftSum - rightSum)
            leftSum += nums[i]
        }

        return res
    }
}