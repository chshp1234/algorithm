package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 一次 或 两次 。请你找出所有出现 两次 的整数，并以数组形式返回。
//
//你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间的算法解决此问题。
//
// 
//
//示例 1：
//
//输入：nums = [4,3,2,7,8,2,3,1]
//输出：[2,3]
//示例 2：
//
//输入：nums = [1,1,2]
//输出：[1]
//示例 3：
//
//输入：nums = [1]
//输出：[]
// 
//
//提示：
//
//n == nums.length
//1 <= n <= 105
//1 <= nums[i] <= n
//nums 中的每个元素出现 一次 或 两次
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-all-duplicates-in-an-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 数组中重复的数据 {
    public List<Integer> findDuplicates(int[] nums) {
        //哈希表
        //利用哈希表查找重复值

        //原地哈希
        //因为数字在1~n之间，并且最多出现2次，所以我们可以使用原地哈希的办法，将数字n放入下标n-1处
        //当发现 nums[nums[i]−1]=nums[i](nums[i]−1 != i)，则说明该数字出现了超过一次。
        //此时我们将 nums[i] 加入答案。为防止重复放入，我们将遍历过的值置为负数，下次遍历时跳过该数。
        //
        //作者：AC_OIer
        //链接：https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/solution/by-ac_oier-0m3c/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        //正负号标记
        //使用我们也可以给 nums[i] 加上「负号」表示数i+1 已经出现过一次。
        Set<Integer> set = new HashSet<>();

        List<Integer> result = new ArrayList<>();

        for (int n : nums) {
            if (!set.add(n)) {
                result.add(n);
            }
        }

        return result;
    }
}
