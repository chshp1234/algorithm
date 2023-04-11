package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

//数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
//
// 
//
//你可以假设数组是非空的，并且给定的数组总是存在多数元素。
//
// 
//
//示例 1:
//
//输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
//输出: 2
// 
//
//限制：
//
//1 <= 数组长度 <= 50000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 数组中出现次数超过一半的数字 {
    @Test
    public void 数组中出现次数超过一半的数字() {
        int[] ints = {1,2,1,2,1,2,1};
        System.out.println("数组中出现次数超过一半的数字:" + majorityElement(ints));
    }

    public int majorityElement(int[] nums) {
        //排序
        //因为某个元素个数超过数组长度一半，根据抽屉原理，排序后，此元素必定会分到数组左半部分和有右半部分，所以中间位置的元素级一定是结果元素
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
