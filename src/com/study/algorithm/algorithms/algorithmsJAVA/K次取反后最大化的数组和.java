package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.PriorityQueue;

//给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
//
//选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
//重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
//
//以这种方式修改数组后，返回数组 可能的最大和 。
//
// 
//
//示例 1：
//
//输入：nums = [4,2,3], k = 1
//输出：5
//解释：选择下标 1 ，nums 变为 [4,-2,3] 。
//示例 2：
//
//输入：nums = [3,-1,0,2], k = 3
//输出：6
//解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
//示例 3：
//
//输入：nums = [2,-3,-1,5,-4], k = 2
//输出：13
//解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
// 
//
//提示：
//
//1 <= nums.length <= 104
//-100 <= nums[i] <= 100
//1 <= k <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class K次取反后最大化的数组和 {
    @Test
    public void K次取反后最大化的数组和() {
        int[] ints = {
                -4,-2,-3
        };
        System.out.println("K次取反后最大化的数组和:" + largestSumAfterKNegations(ints, 4));
    }

    public int largestSumAfterKNegations(int[] nums, int k) {
        //排序+贪心
        //既然要翻转，那么肯定优先翻转负数，负数翻转后变成整数，和会变大
        //负数翻转完后，只翻转最小的正整数，若翻转次数为偶数，不用翻转，翻转次数为奇数，相当于只需要翻转一次
        //注意这个最小正整数，需要最大的负数翻转后的数（负数总是翻转）和最小的正数中较小的那个

        int len = nums.length;
        int sum = 0;
        //优先队列，因为我们只需要知道最小的几位数
        PriorityQueue<Integer> queue = new PriorityQueue<>(len);
        for (int i : nums) {
            //加入队列，并提前计算总和
            sum += i;
            queue.offer(i);
        }
        int temp = -101;

        //条件就是k>0切队列不为空
        while (!queue.isEmpty() && k > 0) {
            if (queue.peek() >= 0) {
                //若当前最小的数已是正数（包括0）
                //取最大的负数翻转后的数和最小的正数中较小的那位
                //取负方便后续计算
                temp = -Math.min(-temp, queue.peek());
                break;
            }

            //当前数是负数，总是翻转
            temp = queue.poll();

            //注意相加需两倍的值
            sum += -temp - temp;
            k--;
        }

        temp = -temp;
        if (k > 0) {
            if (k % 2 != 0) {
                //若翻转次数为偶数，需减去当前最小的正数
                //注意相减需两倍的值
                sum -= temp + temp;
            }
        }

        return sum;
    }
}
