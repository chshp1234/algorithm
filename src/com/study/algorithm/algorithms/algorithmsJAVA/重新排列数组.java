package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

public class 重新排列数组 {

    @Test
    public void 重新排列数组() {
        int[] ints = {1, 2, 3, 4, 4, 3, 2, 1};
        System.out.println("重新排列数组:" + Arrays.toString(shuffle(ints, 4)));
    }

    // 给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
    //
    // 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。
    //
    //
    //
    // 示例 1：
    //
    // 输入：nums = [2,5,1,3,4,7], n = 3
    // 输出：[2,3,5,4,1,7]
    // 解释：由于 x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 ，所以答案为 [2,3,5,4,1,7]
    // 示例 2：
    //
    // 输入：nums = [1,2,3,4,4,3,2,1], n = 4
    // 输出：[1,4,2,3,3,2,4,1]
    // 示例 3：
    //
    // 输入：nums = [1,1,2,2], n = 2
    // 输出：[1,2,1,2]
    //
    //
    // 提示：
    //
    // 1 <= n <= 500
    // nums.length == 2n
    // 1 <= nums[i] <= 10^3
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/shuffle-the-array
    // 著作权归领扣网络所有。商业转载请联系给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
    //
    // 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。
    //
    //
    //
    // 示例 1：
    //
    // 输入：nums = [2,5,1,3,4,7], n = 3
    // 输出：[2,3,5,4,1,7]
    // 解释：由于 x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 ，所以答案为 [2,3,5,4,1,7]
    // 示例 2：
    //
    // 输入：nums = [1,2,3,4,4,3,2,1], n = 4
    // 输出：[1,4,2,3,3,2,4,1]
    // 示例 3：
    //
    // 输入：nums = [1,1,2,2], n = 2
    // 输出：[1,2,1,2]
    //
    //
    // 提示：
    //
    // 1 <= n <= 500
    // nums.length == 2n
    // 1 <= nums[i] <= 10^3
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/shuffle-the-array
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。官方授权，非商业转载请注明出处。
    public int[] shuffle(int[] nums, int n) {

        // 新建一个数组，按规则依次添加数据
        int[] result = new int[nums.length];

        int temp;
        for (int i = 0; i < n; i++) {
            temp = 2 * i;
            result[temp] = nums[i];
            result[temp + 1] = nums[i + n];
        }

        return result;
    }
}
