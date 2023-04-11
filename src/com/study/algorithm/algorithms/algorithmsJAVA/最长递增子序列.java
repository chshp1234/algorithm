package com.study.algorithm.algorithms.algorithmsJAVA;

//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
//
//子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
//
// 
//示例 1：
//
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
//示例 2：
//
//输入：nums = [0,1,0,3,2,3]
//输出：4
//示例 3：
//
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
// 
//
//提示：
//
//1 <= nums.length <= 2500
//-104 <= nums[i] <= 104
// 
//
//进阶：
//
//你可以设计时间复杂度为 O(n2) 的解决方案吗？
//你能将算法的时间复杂度降低到 O(n log(n)) 吗?
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最长递增子序列 {

    public int lengthOfLIS(int[] nums) {
        //动态规划，同“最长递增子序列的个数”
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 1;
        int result = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i]++;
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
