package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

//给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
//
//返回该 最大总和 。
//
// 
//
//示例 1：
//
//输入：nums = [1,4,3,2]
//输出：4
//解释：所有可能的分法（忽略元素顺序）为：
//1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
//2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
//3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
//所以最大总和为 4
//示例 2：
//
//输入：nums = [6,2,6,5,1,2]
//输出：9
//解释：最优的分法为 (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9
// 
//
//提示：
//
//1 <= n <= 104
//nums.length == 2 * n
//-104 <= nums[i] <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/array-partition-i
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 数组拆分I {
    @Test
    public void 数组形式的整数加法() {
        int[] ints = {2, 1, 5, 5};
        System.out.println("数组形式的整数加法:" + arrayPairSum(ints));
    }

    public int arrayPairSum(int[] nums) {
        //排序
        //从小打到排序后，两两分组即可。
        //首先最小的数a，不管跟哪个数b成对，都有a<b，所以a肯定加入结果中。
        //去除最小的a（必定加入结果）后，要使每对中最小的值相加值最大，此时数组中第二小的b就不能加入结果，所以最小的a和第二小的b将成为一对。
        //同理，去除最小的a和第二小的b后，后面的取值依旧按照上面的规律，取出第三小和第四小成对，此时第三小的值加入结果...
        //所以对数组进行从小到大排序，每次跳一个位置，依次选“每对”中小的一位加入结果即可。
        int len = nums.length;

        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < len; i = i + 2) {
            result += nums[i];
        }
        /*PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < len; i++) {
            queue.offer(nums[i]);
        }
        int result = 0;
        while (!queue.isEmpty()){
            result += queue.poll();
            queue.poll();
        }*/
        return result;
    }
}
