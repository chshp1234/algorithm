package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 组合总和 {

    @Test
    public void 组合总和() {

        System.out.println("组合总和:" + combinationSum(new int[] {2, 3, 6, 7}, 7));
    }

    // 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
    //
    // candidates 中的数字可以无限制重复被选取。
    //
    // 说明：
    //
    // 所有数字（包括 target）都是正整数。
    // 解集不能包含重复的组合。
    // 示例 1：
    //
    // 输入：candidates = [2,3,6,7], target = 7,
    // 所求解集为：
    // [
    //  [7],
    //  [2,2,3]
    // ]
    // 示例 2：
    //
    // 输入：candidates = [2,3,5], target = 8,
    // 所求解集为：
    // [
    //   [2,2,2,2],
    //   [2,3,3],
    //   [3,5]
    // ]
    //
    //
    // 提示：
    //
    // 1 <= candidates.length <= 30
    // 1 <= candidates[i] <= 200
    // candidate 中的每个元素都是独一无二的。
    // 1 <= target <= 500
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/combination-sum
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 回溯
        // 由于这里可以重复选择元素，但不能出现重复解，所以：
        // 下一次递归搜索时，从当前下标开始，不进行回头搜索，这样可以保证重复遍历同一个元素，也可以保证不搜索出重复解。
        backtrack(0, 0, 0, candidates.length, candidates, target);
        return result;
    }

    private void backtrack(int index, int sum, int count, int num, int[] candidates, int target) {

        // 从上一次搜索的下标开始搜索
        for (int i = index; i < num; i++) {
            int currentSum = candidates[i] + sum;
            if (currentSum == target) {
                temp.add(candidates[i]);
                result.add(new ArrayList<>(temp));
                temp.remove(count);
            } else if (currentSum < target) {
                temp.add(candidates[i]);
                backtrack(i, currentSum, count + 1, num, candidates, target);
                temp.remove(count);
            }
        }
    }
}
