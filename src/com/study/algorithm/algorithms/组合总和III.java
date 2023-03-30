package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 组合总和III {
    @Test
    public void 组合总和() {

        System.out.println("组合总和:" + combinationSum3(3, 15));
    }

    // 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
    //
    // 说明：
    //
    // 所有数字都是正整数。
    // 解集不能包含重复的组合。
    // 示例 1:
    //
    // 输入: k = 3, n = 7
    // 输出: [[1,2,4]]
    // 示例 2:
    //
    // 输入: k = 3, n = 9
    // 输出: [[1,2,6], [1,3,5], [2,3,4]]
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/combination-sum-iii
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int target) {
        // 回溯，同组合12
        // 这里可以默认目标数组为{1,2,3,4,...,9}
        // 需注意搜索出的集合大小等于k即可
        backtrack(1, target, 0, k);
        return result;
    }

    private void backtrack(int index, int diff, int count, int k) {

        // 当集合中元素数量大于k，则不进行后续搜索
        if (count > k) {
            return;
        }

        // 从上一次搜索的下标开始搜索
        for (int i = index; i <= 9; i++) {
            int diffValue = diff - i;

            // 如果差值为0（说明加上这个元素后，此时查找到的组合总和等于目标值），且数组大小为k-1（说明加上这个元素后数组大小为k）
            if (diffValue == 0 && (count + 1) == k) {
                temp.add(i);
                result.add(new ArrayList<>(temp));
                temp.remove(count);
                return;
            }

            // 如果差值大于i，则递归查找后续元素（因为不能重复，所以小于i时不进行查找）
            if (diffValue > i) {
                temp.add(i);
                backtrack(i + 1, diffValue, count + 1, k);
                temp.remove(count);
            }
        }
    }
}
