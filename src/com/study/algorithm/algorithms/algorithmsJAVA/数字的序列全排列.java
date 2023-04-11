package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class 数字的序列全排列 {
    @Test
    public void 全排列() {
        int[] ints = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("全排列:" + permute(ints));
    }

    /**
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     *
     * <p>示例:
     *
     * <p>输入: [1,2,3] 输出: [ [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1] ]
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/permutations
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        /*todo 未完成*/
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list;

        for (int i = 0, l = nums.length; i < l; i++) {
            list = new ArrayList<>();
            permute(list, nums, i);
            result.add(list);
        }

        return result;
    }

    private void permute(List<Integer> result, int[] nums, int index) {}
}
