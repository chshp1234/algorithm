package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

public class 一维数组的动态和 {
    @Test
    public void 一维数组的动态和() {
        System.out.println(
                "一维数组的动态和（数组前缀和）:" + Arrays.toString(runningSum(new int[] {-1, 0, 1, 2, -1, -4})));
    }

    // 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
    //
    // 请返回 nums 的动态和。
    //
    //
    //
    // 示例 1：
    //
    // 输入：nums = [1,2,3,4]
    // 输出：[1,3,6,10]
    // 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
    // 示例 2：
    //
    // 输入：nums = [1,1,1,1,1]
    // 输出：[1,2,3,4,5]
    // 解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。
    // 示例 3：
    //
    // 输入：nums = [3,1,2,10,1]
    // 输出：[3,4,6,16,17]
    //
    //
    // 提示：
    //
    // 1 <= nums.length <= 1000
    // -10^6 <= nums[i] <= 10^6
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/running-sum-of-1d-array
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int[] runningSum(int[] nums) {

        // 计算数组前缀和，其中第i项前缀和为第i-1项前缀和+第i项的值
        // 固只需一次遍历，依次更新当前项i的前缀和，下一次更新时，值就为nums[i - 1] + nums[i]
        for (int i = 1, l = nums.length; i < l; i++) {
            nums[i] = nums[i - 1] + nums[i];
        }
        return nums;
    }
}
