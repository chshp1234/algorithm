package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test

//给你一个整数数组 nums 和两个整数 firstLen 和 secondLen，请你找出并返回两个非重叠 子数组 中元素的最大和，长度分别为 firstLen 和 secondLen 。
//
//长度为 firstLen 的子数组可以出现在长为 secondLen 的子数组之前或之后，但二者必须是不重叠的。
//
//子数组是数组的一个 连续 部分。
//
// 
//
//示例 1：
//
//输入：nums = [0,6,5,2,2,5,1,9,4], firstLen = 1, secondLen = 2
//输出：20
//解释：子数组的一种选择中，[9] 长度为 1，[6,5] 长度为 2。
//示例 2：
//
//输入：nums = [3,8,1,3,2,1,8,9,0], firstLen = 3, secondLen = 2
//输出：29
//解释：子数组的一种选择中，[3,8,1] 长度为 3，[8,9] 长度为 2。
//示例 3：
//
//输入：nums = [2,1,5,6,0,9,5,0,3,8], firstLen = 4, secondLen = 3
//输出：31
//解释：子数组的一种选择中，[5,6,0,9] 长度为 4，[0,3,8] 长度为 3。
// 
//
//提示：
//
//1 <= firstLen, secondLen <= 1000
//2 <= firstLen + secondLen <= 1000
//firstLen + secondLen <= nums.length <= 1000
//0 <= nums[i] <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-sum-of-two-non-overlapping-subarrays
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 两个非重叠子数组的最大和 {

    @Test
    fun 两个非重叠子数组的最大和() {
        System.out.println(
            "两个非重叠子数组的最大和:${
                maxSumTwoNoOverlap(
                    intArrayOf(0, 6, 5, 2, 2, 5, 1, 9, 4),
                    1,
                    2
                )
            }"
        )
    }

    fun maxSumTwoNoOverlap(nums: IntArray, firstLen: Int, secondLen: Int): Int {
        //动态规划+滑动窗口
        //设dp[i][0]:以下标i+len-1(子数组长度)为结尾时,最大子数组的和;
        //设dp[i][1]:以下标i为子数组起始项时,子数组的和;
        //那么对于两个长度的子数组firstLen和secondLen,我们只要分别求出当firstLen长度的子数组在前面时,最大子数组的和,以及firstLen长度的子数组在后面时,最大子树的和
        //两个值中最大值即为整体的最大子数组的和
        //1.先得出firstDp和secondDp
        //2.然后循环len - firstLen - secondLen次
        //firstSum = firstDp[i][0]:以下标i+len-1(子数组长度)为结尾时,前面一个最大子数组的和
        //secondSum = secondDp[firstLen + i][1]:以firstLen + i为起始项,后面一个子数组的和
        //res = Math.max(res, secondSum + firstSum)
        val fd = getDpArray(nums, firstLen)
        val sd = getDpArray(nums, secondLen)
        return Math.max(maxSum(fd, firstLen, sd, secondLen, nums.size), maxSum(sd, secondLen, fd, firstLen, nums.size))
    }

    fun getDpArray(nums: IntArray, len: Int): Array<IntArray> {
        val res = Array(nums.size - len + 1) {
            IntArray(2)
        }
        var preSum = 0
        repeat(len) {
            preSum += nums[it]
        }
        res[0][0] = preSum
        res[0][1] = preSum

        for (i in len until nums.size) {
            //滑动窗口,以i为结尾时,子数组的和
            preSum -= nums[i - len]
            preSum += nums[i]
            //更新最大子数组和
            res[i - len + 1][0] = Math.max(res[i - len][0], preSum)
            res[i - len + 1][1] = preSum
        }
        return res
    }

    fun maxSum(firstDp: Array<IntArray>, firstLen: Int, secondDp: Array<IntArray>, secondLen: Int, len: Int): Int {
        var res = 0

        for (i in 0..len - firstLen - secondLen) {
            val firstSum = firstDp[i][0]
            val secondSum = secondDp[firstLen + i][1]
            res = Math.max(res, secondSum + firstSum)
        }

        return res
    }

}