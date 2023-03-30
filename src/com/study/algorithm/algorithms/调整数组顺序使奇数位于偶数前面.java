package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

//输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
//
// 
//
//示例：
//
//输入：nums = [1,2,3,4]
//输出：[1,3,2,4]
//注：[3,1,2,4] 也是正确的答案之一。
// 
//
//提示：
//
//1 <= nums.length <= 50000
//1 <= nums[i] <= 10000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 调整数组顺序使奇数位于偶数前面 {

    @Test
    public void 调整数组顺序使奇数位于偶数前面() {
        int[] ints = {2, 3, 1, 1, 4};
        System.out.println("调整数组顺序使奇数位于偶数前面:" + Arrays.toString(exchange(ints)));
    }

    public int[] exchange(int[] nums) {
        //双指针
        //首尾双指针，start往尾部寻找偶数（因为，奇数在数组前半部分，寻找到的偶数的位置将是下一个奇数要交换的位置）
        //end指针往首部寻找奇数
        //两个指针找到各自的位置后，交换，即start处的偶数和end处的奇数交换，即可把奇数放置前面，偶数放置后面
        int end = nums.length - 1;
        int start = 0;
        while (start < end) {
            if (nums[start] % 2 != 0) {
                start++;
                continue;
            }
            if (nums[end] % 2 == 0) {
                end--;
                continue;
            }
            nums[start] ^= nums[end];
            nums[end] ^= nums[start];
            nums[start] ^= nums[end];
        }
        return nums;
    }
}
