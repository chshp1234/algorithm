package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

// 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
//
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
//
// 必须原地修改，只允许使用额外常数空间。
//
// 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
// 1,2,3 → 1,3,2
// 3,2,1 → 1,2,3
// 1,1,5 → 1,5,1
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/next-permutation
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 下一个排列 {
    @Test
    public void 下一个排列() {
        int[] ints = new int[] {1, 3, 2};
        nextPermutation(ints);
        System.out.println("下一个排列:" + Arrays.toString(ints));
    }

    public void nextPermutation(int[] nums) {

        // 根据规律，要找到下一个更大的排序，必然从数组右端开始：
        // ·第一层循环，找到数组右端中第一个最大的排序：此序列肯定是单调递减的，如{1,2,4,3,5,4,2,1}中的最右侧子序列{5,4,2,1}。
        // ·第二层循环，交换数字：找到最大排序后，左侧数字即为要交换的数，从最大排序中找到第一个大于交换位置的数，
        // 还是上述例子数组中，数字‘3’即为要交换的数字，应该和谁进行交换,很显然，数字‘3’后下一位应该从最大排序中找，第一个大于‘3’的数字是‘4’，
        // 把‘3’和‘4’进行交换，此时数组为{}1,2,4,4,5,3,2,1}（此最大排序依旧是最大排序）.
        // ·最后对此最大排序进行从小到大排序即可。

        int len = nums.length;
        int high = len - 2;
        while (high >= 0) {

            // 找到最大排序下标，为high + 1，为high即为将要进行交换的数字的下标
            if (nums[high] < nums[high + 1]) {

                int swapIndex;

                // 从最大排序中找到要进行交换的数字下标
                for (swapIndex = high + 2; swapIndex < len; swapIndex++) {
                    // 当找到大于等于，或者遍历到数组结尾时，退出。要交换的数字下标为swapIndex-1（即为前一位）
                    if (nums[high] >= nums[swapIndex]) {
                        break;
                    }
                }

                swapIndex--;

                // 交换数字
                nums[high] ^= nums[swapIndex];
                nums[swapIndex] ^= nums[high];
                nums[high] ^= nums[swapIndex];

                break;
            }
            high--;
        }

        // 对最大排序进行从小到大重新排序
        // 当然，此子序列单调递减，可以用双指针对高低位进行数字交换
        Arrays.sort(nums, high + 1, len);
    }
}
