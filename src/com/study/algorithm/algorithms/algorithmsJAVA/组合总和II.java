package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 组合总和II {

    @Test
    public void 组合总和() {

        System.out.println("组合总和:" + combinationSum2(new int[] {2, 5, 2, 1, 2}, 5));
    }

    // 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
    //
    // candidates 中的每个数字在每个组合中只能使用一次。
    //
    // 说明：
    //
    // 所有数字（包括目标数）都是正整数。
    // 解集不能包含重复的组合。
    // 示例 1:
    //
    // 输入: candidates = [10,1,2,7,6,1,5], target = 8,
    // 所求解集为:
    // [
    //  [1, 7],
    //  [1, 2, 5],
    //  [2, 6],
    //  [1, 1, 6]
    // ]
    // 示例 2:
    //
    // 输入: candidates = [2,5,2,1,2], target = 5,
    // 所求解集为:
    // [
    //   [1,2,2],
    //   [5]
    // ]
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/combination-sum-ii
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 回溯
        // 由于这里不能重复选择元素（和“组合总和”不同），但不能出现重复解，所以：
        // 下一次递归搜索时，从‘当前下标+1’处开始，这样可保证同一个元素只遍历一次；
        // 同时为了保证没有重复解，首先对原数组进行排序，然后每次递归中的循环遍历中，需与上一个元素作对比，若相同则跳过；
        // 由于数组已排序，所以在循环遍历中，若相加之和大于目标数，则可直接返回退出循环。
        Arrays.sort(candidates);
        backtrack(0, 0, 0, candidates.length, candidates, target);
        return result;
    }

    private void backtrack(int index, int sum, int count, int num, int[] candidates, int target) {

        // 从上一次搜索的下标开始搜索
        for (int i = index; i < num; i++) {
            // 防止重复，与上一个元素作对比
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            int currentSum = candidates[i] + sum;
            if (currentSum == target) {
                temp.add(candidates[i]);
                result.add(new ArrayList<>(temp));
                temp.remove(count);
                return;
            } else if (currentSum < target) {
                temp.add(candidates[i]);
                backtrack(i + 1, currentSum, count + 1, num, candidates, target);
                temp.remove(count);
            } else {
                // 若总和大于目标数，直接退出（因为后续的计算只会越来越大）
                return;
            }
        }
    }
}
