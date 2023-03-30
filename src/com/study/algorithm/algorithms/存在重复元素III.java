package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

//给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
//
//如果存在则返回 true，不存在返回 false。
//
// 
//
//示例 1：
//
//输入：nums = [1,2,3,1], k = 3, t = 0
//输出：true
//示例 2：
//
//输入：nums = [1,0,1,1], k = 1, t = 2
//输出：true
//示例 3：
//
//输入：nums = [1,5,9,1,5,9], k = 2, t = 3
//输出：false
// 
//
//提示：
//
//0 <= nums.length <= 2 * 104
//-231 <= nums[i] <= 231 - 1
//0 <= k <= 104
//0 <= t <= 231 - 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/contains-duplicate-iii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 存在重复元素III {
    @Test
    public void 存在重复元素III() {
        int[] ints = {1, 2};
        System.out.println("存在重复元素III:" + containsNearbyAlmostDuplicate(ints, 0, 1));
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        if (nums.length < 2 || k == 0) {
            return false;
        }

        final boolean[] result = {false, false};

        TreeSet<Integer> treeSet = new TreeSet<>((integer, t1) -> {
            if (result[1] && Math.abs(integer.longValue() - t1) <= t) {
                result[0] = true;
            }
            return Integer.compare(integer, t1);
        });
        Map<Integer, Integer> map = new HashMap<>();
        treeSet.add(nums[0]);
        result[1] = true;
        map.put(nums[0], 1);
        int len = nums.length;
        for (int i = 1; i <= k && i < len; i++) {
            treeSet.add(nums[i]);
            if (result[0]) {
                return true;
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for (int i = k + 1; i < len; i++) {
            int     head    = i - k - 1;
            Integer integer = map.get(nums[head]);
            if (integer == 1) {
                result[1] = false;
                treeSet.remove(nums[head]);
                result[1] = true;
                map.remove(nums[head]);
            } else {
                map.put(nums[head], integer - 1);
            }

            treeSet.add(nums[i]);
            if (result[0]) {
                return true;
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        return false;
    }
}
