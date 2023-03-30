package com.study.algorithm.algorithms;

import org.junit.Test;

//给你一个整数数组 nums 和一个整数 target 。
//
//向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
//
//例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
//返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
//
// 
//
//示例 1：
//
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
//示例 2：
//
//输入：nums = [1], target = 1
//输出：1
// 
//
//提示：
//
//1 <= nums.length <= 20
//0 <= nums[i] <= 1000
//0 <= sum(nums[i]) <= 1000
//-1000 <= target <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/target-sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 目标和 {

    @Test
    public void 目标和() {

        System.out.println("目标和:" + findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }

    int result = 0;

    public int findTargetSumWays(int[] nums, int target) {
        backTrack(nums, 0, nums.length, 0, target);
        //回溯
        //深度优先搜索+回溯，当遍历到头时，判断当前计数是否等于目标值
        return result;
    }

    public void backTrack(int[] nums, int index, int len, int sumNow, int target) {

        if (index == len) {
            if (sumNow == target) {
                result++;
            }
            return;
        }

        //当前为加号
        sumNow += nums[index];
        backTrack(nums, index + 1, len, sumNow, target);

        //当前为减号
        sumNow -= nums[index] + nums[index];
        backTrack(nums, index + 1, len, sumNow, target);
    }
}
