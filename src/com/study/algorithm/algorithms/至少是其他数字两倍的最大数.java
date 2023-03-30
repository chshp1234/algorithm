package com.study.algorithm.algorithms;

import org.junit.Test;

public class 至少是其他数字两倍的最大数 {
    @Test
    public void 至少是其他数字两倍的最大数() {
        long start = System.nanoTime();
        int index = dominantIndex(new int[] {1, 2, 3, 4});
        long end = System.nanoTime();
        System.out.println("start=" + start + " end=" + end + " result=" + (end - start));
        System.out.println(index);
    }
    /**
     * 在一个给定的数组nums中，总是存在一个最大元素 。
     *
     * <p>查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
     *
     * <p>如果是，则返回最大元素的索引，否则返回-1。
     */
    public int dominantIndex(int[] nums) {
        int max = 0;
        int sec = 0;
        int index = -1;
        for (int i = 0, l = nums.length; i < l; i++) {
            if (nums[i] > max) {
                sec = max;
                max = nums[i];
                index = i;
            } else if (nums[i] > sec) {
                sec = nums[i];
            }
        }
        if (max >= sec * 2) {
            return index;
        }
        return -1;
    }
}
