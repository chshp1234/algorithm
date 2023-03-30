package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

public class 组合总和Ⅳ {

    @Test
    public void 组合总和Ⅳ() {

        System.out.println("组合总和Ⅳ:" + combinationSum4ByDp(new int[] {2, 1, 3}, 35));
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

    private int result = 0;

    public int combinationSum4(int[] candidates, int target) {
        // 回溯(这里会超时)
        backtrack(0, 0, candidates.length, candidates, target);
        return result;
    }

    private void backtrack(int sum, int count, int num, int[] candidates, int target) {

        // 由于可以重复解（排序不同），所以每次从0下标开始搜索
        for (int i = 0; i < num; i++) {
            int currentSum = candidates[i] + sum;

            // 搜索到等于目标值时
            if (currentSum == target) {
                result++;
            } else if (currentSum < target) {
                backtrack(currentSum, count + 1, num, candidates, target);
            }
        }
    }

    private int combinationSum4ByDp(int[] candidates, int target) {
        // 动态规划 dp(x)代表，加到数字x（x<=target）时，共有多少种选择
        // f(x) += f(x-candidates[0...i])
        // 其中(x-candidates[i]>=0)，所以可以对candidates数组提前进行排序，当x-candidates[i]<0时，数组后续可不再查找，跳出循环

        int[] dp = new int[target + 1];
        // 等于0，说明加上数组当前元素时正好等于当前数
        dp[0] = 1;

        // 排序，方便后续剪枝
        Arrays.sort(candidates);
        int count;
        int len = candidates.length;

        int diff;
        for (int i = 1; i <= target; i++) {
            count = 0;
            for (int j = 0; j < len; j++) {
                diff = i - candidates[j];
                if (diff >= 0) {
                    count += dp[diff];
                } else {
                    // 当差值小于0时，不对后续的元素进行判断
                    break;
                }
            }
            dp[i] = count;
        }
        return dp[target];
    }
}
