package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

//输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
//
// 
//
//示例 1：
//
//输入：nums = [2,7,11,15], target = 9
//输出：[2,7] 或者 [7,2]
//示例 2：
//
//输入：nums = [10,26,30,31,47,60], target = 40
//输出：[10,30] 或者 [30,10]
// 
//
//限制：
//
//1 <= nums.length <= 10^5
//1 <= nums[i] <= 10^6
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 和为s的两个数字 {
    @Test
    public void 和为s的两个数字() {

        int[] ints = new int[]{
                10, 34, 36, 41, 66, 92, 99, 118, 153, 159, 170, 175, 184, 206, 227, 240, 242, 243,
                247, 257, 260, 293, 295, 312, 315, 315, 319, 321, 323, 338, 338, 342, 344, 367, 370,
                378, 385, 410, 415, 417, 446, 448, 459, 487, 497, 506, 507, 509, 510, 513, 594, 597,
                600, 619, 622, 644, 656, 672, 674, 675, 677, 692, 700, 710, 710, 720, 774, 810, 828
        };
        int K = 720;

        System.out.printf("和为s的两个数字：" + Arrays.toString(twoSum(ints, K)), K);
    }

    public int[] twoSum(int[] nums, int target) {
        //双指针，二分查找
        //因为数组已是有序的，所以可以使用双指针+二分查找的方式进行查找
        //高低位值进行相加，若大于target，则高位指针-1，若小于target，则低位指针+1
        //开始查找前，先使用二分查找确定高低位指针的起始位置，太大的值和太小的值提前排除
        int end = Arrays.binarySearch(nums, target);
        if (end < 0) {
            end = -end - 2;
        } else {
            end--;
        }
        int start = Arrays.binarySearch(nums, target - nums[end]);
        if (start >= 0) {
            return new int[]{nums[start], nums[end]};
        } else {
            start = -start - 1;
            while (start < end) {
                if (nums[start] + nums[end] > target) {
                    end--;
                } else if (nums[start] + nums[end] < target) {
                    start++;
                } else {
                    return new int[]{nums[start], nums[end]};
                }
            }
        }
        return new int[0];
    }
}
