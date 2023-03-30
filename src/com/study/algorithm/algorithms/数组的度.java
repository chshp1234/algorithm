package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
//
//你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
//
// 
//
//示例 1：
//
//输入：[1, 2, 2, 3, 1]
//输出：2
//解释：
//输入数组的度是2，因为元素1和2的出现频数最大，均为2.
//连续子数组里面拥有相同度的有如下所示:
//[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
//最短连续子数组[2, 2]的长度为2，所以返回2.
//示例 2：
//
//输入：[1,2,2,3,1,4,2]
//输出：6
// 
//
//提示：
//
//nums.length 在1到 50,000 区间范围内。
//nums[i] 是一个在 0 到 49,999 范围内的整数。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/degree-of-an-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 数组的度 {
    @Test
    public void 数组的度() {
        int[] ints = {1, 2, 2, 3, 1, 4, 2};
        System.out.println("数组的度:" + findShortestSubArray(ints));
    }

    public int findShortestSubArray(int[] nums) {
        //哈希表
        //数组中频次最大的元素即为该数组的度（晦涩的题目，我们并不需要知道这个“度”），
        //题目要求其实就是要我们找到频次最大的元素的最短区间。既然要最短，这个区间首尾元素肯定就是该元素。
        //用哈希表记录数组中每个元素出现的频次以及每个元素的起始下标。
        //由于数组元素跨度较大，这里不用数组进行频次计算，用哈希表。
        //由于Java中哈希表占用内存较多，而且有拆箱装箱、hash计算，所以我们用一个哈希表进行统计——Map<Integer, int[]>的数据结构，value是个长度为2的一维数组，用于记录每个元素的长度和起始位置。
        //一趟遍历，更新value值，判断比较，更新结果。
        //更新value值（频次）后，这里有两个地方需要更新结果：
        //1.此元素的频次更大；
        //2.此元素的频次和之前的最大值maxCount一样，但是此元素的区间更小（连续子数组更短）；
        //更新maxCount，更新minLen。

        //哈希表，key为数组中的元素，value是一个长度为2的数组，记录元素出现的频次和元素出现的起始下标。
        Map<Integer, int[]> counter = new HashMap<>();
        counter.put(nums[0], new int[]{1, 0});
        int len = nums.length;

        //记录的最大频次和最短区间长度
        int maxCount = 1, minLen = 0;

        int[] temp;
        for (int i = 1; i < len; i++) {
            temp = counter.get(nums[i]);
            if (temp == null) {
                //如果temp为空，说明此元素第一次出现
                temp = new int[]{0, i};
                counter.put(nums[i], temp);
            }

            //频次+1
            temp[0]++;

            //两个条件更新结果
            //1.当前元素频次比之前的大
            //2.当前元素频次和之前的一样，但是当前元素区间更短
            if (temp[0] > maxCount || (temp[0] == maxCount && minLen > i - temp[1])) {
                maxCount = temp[0];
                minLen = i - temp[1];
            }
        }

        //结果是子数组长度，记得+1
        return minLen + 1;
    }
}
