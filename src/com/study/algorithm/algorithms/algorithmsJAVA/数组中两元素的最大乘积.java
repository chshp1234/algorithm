package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.PriorityQueue;

public class 数组中两元素的最大乘积 {
    public int maxProductByPriorityQueue(int[] nums) {
        //排序
        //排序,使得快速的找出最大值和次大值
        //可以使用优先队列,更方便的找出最大值和次大值
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int n : nums) {
            queue.offer(n);
        }
        return (queue.poll() - 1) * (queue.poll() - 1);
    }

    public int maxProduct(int[] nums) {
        //一趟遍历,直接寻找最大值和次大值
        //维护最大值a,和次大值b
        //如果num[i]>a,那么令b=a,a=num[i];
        //如果num[i]>b,那么令b=num[i]
        //最后返回(a - 1) * (b - 1)
        int a = Math.max(nums[0], nums[1]);
        int b = Math.min(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] > a) {
                b = a;
                a = nums[i];
            } else if (nums[i] > b) {
                b = nums[i];
            }
        }

        return (a - 1) * (b - 1);
    }
}
