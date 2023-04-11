package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 全排列II {

    @Test
    public void 全排列II() {
        System.out.println("全排列II：" + permuteUnique(new int[] {1, 1, 2}));
    }

    // 给定一个可包含重复数字的序列，返回所有不重复的全排列。
    //
    // 示例:
    //
    // 输入: [1,1,2]
    // 输出:
    // [
    //  [1,1,2],
    //  [1,2,1],
    //  [2,1,1]
    // ]
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/permutations-ii
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {

        // 回溯
        // 回溯方法backtrack(index,nums),代表要往返回的结果中的第index下标中添加元素，当index==num.length时，说明元素已全部添加完毕。
        // 由于元素有重复，但全排列的结果不能有重复（相同），所以这里需要一个去重的方式
        // 1.哈希表，在每个遍历的下标时，维护一个哈希表，每次从原数组中取元素添加时，跳过重复（已添加过）的元素
        // 2.提前对原数组进行排序，这样重复的元素肯定是相连的，可以很方便的去重if (i > 0 && nums[i] == nums[i - 1]）

        int len = nums.length;

        // 已添加的元素不再遍历
        boolean[] visit = new boolean[len];
        backtrack(0, visit, nums, len);
        return result;
    }

    private void backtrack(int index, boolean[] visit, int[] nums, int len) {
        if (index == len) {
            result.add(new ArrayList<>(temp));
            return;
        }

        Set<Integer> repetition = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (!visit[i] && repetition.add(nums[i])) {
                visit[i] = true;
                temp.add(nums[i]);
                backtrack(index + 1, visit, nums, len);
                temp.remove(index);
                visit[i] = false;
            }
        }
    }
}
