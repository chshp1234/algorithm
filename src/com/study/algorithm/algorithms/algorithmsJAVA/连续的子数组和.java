package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
//
//子数组大小 至少为 2 ，且
//子数组元素总和为 k 的倍数。
//如果存在，返回 true ；否则，返回 false 。
//
//如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
//
// 
//
//示例 1：
//
//输入：nums = [23,2,4,6,7], k = 6
//输出：true
//解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
//示例 2：
//
//输入：nums = [23,2,6,4,7], k = 6
//输出：true
//解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。
//42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
//示例 3：
//
//输入：nums = [23,2,6,4,7], k = 13
//输出：false
// 
//
//提示：
//
//1 <= nums.length <= 105
//0 <= nums[i] <= 109
//0 <= sum(nums[i]) <= 231 - 1
//1 <= k <= 231 - 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/continuous-subarray-sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 连续的子数组和 {

    @Test
    public void 连续的子数组和() {
        int[] nums = new int[]{5, 0, 0, 0, 0};

        System.out.println("连续的子数组和：" + checkSubarraySum(nums, 6));
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        //前缀和+哈希表
        //同余定理：如果两个整数m、n满足n-m能被k整除，那么n和m对k同余
        //
        //即 ( pre(j) - pre (i) ) % k == 0 则 pre(j) % k == pre(i) % k
        //那么我们只需要求出数组前缀和，找出是否存在preSum(i)==preSum(j)即可
        int m = nums.length;
        if (m < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < m; i++) {
            remainder = (remainder + nums[i]) % k;
            if (map.containsKey(remainder)) {
                int prevIndex = map.get(remainder);
                if (i - prevIndex >= 2) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }
}
