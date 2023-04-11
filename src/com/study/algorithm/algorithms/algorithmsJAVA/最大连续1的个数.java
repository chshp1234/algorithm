package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给定一个二进制数组， 计算其中最大连续1的个数。
//
//示例 1:
//
//输入: [1,1,0,1,1,1]
//输出: 3
//解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
//注意：
//
//输入的数组只包含 0 和1。
//输入数组的长度是正整数，且不超过 10,000。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/max-consecutive-ones
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最大连续1的个数 {
    @Test
    public void 最大连续1的个数() {
        int[] ints = {1, 1, 0, 1, 1, 1};
        System.out.println("最大连续1的个数:" + findMaxConsecutiveOnes(ints));
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        // 一次遍历
        //为了得到数组中最大连续 1 的个数，需要遍历数组，并记录最大的连续 1 的个数和当前的连续 1 的个数。
        //如果当前元素是 1，则将当前的连续 1 的个数加 1，否则，使用之前的连续 1 的个数更新最大的连续 1 的个数，并将当前的连续 1 的个数清零。
        //
        //遍历数组结束之后，需要再次使用当前的连续 1 的个数更新最大的连续 1 的个数，因为数组的最后一个元素可能是 1，
        //且最长连续 1 的子数组可能出现在数组的末尾，如果遍历数组结束之后不更新最大的连续 1 的个数，则会导致结果错误。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/max-consecutive-ones/solution/zui-da-lian-xu-1de-ge-shu-by-leetcode-so-252a/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int len = nums.length;
        int sum = 0;
        int result = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                result = Math.max(result, sum);
                sum = 0;
            } else {
                sum++;
            }
        }
        result = Math.max(result, sum);
        return result;
    }
}
