package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class 前K个高频元素 {

    @Test
    public void 前K个高频元素() {
        int[] graph = {1, 2, 2, 3, 5, 5, 5, 5, 5, 4, 1, 3, 3};
        System.out.println("前K个高频元素:" + Arrays.toString(topKFrequent(graph, 2)));
    }

    // 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
    //
    //
    //
    // 示例 1:
    //
    // 输入: nums = [1,1,1,2,2,3], k = 2
    // 输出: [1,2]
    // 示例 2:
    //
    // 输入: nums = [1], k = 1
    // 输出: [1]
    //
    //
    // 提示：
    //
    // 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
    // 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
    // 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int[] topKFrequent(int[] nums, int k) {

        // 哈希表+优先队列（大顶堆）
        // 用哈希表可以很方便的记录重复元素，其中key为元素值，value为元素出现的次数。
        // 再用优先队列进行排序，对哈希表中Entry.value进行排序
        // 取出优先队列中前k个元素，即可

        // 哈希表记录元素出现的次数
        Map<Integer, Integer> map = new HashMap<>();

        // 按元素出现的次数对元素进行排序
        PriorityQueue<Map.Entry<Integer, Integer>> heap =
                new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());

        int[] result = new int[k];

        // 统计元素出现的次数
        for (int i = 0, l = nums.length; i < l; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // 加入优先队列
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            heap.offer(entry);
        }

        // 取出值
        for (int i = 0; i < k; i++) {
            Map.Entry<Integer, Integer> curr = heap.poll();
            result[i] = curr.getKey();
        }

        return result;
    }
}
