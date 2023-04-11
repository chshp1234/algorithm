package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
//
// 
//
//示例：
//
//输入：[1,12,-5,-6,50,3], k = 4
//输出：12.75
//解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
// 
//
//提示：
//
//1 <= k <= n <= 30,000。
//所给数据范围 [-10,000，10,000]。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximum-average-subarray-i
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 子数组最大平均数I {
    @Test
    public void 子数组最大平均数I() {
        int[] ints = {7, 4, 5, 8, 8, 3, 9, 8, 7, 6};
        System.out.println("子数组最大平均数I:" + findMaxAverage(ints, 7));
    }

    public double findMaxAverage(int[] nums, int k) {
        //计算窗口k中的总和，并记录窗口滑动过程中最大的和
        //窗口滑动时，减去第一个将要移出窗口的数，加上下一个将要进入窗口的数，即可保持计算出窗口内的子数组总和
        //最后再用最大和除以窗口大小即可
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = k; i < len; i++) {
            maxSum = Math.max(maxSum, sum = (sum - nums[i - k] + nums[i]));
        }
        return (double) maxSum / k;
    }
}
