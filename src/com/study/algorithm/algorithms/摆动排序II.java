package com.study.algorithm.algorithms;

import java.util.Arrays;

//给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
//
//你可以假设所有输入数组都可以得到满足题目要求的结果。
//
// 
//
//示例 1：
//
//输入：nums = [1,5,1,1,6,4]
//输出：[1,6,1,5,1,4]
//解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
//示例 2：
//
//输入：nums = [1,3,2,2,3,1]
//输出：[2,3,1,3,1,2]
// 
//
//提示：
//
//1 <= nums.length <= 5 * 104
//0 <= nums[i] <= 5000
//题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
// 
//
//进阶：你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/wiggle-sort-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 摆动排序II {
    public void wiggleSort(int[] nums) {
        //排序+双指针
        //先对数组进行排序，此时即可方便区分数并定位组中元素的大小
        //使用双指针，将元素按摆动顺序放入一个新的临时数组中
        //最后将临时数组元素放回元素组即可

        //使用双指针时，注意左指针应在数组中间位置，并向左移动
        //因为若左指针在最左端并向右移动，那么左右指针有可能会碰撞，而碰撞的两个元素有可能相同，此时就不能构成摆动顺序
        //又因为题目给定可以构成摆动排序，所以左指针从数组中间开始向左移动，肯定能使得构成的数组是摆动排序的

        Arrays.sort(nums);
        int l = nums.length % 2 == 0 ? nums.length / 2 - 1 : nums.length / 2;
        int r = nums.length - 1;
        int index = 0;
        int[] temp = new int[nums.length];

        while (index < nums.length) {
            temp[index++] = nums[l--];
            if (index < nums.length) {
                temp[index++] = nums[r--];
            }
        }
        index = 0;
        while (index < nums.length) {
            nums[index] = temp[index++];
        }
    }
}
