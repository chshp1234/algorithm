package com.study.algorithm.algorithms;

//数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
//
// 
//
//示例 1：
//
//输入：[1,2,5,9,5,9,5,5,5]
//输出：5
//示例 2：
//
//输入：[3,2]
//输出：-1
//示例 3：
//
//输入：[2,2,1,1,1,2,2]
//输出：2
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-majority-element-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 主要元素 {
    public int majorityElement(int[] nums) {
        //摩尔投票法（Boyer-Moore 投票算法）
        //首先，我们需要找出数组中的众数（出现次数最多的数）
        //然后判断这个元素，数量是否大于数组的一半

        //对于第一步查找众数，我们使用摩尔投票算法，该算法使得两个不同元素相互抵消，相同元素累加（按个数数量），来查找数组中的众数
        //遍历数组过程中，维持当前查找的众数元素last和当前元素出现的次数count
        //若遍历的元素和当前元素相同，则count+1，否则count-1（两两抵消）
        //当count为0时，令last为当前遍历的元素，并使得count=1（重新查找新的众数）
        int len = nums.length;
        int last = nums[0];
        int count = 1;

        for (int i = 1; i < len; i++) {
            if (last == nums[i]) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    last = nums[i];
                    count = 1;
                }
            }
        }

        count = 0;
        for (int n : nums) {
            if (n == last) {
                count++;
            }
        }

        return count > len / 2 ? last : -1;
    }
}
