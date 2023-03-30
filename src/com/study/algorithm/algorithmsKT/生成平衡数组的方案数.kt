package com.study.algorithm.algorithmsKT

import org.junit.Test

//给你一个整数数组 nums 。你需要选择 恰好 一个下标（下标从 0 开始）并删除对应的元素。请注意剩下元素的下标可能会因为删除操作而发生改变。
//
//比方说，如果 nums = [6,1,7,4,1] ，那么：
//
//选择删除下标 1 ，剩下的数组为 nums = [6,7,4,1] 。
//选择删除下标 2 ，剩下的数组为 nums = [6,1,4,1] 。
//选择删除下标 4 ，剩下的数组为 nums = [6,1,7,4] 。
//如果一个数组满足奇数下标元素的和与偶数下标元素的和相等，该数组就是一个 平衡数组 。
//
//请你返回删除操作后，剩下的数组 nums 是 平衡数组 的 方案数 。
//
// 
//
//示例 1：
//
//输入：nums = [2,1,6,4]
//输出：1
//解释：
//删除下标 0 ：[1,6,4] -> 偶数元素下标为：1 + 4 = 5 。奇数元素下标为：6 。不平衡。
//删除下标 1 ：[2,6,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：6 。平衡。
//删除下标 2 ：[2,1,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：1 。不平衡。
//删除下标 3 ：[2,1,6] -> 偶数元素下标为：2 + 6 = 8 。奇数元素下标为：1 。不平衡。
//只有一种让剩余数组成为平衡数组的方案。
//示例 2：
//
//输入：nums = [1,1,1]
//输出：3
//解释：你可以删除任意元素，剩余数组都是平衡数组。
//示例 3：
//
//输入：nums = [1,2,3]
//输出：0
//解释：不管删除哪个元素，剩下数组都不是平衡数组。
// 
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/ways-to-make-a-fair-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 生成平衡数组的方案数 {
    @Test
    fun 生成平衡数组的方案数() {
        println(
            "生成平衡数组的方案数:${
                waysToMakeFair(
                    intArrayOf(2,1,6,4)
                )
            }"
        )
    }
    fun waysToMakeFair(nums: IntArray): Int {
        //前缀和
        //一趟遍历记录从左到右的偶数前缀和leftEven，奇数前缀和leftOdd，
        //再反向遍历，如果下标是奇数，则leftOdd -= nums[i]；如果下标是偶数，则leftEven -= nums[i]
        //并记录从右到左的偶数前缀和rightEven，奇数前缀和rightOdd
        //因为删除一个下标后，后面的数都会往前移动，所以此时所有的偶数下标和为leftEven + rightOdd，所有奇数的前缀和为leftOdd + rightEven
        //如果两个和相同，则说明此时是平衡数组，结果+1

        var leftEven = 0
        var leftOdd = 0
        for (i in 0 until nums.size step 2) {
            leftEven += nums[i]
        }
        for (i in 1 until nums.size step 2) {
            leftOdd += nums[i]
        }

        var res = 0

        var rightEven = 0
        var rightOdd = 0
        for (i in nums.size - 1 downTo 0) {
            if (i % 2 == 0) {
                leftEven -= nums[i]
                if (leftOdd + rightEven == leftEven + rightOdd) {
                    res++
                }
                rightEven += nums[i]
            } else {
                leftOdd -= nums[i]
                if (leftOdd + rightEven == leftEven + rightOdd) {
                    res++
                }
                rightOdd += nums[i]
            }


        }

        return res
    }
}