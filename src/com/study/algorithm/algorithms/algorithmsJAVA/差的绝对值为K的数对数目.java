package com.study.algorithm.algorithms.algorithmsJAVA;

public class 差的绝对值为K的数对数目 {
    public int countKDifference(int[] nums, int k) {
        //模拟
        //直接两层循环，判断两数只差的绝对值是否为k即可
        int result = 0;
        for (int i = 0, l = nums.length; i < l; i++) {
            for (int j = i + 1; j < l; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    result++;
                }
            }
        }
        return result;
    }
}
