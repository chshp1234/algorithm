package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 长度最小的子数组 {

    @Test
    public void 长度最小的子数组() {
        //        System.out.println("长度最小的子数组：" + minSubArrayLen(7, new int[] {2, 3, 1, 2, 4, 3}));
    }

    // 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
    //
    // 示例:
    //
    // 输入: s = 7, nums = [2,3,1,2,4,3]
    // 输出: 2
    // 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
    // 进阶:
    //
    // 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int minSubArrayLen(int s, int[] nums) {
        // 万年不变的前置判空条件
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 前缀和，设置数组长度为nums.length + 1，并令第一项SUM[0] = 0，可以省去很多if判断（省去边界条件判断，第m~n的数组和为前n项的和减去前m-1项的和，
        // 但当m为0时，就需要进行边界条件判断，防止越界）
        int[] SUM = new int[nums.length + 1];

        SUM[0] = 0;
        SUM[1] = nums[0];
        // 计算前缀和
        for (int i = 0, l = nums.length; i < l; i++) {
            SUM[i + 1] = SUM[i] + nums[i];
        }

        // 因为数组都为正数，前缀和是个递增的数组，所以可采用二分查找
        int low = 1, high = nums.length;

        int result = 0;

        Loop:
        for (
        /*判断长度，初始为数组一半长度*/ int length = low + ((high - low) >> 1); low <= high; ) {
            for (int index = 0, ll = nums.length - length + 1; index < ll; index++) {
                // 数组中起始位置index长度为length的子数组的和
                if (SUM[length + index] - SUM[index] >= s) {
                    // 如果和>=s，则最小子数组的长度肯定小于等于length：
                    // 1.令结果=length
                    // 2.令高位high=length-1
                    // 3.二分，令length = low + ((high - low) >> 1)，继续判断在low~high区间中位是否符合条件
                    result = length;
                    high = length - 1;
                    length = low + ((high - low) >> 1);

                    // 继续（重新）外层循环
                    continue Loop;
                }
            }

            // 如果执行到此，说明长度为length情况下，没有子数组的和>=s，则最小子数组的长度肯定大于length
            // 令低位low=length + 1
            // 继续二分，令length = low + ((high - low) >> 1)，判断在low~high区间中位是否符合条件
            low = length + 1;
            length = low + ((high - low) >> 1);
        }
        return result;
    }

    // 滑动窗口
    // 定义两个指针 start 和 end 分别表示子数组的开始位置和结束位置，维护变量 sum
    // 存储子数组中的元素和（即从 nums[start] 到 nums[end]的元素和）。
    //
    // 初始状态下，start 和 end 都指向下标 0，sum 的值为 0。
    //
    // 每一轮迭代，将 nums[end] 加到 sum，如果sum≥s，则更新子数组的最小长度（此时子数组的长度是end−start+1），
    // 然后将nums[start] 从 sum 中减去并将 start 右移，直到sum<s，在此过程中同样更新子数组的最小长度。
    // 在每一轮迭代的最后，将 end 右移。
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum/solution/chang-du-zui-xiao-de-zi-shu-zu-by-leetcode-solutio/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    /*public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }*/
}
