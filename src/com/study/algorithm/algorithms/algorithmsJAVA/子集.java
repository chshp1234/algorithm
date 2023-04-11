package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 子集 {

    @Test
    public void 子集() {
        int[] ints = {1, 2, 3};
        System.out.println("子集:" + subsetsByBacktrack(ints));
    }

    // 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
    //
    // 说明：解集不能包含重复的子集。
    //
    // 示例:
    //
    // 输入: nums = [1,2,3]
    // 输出:
    // [
    //  [3],
    //   [1],
    //   [2],
    //   [1,2,3],
    //   [1,3],
    //   [2,3],
    //   [1,2],
    //   []
    // ]
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/subsets
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<List<Integer>> subsetsByRecursion(int[] nums) {
        // 方法一：递归
        List<List<Integer>> output = new ArrayList<>();
        output.add(new ArrayList<>());

        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList<>();
            for (List<Integer> curr : output) {
                newSubsets.add(
                        new ArrayList<Integer>(curr) {
                            {
                                add(num);
                            }
                        });
            }
            output.addAll(newSubsets);
        }
        return output;
    }

    List<List<Integer>> output = new ArrayList<>();
    int n, k;

    public List<List<Integer>> subsetsByBacktrack(int[] nums) {
        // 回溯
        n = nums.length;
        for (k = 0; k < n + 1; ++k) {
            backtrack(0, new ArrayList<>(), nums);
        }
        return output;
    }

    public void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
        // if the combination is done
        if (curr.size() == k) {
            output.add(new ArrayList<>(curr));
            return;
        }

        for (int i = first; i < n; ++i) {
            // add i into the current combination
            curr.add(nums[i]);
            // use next integers to complete the combination
            backtrack(i + 1, curr, nums);
            // backtrack
            curr.remove(curr.size() - 1);
        }
    }

    public List<List<Integer>> subsetsByBitmask(int[] nums) {
        // 字典排序（二进制排序） 子集
        List<List<Integer>> output = new ArrayList<>();
        int n = nums.length;

        for (int i = (int) Math.pow(2, n); i < (int) Math.pow(2, n + 1); ++i) {
            // generate bitmask, from 0..00 to 1..11
            String bitmask = Integer.toBinaryString(i).substring(1);

            // append subset corresponding to that bitmask
            List<Integer> curr = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                if (bitmask.charAt(j) == '1') curr.add(nums[j]);
            }
            output.add(curr);
        }
        return output;
    }
}
