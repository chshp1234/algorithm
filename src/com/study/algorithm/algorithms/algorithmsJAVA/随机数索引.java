package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.*;

//给你一个可能含有 重复元素 的整数数组 nums ，请你随机输出给定的目标数字 target 的索引。你可以假设给定的数字一定存在于数组中。
//
//实现 Solution 类：
//
//Solution(int[] nums) 用数组 nums 初始化对象。
//int pick(int target) 从 nums 中选出一个满足 nums[i] == target 的随机索引 i 。如果存在多个有效的索引，则每个索引的返回概率应当相等。
// 
//
//示例：
//
//输入
//["Solution", "pick", "pick", "pick"]
//[[[1, 2, 3, 3, 3]], [3], [1], [3]]
//输出
//[null, 4, 0, 2]
//
//解释
//Solution solution = new Solution([1, 2, 3, 3, 3]);
//solution.pick(3); // 随机返回索引 2, 3 或者 4 之一。每个索引的返回概率应该相等。
//solution.pick(1); // 返回 0 。因为只有 nums[0] 等于 1 。
//solution.pick(3); // 随机返回索引 2, 3 或者 4 之一。每个索引的返回概率应该相等。
// 
//
//提示：
//
//1 <= nums.length <= 2 * 104
//-231 <= nums[i] <= 231 - 1
//target 是 nums 中的一个整数
//最多调用 pick 函数 104 次
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/random-pick-index
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 随机数索引 {
    //方法一：哈希表
    //用哈希表记录每个元素及其下标
    //pick时可在O1时间复杂度下取得随机下标
    Map<Integer, List<Integer>> map = new HashMap<>();
    Random random = new Random();

    public 随机数索引(int[] nums) {
        List<Integer> list;
        for (int i = 0, l = nums.length; i < l; i++) {
            list = map.get(nums[i]);
            if (list == null) {
                list = new ArrayList<>();
                map.put(nums[i], list);
            }
            list.add(i);
        }
    }

    public int pick(int target) {
        List<Integer> list = map.get(target);
        return list.get(random.nextInt(list.size()));
    }

    //方法二；水塘抽样
    //public int pick(int target) {
    //        int ans = 0;
    //        for (int i = 0, cnt = 0; i < nums.length; ++i) {
    //            if (nums[i] == target) {
    //                ++cnt; // 第 cnt 次遇到 target
    //                if (random.nextInt(cnt) == 0) {
    //                    ans = i;
    //                }
    //            }
    //        }
    //        return ans;
    //    }
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/random-pick-index/solution/sui-ji-shu-suo-yin-by-leetcode-solution-ofsq/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
