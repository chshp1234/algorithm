package com.study.algorithm.algorithms;

import org.junit.Test;

//给定一个长度为 n 的整数数组 nums 。
//
//假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数  F 为：
//
//F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
//返回 F(0), F(1), ..., F(n-1)中的最大值 。
//
//生成的测试用例让答案符合 32 位 整数。
//
// 
//
//示例 1:
//
//输入: nums = [4,3,2,6]
//输出: 26
//解释:
//F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
//F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
//F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
//F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
//所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。
//示例 2:
//
//输入: nums = [100]
//输出: 0
// 
//
//提示:
//
//n == nums.length
//1 <= n <= 105
//-100 <= nums[i] <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/rotate-function
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 旋转函数 {

    @Test
    public void 旋转函数() {
        int[] ints = {4, 3, 2, 6};
        System.out.println("旋转函数:" + maxRotateFunctionByDp(ints));
    }

    public int maxRotateFunction(int[] nums) {
        //暴力遍历
        //两层循环，遍历每种情况的sum，并找出最大值
        //超时
        int len = nums.length;

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for (int j = 0; j < len; j++) {
                int index = (i + j) % len;
                sum += index * nums[j];
            }
            max = Math.max(max, sum);
        }

        return max;
    }

    public int maxRotateFunctionByDp(int[] nums) {
        //动态规划，规律
        //设dp[i]为数组旋转i次后，根据选择方程得出的总和值
        //dp[i]=dp[i-1]+sum(nums)-len*nums[len−i](1<=i<len)
        //先一次循环，求出dp[0]和sum(nums)
        //在根据状态方程求出dp[1..len]，并维护最大值max

        int len = nums.length;

        int sum = 0;
        int dp = sum;

        for (int i = 0; i < len; i++) {
            sum += nums[i];
            dp += i * nums[i];
        }
        int max = dp;

        for (int i = 1; i < len; i++) {
            dp = dp + sum - len * nums[len - i];
            max = Math.max(max, dp);
        }

        return max;
    }
}
