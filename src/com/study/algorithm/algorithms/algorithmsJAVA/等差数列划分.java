package com.study.algorithm.algorithms.algorithmsJAVA;

//如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
//
//例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
//给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
//
//子数组 是数组中的一个连续序列。
//
// 
//
//示例 1：
//
//输入：nums = [1,2,3,4]
//输出：3
//解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
//示例 2：
//
//输入：nums = [1]
//输出：0
// 
//
//提示：
//
//1 <= nums.length <= 5000
//-1000 <= nums[i] <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/arithmetic-slices
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 等差数列划分 {
    public int numberOfArithmeticSlices(int[] nums) {
        //动态规划
        //因为子数组最少数量为3，那么从下标2开始遍历
        //记录当前子数组等差值diff，若nums[i]-nums[i-1]=diff，那么 说明将nums[i]加入此子数组也可以构成等差数列。
        //在用一个数lLen记录以nums[i-1]为结尾的构成等差数列的子数组的数量，那么遍历到nums[i]时：
        //若nums[i]加入后可继续构成一个等差数量，那么以nums[i]结尾的子数列的数量为lLen+1；
        //否则重新计算diff=nums[i]-nums[i-1]，并使lLen=0；

        //由此可得状态dp[i]:以下标i为结尾的元素，能够构成等差数列的子数组的数量
        //则dp[i]=dp[i-1]+1(将nums[i]加入后可构成等差数量)；dp[i]=0(将nums[i]加入后不可构成等差数列)
        int len = nums.length;
        if (len < 3) {
            return 0;
        }

        int result = 0;
        int diff = nums[1] - nums[0];
        int lastLen = 0;
        for (int i = 2; i < len; i++) {
            if (nums[i] - nums[i - 1] == diff) {
                lastLen++;
                result += lastLen;
            } else {
                lastLen = 0;
                diff = nums[i] - nums[i - 1];
            }
        }

        return result;
    }
}
