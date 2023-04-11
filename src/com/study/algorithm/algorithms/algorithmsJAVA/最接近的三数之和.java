package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

public class 最接近的三数之和 {

    @Test
    public void 最接近的三数之和() {

        System.out.println("最接近的三数之和：" + threeSumClosest(new int[] {1, 2, 3, 4, 5, 100}, 102));
        //        System.out.println("有效括号的嵌套深度：" + calculateDepth(seq, seq.length()));
    }

    public int threeSumClosest(int[] nums, int target) {

        // 方法一：三层循环判断

        // 方法二：双指针移动（本解法）
        // 首先为了寻求规律，对数组进行排序。
        // 从小到大（从左到右）遍历第一个数f，再在[f+1,len-1]的范围中遍历第二个数s和第三个数t
        // 由于数组是有序的，我们遍历s和t时分别从f+1，len-1（也就是头和尾）开始遍历
        // 当f+s+t>target时，令t-1，因为数组此时是排序的，其他任何情况（不论f+1还是s+1）都会使得结果值更大
        // 同理，f+s+t<target时，令s+1
        // f+s+t=target时，就可以直接返回该值
        // 优化：防止重复计算，我们可以判断当前遍历的值和上一次遍历的是否相等
        Arrays.sort(nums);
        int len = nums.length;
        int result = nums[0] + nums[1] + nums[2];
        int sum;
        int diff;
        int s, t;

        // 枚举 first
        for (int f = 0; f < len; f++) {

            // 保证和上一次枚举的元素不相等
            if (f != 0 && nums[f] == nums[f - 1]) {
                continue;
            }

            // 使用双指针枚举 second 和 third
            s = f + 1;
            t = len - 1;

            // 双指针移动，若s<t
            while (s < t) {
                sum = nums[f] + nums[s] + nums[t];
                diff = sum - target;
                // 根据差值的绝对值来更新答案
                if (Math.abs(diff) < Math.abs(result - target)) {
                    result = sum;
                }

                // 如果差值>0（和大于 target），移动 third 对应的指针
                if (diff > 0) {
                    t--;
                    // 移动到下一个不相等的元素
                    while (s < t && nums[t] == nums[t + 1]) {
                        t--;
                    }
                }
                // 如果差值<0（和小于 target），移动 b 对应的指针
                else if (diff < 0) {
                    s++;
                    // 移动到下一个不相等的元素
                    while (s < t && nums[s] == nums[s - 1]) {
                        s++;
                    }
                }
                // 如果和为 target 直接返回答案
                else {
                    return sum;
                }
            }
        }

        return result;
    }
}
