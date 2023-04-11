package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

@Deprecated
public class 乘积最大子数组 {

    /*todo 未完成*/
    @Test
    public void 乘积最大子数组() {
        System.out.println("乘积最大子数组：" + maxProduct(new int[] {-3, 3, -1, -2, 2, 2}));
    }

    // 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
    //
    //
    //
    // 示例 1:
    //
    // 输入: [2,3,-2,4]
    // 输出: 6
    // 解释: 子数组 [2,3] 有最大乘积 6。
    // 示例 2:
    //
    // 输入: [-2,0,-1]
    // 输出: 0
    // 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/maximum-product-subarray
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        // 结果
        int result = 0;

        // 当前最大的正数
        int p = 0;
        // 当前最大的负数
        int n = 0;

        int i = 0, l = nums.length;

        for (; i < l; i++) {

            // 如果当前项大于0，显然乘积符号不会改变
            if (nums[i] > 0) {
                // 当前正数和当前负数各乘上当前项
                if (p == 0) {
                    // 若当前正数为0，说明还未给予初始值
                    p = nums[i];
                } else {
                    p = p * nums[i];
                }
                n = n * nums[i];
            }
            // 如果当前项小于0，则乘积会改变符号
            else if (nums[i] < 0) {
                int temp = p;

                // 当前正数为之前负数乘当前项
                p = n * nums[i];

                // 判断最大值，因为遇到负数后，符号改变，连续子数组因该为之前的负数子数组中开始（也就是正数乘到此后符号改变，并不能连续了）
                if (p < temp) {
                    result = Math.max(temp, result);
                    //                    p = 1;
                }

                // 当前负数就为之前正数和当前项的乘积
                if (temp == 0) {
                    // 若之前正数为0，则赋予负数初始值
                    n = nums[i];
                } else {
                    n = temp * nums[i];
                }

            } else {
                // 若当前项为0，则判断当前正数合结果大小，并重置负数合正数为0值
                result = Math.max(p, result);
                n = 0;
                p = 0;
            }
        }

        return Math.max(p, result);
    }
}
