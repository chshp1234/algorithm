package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.HashSet;
import java.util.Set;

//给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
//
// 
//
//示例 1：
//
//输入：nums = [1,2,3,1], k = 3
//输出：true
//示例 2：
//
//输入：nums = [1,0,1,1], k = 1
//输出：true
//示例 3：
//
//输入：nums = [1,2,3,1,2,3], k = 2
//输出：false
// 
//
// 
//
//提示：
//
//1 <= nums.length <= 105
//-109 <= nums[i] <= 109
//0 <= k <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/contains-duplicate-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 存在重复元素II {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        //哈希表+滑动窗口
        //用哈希表（Set）记录是否存在某个值
        //滑动窗口，将进入窗口的元素存进哈希表中，移出窗口的元素从哈希表中移除。这样可保证哈希表中的元素下标差值小于等于k。
        int len = nums.length;
        Set<Integer> set = new HashSet<>(len);
        for (int i = 0, l = Math.min(k, len); i < l; i++) {
            if (!set.add(nums[i])) {
                return true;
            }
        }

        for (int i = k; i < len; i++) {
            if (!set.add(nums[i])) {
                return true;
            }
            set.remove(nums[i - k]);
        }
        return false;
    }
}
