package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.Arrays;

public class 最少移动次数使数组元素相等II {
    //寻找中位数
    public int minMoves2(int[] nums) {
        //排序
        Arrays.sort(nums);
        int mid = nums[nums.length / 2];
        int result = 0;
        for (int n : nums) {
            result += Math.abs(n - mid);
        }
        return result;
    }
}
