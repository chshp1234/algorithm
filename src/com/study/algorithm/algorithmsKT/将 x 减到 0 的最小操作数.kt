package com.study.algorithm.algorithmsKT

import org.junit.Test

//给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要 修改 数组以供接下来的操作使用。
//
//如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
//
// 
//
//示例 1：
//
//输入：nums = [1,1,4,2,3], x = 5
//输出：2
//解释：最佳解决方案是移除后两个元素，将 x 减到 0 。
//示例 2：
//
//输入：nums = [5,6,7,8,9], x = 4
//输出：-1
//示例 3：
//
//输入：nums = [3,2,20,1,1,3], x = 10
//输出：5
//解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
// 
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 104
//1 <= x <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class `将 x 减到 0 的最小操作数` {
    @Test
    fun `将 x 减到 0 的最小操作数`() {
        println(
            "`将 x 减到 0 的最小操作数`:${
                minOperations(
                    intArrayOf(1, 1), 4
                )
            }"
        )
    }

    fun minOperations(nums: IntArray, x: Int): Int {
        //滑动窗口
        //因为所有元素的值都是正整数，所以，数组中所有子数组元素和都将大于0
        //如果头尾元素和为x时，那么中间剩余部分的元素和target=sum-x
        //我们可以使用双指针lr滑动窗口，目标是使得窗口内的元素等于target，那么首尾去除的元素的元素和就为x，个数res为nums.size - (r - l + 1)
        //求出所有窗口值为target的情况，保留其中个数res的最小值
        //因为所有元素的值都是正整数，所以当r指针右移时，窗口元素和将会一直增大，l指针右移时，窗口的元素和将会越来越小
        //所以，当窗口元素和s=target时，得出其中一个情况，判断并保留res最小值
        //当窗口元素和s>target时，就令左指针l右移，直到s<=target
        //每次r指针右移，并加上右移后的元素的值，直到遍历结束
        //注：如果target<0，说明nums.sum()<x，去除所有元素都得不到目标值x；如果target=0时，去除所有元素正好得到目标值x
        val target = nums.sum() - x
        if (target < 0) {
            return -1
        }
        if (target == 0) {
            return nums.size
        }
        var l = 0
        var r = 0
        var s = 0
        var res = nums.size
        while (r < nums.size) {
            s += nums[r]
            if (s == target) {
                res = Math.min(res, nums.size - (r - l + 1))
            } else if (s > target) {
                do {
                    s -= nums[l]
                    l++
                } while (s > target)
                if (s == target) {
                    res = Math.min(res, nums.size - (r - l + 1))
                }
            }
            r++
        }
        return if (res == nums.size) -1 else res
    }
}