package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class 好数对的数目 {
    @Test
    public void 好数对的数目() {
        System.out.println("好数对的数目:" + numIdenticalPairs(new int[] {1, 2, 3}));
    }

    // 给你一个整数数组 nums 。
    //
    // 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
    //
    // 返回好数对的数目。
    //
    //
    //
    // 示例 1：
    //
    // 输入：nums = [1,2,3,1,1,3]
    // 输出：4
    // 解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始
    // 示例 2：
    //
    // 输入：nums = [1,1,1,1]
    // 输出：6
    // 解释：数组中的每组数字都是好数对
    // 示例 3：
    //
    // 输入：nums = [1,2,3]
    // 输出：0
    //
    //
    // 提示：
    //
    // 1 <= nums.length <= 100
    // 1 <= nums[i] <= 100
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/number-of-good-pairs
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int numIdenticalPairs(int[] nums) {
        // 哈希表记录数组中元素出现的次数
        // 根据规律，重复元素2个，好数对1；重复元素3个，好数对3；重复元素4个，好数对6；重复元素5个，好数对10...
        // 重复元素x个，好数对1+2+...+(x-1)个

        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        Integer integer;
        for (int i = 0, l = nums.length; i < l; i++) {
            integer = map.get(nums[i]);
            if (integer == null) {
                map.put(nums[i], 1);
            } else {
                result += integer;
                map.put(nums[i], integer + 1);
            }
        }
        return result;
    }
}
