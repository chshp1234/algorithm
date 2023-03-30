package com.study.algorithm.algorithmsKT

//给你一个正整数数组 nums 。
//
//元素和 是 nums 中的所有元素相加求和。
//数字和 是 nums 中每一个元素的每一数位（重复数位需多次求和）相加求和。
//返回 元素和 与 数字和 的绝对差。
//
//注意：两个整数 x 和 y 的绝对差定义为 |x - y| 。
//
// 
//
//示例 1：
//
//输入：nums = [1,15,6,3]
//输出：9
//解释：
//nums 的元素和是 1 + 15 + 6 + 3 = 25 。
//nums 的数字和是 1 + 1 + 5 + 6 + 3 = 16 。
//元素和与数字和的绝对差是 |25 - 16| = 9 。
//示例 2：
//
//输入：nums = [1,2,3,4]
//输出：0
//解释：
//nums 的元素和是 1 + 2 + 3 + 4 = 10 。
//nums 的数字和是 1 + 2 + 3 + 4 = 10 。
//元素和与数字和的绝对差是 |10 - 10| = 0 。
// 
//
//提示：
//
//1 <= nums.length <= 2000
//1 <= nums[i] <= 2000
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/difference-between-element-sum-and-digit-sum-of-an-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 数组元素和与数字和的绝对差 {
    fun differenceOfSum(nums: IntArray): Int {
        //模拟
        //用一个数sum记录元素和，用一个数nSum记录数字和
        //最后返回两个数的差的绝对值
        var sum = 0
        var nSum = 0
        for (n in nums) {
            sum += n
            var left = n
            while (left > 0) {
                nSum += (left % 10)
                left /= 10
            }
        }

        return Math.abs(sum - nSum)
    }
}