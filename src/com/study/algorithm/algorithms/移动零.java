package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

// 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 示例:
//
// 输入: [0,1,0,3,12]
// 输出: [1,3,12,0,0]
// 说明:
//
// 必须在原数组上操作，不能拷贝额外的数组。
// 尽量减少操作次数。
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/move-zeroes
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 移动零 {
    @Test
    public void 移动零() {
        int[] ints = {1, 1, 5, 2, 0, 3, 12, 0};
        moveZeroes(ints);
        System.out.println("移动零:" + Arrays.toString(ints));
    }

    public void moveZeroes(int[] nums) {

        // 双指针（偷看了标签）
        // 其中第一个指针指向第一个‘0’，第二个指针在第一个指针右侧，不断遍历后找到第一个不是‘0’的数，
        // 此时左指针左侧全为非‘0’元素，右指针左侧直到左指针处全为‘0’元素，交换两元素。
        // 此时‘0’就跑到后面，非‘0’元素则放置在前面。

        int zero = 0;
        int non_zero;

        int len = nums.length;

        // 第一次循环，找到第一个‘0’元素
        while (zero < len && nums[zero] != 0) {
            zero++;
        }
        // 非‘0’元素从左指针下标+1开始
        non_zero = zero + 1;
        while (non_zero < len) {

            // 右指针找到第一个非‘0’元素
            while (non_zero < len && nums[non_zero] == 0) {
                non_zero++;
            }

            // 交换两元素
            if (non_zero < len) {
                nums[zero] ^= nums[non_zero];
                nums[non_zero] ^= nums[zero];
                nums[zero] ^= nums[non_zero];

                // 左右指针各+1（因为交换位置后，左指针将变成非‘0’元素，右指针将变成‘0’元素）
                zero++;
                non_zero++;
            }
        }
    }
}
