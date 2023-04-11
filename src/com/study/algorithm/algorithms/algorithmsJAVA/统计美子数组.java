package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class 统计美子数组 {
    @Test
    public void 统计美子数组() {
        int[] ints = {2, 4, 6};

        System.out.println("统计美子数组:" + numberOfSubarrays(ints, 3));
    }
    /**
     * 给你一个整数数组 nums 和一个整数 k。
     *
     * <p>如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
     *
     * <p>请返回这个数组中「优美子数组」的数目。
     *
     * <p>
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,1,2,1,1], k = 3 输出：2 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。 示例 2：
     *
     * <p>输入：nums = [2,4,6], k = 1 输出：0 解释：数列中不包含任何奇数，所以不存在优美子数组。 示例 3：
     *
     * <p>输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2 输出：16
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 50000 1 <= nums[i] <= 10^5 1 <= k <= nums.length
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/count-number-of-nice-subarrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int numberOfSubarrays(int[] nums, int k) {
        int num = 0;
        //        int[] ints = new int[k + 2];//可使用数组操作
        Deque<Integer> deque = new LinkedList<>();
        deque.offer(-1);
        for (int i = 0, l = nums.length; i < l; i++) {
            if ((nums[i] & 1) == 1) {
                if (deque.size() == k + 1) {
                    num += -(deque.poll() - deque.peek()) * (i - deque.peekLast());
                }
                deque.offer(i);
            }
        }

        if (deque.size() == k + 1) {
            num += -(deque.poll() - deque.peek()) * (nums.length - deque.peekLast());
        }
        return num;
    }
}
