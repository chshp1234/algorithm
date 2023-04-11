package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 递增子序列 {

    @Test
    public void 递增子序列() {
        int[] ints = {2, 3, 1, 1, 2, 4};
        System.out.println("递增子序列:" + findSubsequences(ints));
    }

    // 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
    //
    // 示例:
    //
    // 输入: [4, 6, 7, 7]
    // 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
    // 说明:
    //
    // 给定数组的长度不会超过15。
    // 数组中的整数范围是 [-100,100]。
    // 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/increasing-subsequences
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    public List<List<Integer>> findSubsequences(int[] nums) {

        // 回溯，哈希表去重：因为要去重，所以在每一层迭代中，加入set，判断当前这层是否已经有相同数字加入过

        // 结果列表
        List<List<Integer>> result = new ArrayList<>();
        // 临时列表
        List<Integer> ele = new ArrayList<>(nums.length);

        // Set（哈希表）
        Set<Integer> set = new HashSet<>(nums.length);

        for (int i = 0, l = nums.length; i < l; i++) {
            if (set.add(nums[i])) {

                // 因为结果数组大小要大于1.所以此处只加入临时列表，不加入结果列表
                ele.add(nums[i]);
                dfs(result, ele, nums, i + 1);

                // 删除上一个元素（回溯）
                ele.remove(0);
            }
        }
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> ele, int[] nums, int index) {
        if (index == nums.length) {
            return;
        }

        int last = ele.size();
        Set<Integer> set = new HashSet<>(nums.length - last);

        // 第二层开始dfs搜索
        for (int i = index, l = nums.length; i < l; i++) {

            // 只有当前元素大于等于列表中上一个元素，并且当前值没有被添加过
            if (nums[i] >= ele.get(last - 1) && set.add(nums[i])) {

                // 加入临时列表
                ele.add(nums[i]);

                // 加入结果列表
                result.add(new ArrayList<>(ele));

                dfs(result, ele, nums, i + 1);

                // 删除上一个元素（回溯）
                ele.remove(last);
            }
        }
    }
}
