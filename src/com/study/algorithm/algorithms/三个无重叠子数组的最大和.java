package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

//给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且 3 * k 项的和最大的子数组，并返回这三个子数组。
//
//以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。
//
// 
//
//示例 1：
//
//输入：nums = [1,2,1,2,6,7,5,1], k = 2
//输出：[0,3,5]
//解释：子数组 [1, 2], [2, 6], [7, 5] 对应的起始下标为 [0, 3, 5]。
//也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。
//示例 2：
//
//输入：nums = [1,2,1,2,1,2,1,2,1], k = 2
//输出：[0,2,4]
// 
//
//提示：
//
//1 <= nums.length <= 2 * 104
//1 <= nums[i] < 216
//1 <= k <= floor(nums.length / 3)
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 三个无重叠子数组的最大和 {
    @Test
    public void 三个无重叠子数组的最大和() {
        System.out.println("三个无重叠子数组的最大和:" + Arrays.toString(maxSumOfThreeSubarrays(
                new int[]{1, 2, 1, 2, 6, 7, 5, 1}, 2)));
    }

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {

        //动态规划
        //先用前缀和求出每k个数为一个区间时的区间和preSum
        //思路：
        //1.找出一个最大子数组：找出以i为第一个数组起点时，最大的一个子数组
        //2.找出两个最大子数组：找出以i为第二个数组起点时，最大的两个子数组
        //3.找出三个最大子数组：找出以i为第三个数组起点时，最大的三个子数组

        int len = nums.length;
        int[] result = new int[]{0, k, 2 * k};
        if (3 * k == len) {
            return result;
        }
        len = len - k + 1;
        int[] preSum = new int[len];
        preSum[0] = nums[0];
        for (int i = 1; i < k; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        preSum[0] = preSum[k - 1];
        //求k个数为一组时的各个区间和
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i - 1] + nums[i + k - 1] - nums[i - 1];
        }

        //1.找出一个最大子数组：找出以i为第一个数组起点时，最大的一个子数组
        //dp[i]代表最大子数组的下标
        int[] dp1 = new int[len];
        for (int i = 1; i < len; i++) {
            if (preSum[i] > preSum[dp1[i - 1]]) {
                dp1[i] = i;
            } else {
                dp1[i] = dp1[i - 1];
            }
        }

        //2.找出两个最大子数组：找出以i为第二个数组起点时，最大的两个子数组
        //dp2[i][0]代表最大子数组的第一个下标
        //dp2[i][1]代表最大子数组的第二个下标
        int[][] dp2 = new int[len][2];
        dp2[k][0] = 0;
        dp2[k][1] = k;
        for (int i = k + 1; i < len; i++) {
            //以i为结尾时的区间和preSum[i]，在[0..i-k]上的最大区间和的起始下标dp1[i - k]
            if ((preSum[i] + preSum[dp1[i - k]]) > (preSum[dp2[i - 1][0]] + preSum[dp2[i - 1][1]])) {
                //当前组合的区间和更大
                dp2[i][0] = dp1[i - k];
                dp2[i][1] = i;
            } else {
                dp2[i][0] = dp2[i - 1][0];
                dp2[i][1] = dp2[i - 1][1];
            }
        }

        long maxSum = preSum[0] + preSum[k] + preSum[2 * k];
        //3.找出三个最大子数组：找出以i为第三个数组起点时，最大的三个子数组
        for (int i = 2 * k + 1; i < len; i++) {
            //根据dp2,找出[0..i-k]中最大子数组的两个下标
            int index0 = dp2[i - k][0];
            int index1 = dp2[i - k][1];
            //拿之与当前i下标的区间和相加
            long currentSum = preSum[index0] + preSum[index1] + preSum[i];
            //判断和上一个最大区间和相比
            if (currentSum > maxSum) {
                //若更大，更新下标值（此时只需要知道最大和的三个下标以及值）
                maxSum = currentSum;
                result[0] = index0;
                result[1] = index1;
                result[2] = i;
            }
        }

        return result;
    }

    //滑动窗口
    public int[] maxSumOfTwoSubarraysByLeetCode(int[] nums, int k) {
        int[] ans = new int[2];
        int sum1 = 0, maxSum1 = 0, maxSum1Idx = 0;
        int sum2 = 0, maxSum12 = 0;
        for (int i = k; i < nums.length; ++i) {
            sum1 += nums[i - k];
            sum2 += nums[i];
            if (i >= k * 2 - 1) {
                if (sum1 > maxSum1) {
                    maxSum1 = sum1;
                    maxSum1Idx = i - k * 2 + 1;
                }
                if (maxSum1 + sum2 > maxSum12) {
                    maxSum12 = maxSum1 + sum2;
                    ans[0] = maxSum1Idx;
                    ans[1] = i - k + 1;
                }
                sum1 -= nums[i - k * 2 + 1];
                sum2 -= nums[i - k + 1];
            }
        }
        return ans;
    }
//
//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays/solution/san-ge-wu-zhong-die-zi-shu-zu-de-zui-da-4a8lb/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
