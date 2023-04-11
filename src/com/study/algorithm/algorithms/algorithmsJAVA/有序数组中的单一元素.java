package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
//
//请你找出并返回只出现一次的那个数。
//
//你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
//
// 
//
//示例 1:
//
//输入: nums = [1,1,2,3,3,4,4,8,8]
//输出: 2
//示例 2:
//
//输入: nums =  [3,3,7,7,10,11,11]
//输出: 10
// 
//
//提示:
//
//1 <= nums.length <= 105
//0 <= nums[i] <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/single-element-in-a-sorted-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 有序数组中的单一元素 {
    @Test
    public void 有序数组中的单一元素() {
        int[] matrix = {1, 1, 2, 3, 3, 4, 4, 5, 5};

        System.out.println("有序数组中的单一元素：" + singleNonDuplicate(matrix));
    }

    public int singleNonDuplicate(int[] nums) {
        //抽屉原理
        //二分
        //中间值下标是偶数，如果下标值等于左边值，则说明单一元素在左半边，否则在右半边；
        //中间值下标是奇数，如果下标值等于左边值，则说明单一元素在右半边，否则在左半边。
        int r = nums.length - 1;
        int l = 0;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (mid % 2 == 0) {
                if (nums[mid] == nums[mid - 1]) {
                    r = mid;
                } else {
                    l = mid;
                }
            } else {
                if (nums[mid] == nums[mid - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return nums[l];
    }
}
