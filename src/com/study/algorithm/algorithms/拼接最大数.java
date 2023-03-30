package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

// 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n)
//  个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
//
// 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
//
// 说明: 请尽可能地优化你算法的时间和空间复杂度。
//
// 示例 1:
//
// 输入:
// nums1 = [3, 4, 6, 5]
// nums2 = [9, 1, 2, 5, 8, 3]
// k = 5
// 输出:
// [9, 8, 6, 5, 3]
// 示例 2:
//
// 输入:
// nums1 = [6, 7]
// nums2 = [6, 0, 4]
// k = 5
// 输出:
// [6, 7, 6, 0, 4]
// 示例 3:
//
// 输入:
// nums1 = [3, 9]
// nums2 = [8, 9]
// k = 3
// 输出:
// [9, 8, 9]
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/create-maximum-number
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
@Deprecated
public class 拼接最大数 {

    @Test
    public void 拼接最大数() throws InterruptedException {
        int[] ints = {6, 7};
        int[] ints2 = {6, 0, 4};
        System.out.println("拼接最大数:" + Arrays.toString(maxNumber(ints, ints2, 5)));
    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) throws InterruptedException {
        int[] result = new int[k];

        int len1 = nums1.length;
        int len2 = nums2.length;

        /*int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < k; i++) {
            int max1 = -1;
            int max2 = -1;

            int temp1 = 0;
            int temp2 = 0;
            for (int j = index1, jl = len1 - (k - i - (len2 - index2)); j <= jl && j < len1; j++) {
                if (nums1[j] > max1) {
                    max1 = nums1[j];
                    temp1 = j;
                }
            }
            for (int j = index2, jl = len2 - (k - i - (len1 - index1)); j <= jl && j < len2; j++) {
                if (nums2[j] > max2) {
                    max2 = nums2[j];
                    temp2 = j;
                }
            }
            if (max1 > max2) {
                index1 = temp1 + 1;
                result[i] = max1;
            } else {
                index2 = temp2 + 1;
                result[i] = max2;
            }
        }*/

        CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(
                        () -> {
                            countDownLatch.countDown();
                        })
                .start();

        countDownLatch.await();

        return result;
    }
}
