package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

//给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
//
//如果不存在满足条件的子数组，则返回 0 。
//
// 
//
//示例 1：
//
//输入：nums = [8,2,4,7], limit = 4
//输出：2
//解释：所有子数组如下：
//[8] 最大绝对差 |8-8| = 0 <= 4.
//[8,2] 最大绝对差 |8-2| = 6 > 4.
//[8,2,4] 最大绝对差 |8-2| = 6 > 4.
//[8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
//[2] 最大绝对差 |2-2| = 0 <= 4.
//[2,4] 最大绝对差 |2-4| = 2 <= 4.
//[2,4,7] 最大绝对差 |2-7| = 5 > 4.
//[4] 最大绝对差 |4-4| = 0 <= 4.
//[4,7] 最大绝对差 |4-7| = 3 <= 4.
//[7] 最大绝对差 |7-7| = 0 <= 4.
//因此，满足题意的最长子数组的长度为 2 。
//示例 2：
//
//输入：nums = [10,1,2,4,7,2], limit = 5
//输出：4
//解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
//示例 3：
//
//输入：nums = [4,2,2,2,4,4,2,2], limit = 0
//输出：3
// 
//
//提示：
//
//1 <= nums.length <= 10^5
//1 <= nums[i] <= 10^9
//0 <= limit <= 10^9
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 绝对差不超过限制的最长连续子数组 {
    @Test
    public void 数组的度() {
        int[] ints = {10, 2, 4, 2, 4, 7, 2};
        System.out.println("数组的度:" + longestSubarrayByTwoDeque(ints, 4));
    }

    public int longestSubarray(int[] nums, int limit) {
        //方法一：使用自带的排序数据结构TreeMap,key为num元素，value为元素在此区间出现的频次
        //每次左右指针右移，保证指针区间内的子数组符合题目要求

        //方法二：双优先队列，扩增窗口
        //使用两个优先队列，记录并获取子区间的最大值和最小值
        //右指针右移的过程中，判断加入的新元素后，子区间是否符合要求
        //若不符合要求，队列中（两个）移除左指针元素，左指针右移
        //注意这里我们左指针只右移一次，此区间代表符合题目要求的最大长度，也许当前窗口中元素不符合要求，但之前必然有符合要求的此长度的区间。

        int len = nums.length;
        LimitQueue limitQueue = new LimitQueue(len, limit);
        int left = 0, right = 0;
        while (right < len) {
            if (!limitQueue.add(nums[right])) {
                //若加入一个元素后，子区间不符合条件，删除左指针处的元素，并且做指针右移
                limitQueue.removeDelay(nums[left]);
                left++;
            }
            right++;
        }

        return right - left;
    }

    static class LimitQueue {
        PriorityQueue<Integer> low;
        PriorityQueue<Integer> high;
        Map<Integer, Integer>  delayRemove;
        int                    limit;

        public LimitQueue(int len, int limit) {

            low = new PriorityQueue<>(len);
            high = new PriorityQueue<>(len, (integer, t1) -> t1 - integer);
            delayRemove = new HashMap<>(len);
            this.limit = limit;
        }

        //加入一个元素后，当前区间是否符合条件
        public boolean add(int num) {
            low.offer(num);
            high.offer(num);
            return high.peek() - low.peek() <= limit;
        }

        //删除（延迟）
        public void removeDelay(int num) {
            delayRemove.put(num, delayRemove.getOrDefault(num, 0) + 1);
            //只有当前元素为两个队列顶部元素时，才真正执行删除
            //因为若不是两个队列中的顶部元素，说明此元素不是此区间的最大值或者最小值，所以没必要删除（延后删除）
            if (num == high.peek()) {
                removeImmediately(high);
            } else if (num == low.peek()) {
                removeImmediately(low);
            }
        }

        public void removeImmediately(PriorityQueue<Integer> queue) {
            Integer removeCount;
            while ((removeCount = delayRemove.get(queue.peek())) != null) {
                removeCount--;
                if (removeCount != 0) {
                    delayRemove.put(queue.peek(), removeCount);
                } else {
                    delayRemove.remove(queue.peek());
                }
                queue.poll();
            }
        }
    }

    //在方法一中，我们仅需要统计当前窗口内的最大值与最小值，因此我们也可以分别使用两个单调队列解决本题。
    //
    //在实际代码中，我们使用一个单调递增的队列 queMin 维护最小值，一个单调递减的队列 queMax 维护最大值。
    //这样我们只需要计算两个队列的队首的差值，即可知道当前窗口是否满足条件。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/solution/jue-dui-chai-bu-chao-guo-xian-zhi-de-zui-5bki/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int longestSubarrayByTwoDeque(int[] nums, int limit) {
        Deque<Integer> queMax = new LinkedList<Integer>();
        Deque<Integer> queMin = new LinkedList<Integer>();
        int n = nums.length;
        int left = 0, right = 0;
        int ret = 0;
        while (right < n) {
            //维护单调递减队列，队首元素依次为滑动窗口中最大元素依次出现的顺序
            while (!queMax.isEmpty() && queMax.peekLast() < nums[right]) {
                queMax.pollLast();
            }
            //维护单调递增队列，队首元素依次为滑动窗口中最小元素依次出现的顺序
            while (!queMin.isEmpty() && queMin.peekLast() > nums[right]) {
                queMin.pollLast();
            }
            queMax.offerLast(nums[right]);
            queMin.offerLast(nums[right]);

            //新的元素加入后，判断此时滑动窗口中的最大差值是否还满足条件
            while (!queMax.isEmpty() && !queMin.isEmpty() && queMax.peekFirst() - queMin.peekFirst() > limit) {
                //若不满足，则一定是新加入的元素替换了原来的最大值或者最小值

                //直到按顺序移除最大或最小值后，满足条件才退出循环
                if (nums[left] == queMin.peekFirst()) {
                    queMin.pollFirst();
                }
                if (nums[left] == queMax.peekFirst()) {
                    queMax.pollFirst();
                }
                left++;
            }
            //更新最长子串长度
            ret = Math.max(ret, right - left + 1);
            right++;
        }
        return ret;
    }
}
