package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
//
//连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
//
// 
//
//示例 1：
//
//输入：nums = [1,3,5,4,7]
//输出：3
//解释：最长连续递增序列是 [1,3,5], 长度为3。
//尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
//示例 2：
//
//输入：nums = [2,2,2,2,2]
//输出：1
//解释：最长连续递增序列是 [2], 长度为1。
// 
//
//提示：
//
//0 <= nums.length <= 104
//-109 <= nums[i] <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最长连续递增序列 {

    @Test
    public void 最长连续递增序列() {

        System.out.println("最长连续递增序列：" + findLengthOfLCIS(new int[]{1, 2, 3, 4, 5, 100}));
        //        System.out.println("最长连续递增序列：" + Integer.toBinaryString(Float.floatToIntBits(0.15625f)));
        System.out.println("最长连续递增序列：" + (0.1 + 0.2));
        //        System.out.println("有效括号的嵌套深度：" + calculateDepth(seq, seq.length()));
    }

    public int findLengthOfLCIS(int[] nums) {
        //双指针滑动窗口
        //记录连续子数组的起始下标start，遍历维护结束下标end
        //若nums[end]<=nums[end-1]，则说明子数组到此结束递增，子数组长度为end-start，并更新start=end（重新开始计算新的子数组）
        //重复，知道遍历到数组尾部，需注意，最后遍历完还得判断更新一次子数组长度
        int len = nums.length;
        if (len < 2) {
            return len;
        }

        int start = 0;
        int result = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] <= nums[i - 1]) {
                result = Math.max(result, i - start);
                start = i;
            }
        }
        result = Math.max(result, len - start);
        return result;
    }
}
