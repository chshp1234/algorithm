package com.study.algorithm.algorithms;

import org.junit.Test;

//给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
//
//我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
//
// 
//
//示例 1:
//
//输入: nums = [4,2,3]
//输出: true
//解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
//示例 2:
//
//输入: nums = [4,2,1]
//输出: false
//解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
// 
//
//说明：
//
//1 <= n <= 10 ^ 4
//- 10 ^ 5 <= nums[i] <= 10 ^ 5
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/non-decreasing-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 非递减数列 {
    @Test
    public void 非递减数列() {
        int[] ints = {3, 4, 2, 3};
        System.out.println("非递减数列:" + checkPossibility(ints));
    }

    public boolean checkPossibility(int[] nums) {
        //对于非递减数量，数组中所有数都小于等于数组下一位数
        //在这题中，如修改一值后可保持非递减，要考虑两种特殊数组：[2,5,3,4],[2,5,1,6]。
        //第一种就是该数组中有一个数特别大，而其他数都是非递减的，我们只需修改这个特别大的数到符合条件的数即可；
        //第二种就是该数组中有一个数特别小，而其他数都是非递减的，我们只需修改这个特别小的数到符合条件的数即可。
        //
        //其实不管这个数大还是小，我们只要保证其余所有数都是非递减的，那么这个特殊的数不管大还是小，都可以经过修改后成为符合条件的数。
        //而如果这个特殊的数有多个（大于1），那么我们只能返回false。
        int len = nums.length - 1;

        boolean isChange = false;

        for (int i = 0; i < len; i++) {
            if (nums[i] > nums[i + 1]) {
                if (isChange) {
                    //找到多个特殊数
                    return false;
                }
                //找到这个特殊的数
                if (i >= len - 1 || nums[i] <= nums[i + 2] || i == 0 || nums[i - 1] <= nums[i + 1]) {
                    //此时找到第一个特殊的数
                    isChange = true;
                } else {
                    //说明这两个数都是特殊的数（i和i+1）。
                    //比如[2,5,1,6]，其中5和1就是两个特殊的数
                    return false;
                }
            }
        }
        return true;
    }
}
