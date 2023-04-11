package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//给定两个以 升序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
//
//定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
//
//请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
//
// 
//
//示例 1:
//
//输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
//输出: [1,2],[1,4],[1,6]
//解释: 返回序列中的前 3 对数：
//     [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
//示例 2:
//
//输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
//输出: [1,1],[1,1]
//解释: 返回序列中的前 2 对数：
//     [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
//示例 3:
//
//输入: nums1 = [1,2], nums2 = [3], k = 3
//输出: [1,3],[2,3]
//解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
// 
//
//提示:
//
//1 <= nums1.length, nums2.length <= 105
//-109 <= nums1[i], nums2[i] <= 109
//nums1 和 nums2 均为升序排列
//1 <= k <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 查找和最小的K对数字 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //优先队列
        //使用小顶堆维护遍历的每一个组合
        int len1 = nums1.length;
        int len2 = nums2.length;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp;
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.get(0) + o.get(1)));
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                temp = new ArrayList<>();
                temp.add(nums1[i]);
                temp.add(nums2[j]);
                queue.offer(temp);
            }
        }

        while (k > 0 && !queue.isEmpty()) {
            result.add(queue.poll());
            k--;
        }

        return result;
    }
}
