package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 寻找数组的中心索引 {
    @Test
    public void 寻找数组的中心索引() {
        System.out.println("寻找数组的中心索引:" + pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
    }

    /**
     * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
     *
     * <p>我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
     *
     * <p>如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
     */
    public int pivotIndex(int[] nums) {
        //前缀和
        //数组nums中，第i项的前缀和sum[i]=sum[i-1]+nums[i]
        //计算完数组nums的总和sum，则判断前半部分的和sum[i-1]和后半部分的和sum-sum[i]是否相等即可
        int sum = 0;
        int l = nums.length;
        for (int i = 0; i < l; i++) {
            sum = sum + nums[i];
        }

        int sumSub = 0;
        for (int i = 0; i < l; i++) {
            if (sum - nums[i] - sumSub == sumSub) {
                return i;
            }
            sumSub = sumSub + nums[i];
        }
        return -1;
    }
}
