package com.study.algorithm.algorithms;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 全排列 {

    @Test
    public void 全排列() {
        System.out.println("数字序列全排列：" + permute(new int[]{1, 2, 3}));
    }

    // 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
    //
    // 示例:
    //
    // 输入: [1,2,3]
    // 输出:
    // [
    //  [1,2,3],
    //  [1,3,2],
    //  [2,1,3],
    //  [2,3,1],
    //  [3,1,2],
    //  [3,2,1]
    // ]
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/permutations
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {

        // 回溯搜索算法
        backtracking(new ArrayList<>(), nums);

        return result;
    }

    public void backtracking(List<Integer> list, int[] ints) {

        // 每次要加入的数字
        for (int anInt : ints) {

            // 由于数字不重复，在此判断当前数字是否已加入列表中
            // 优化，用空间换时间：
            // 1.可使用额外数组boolean[]记录当前位是否已加入列表中；
            // 2.哈希表；
            // 3.位掩码，即使用一个整数表示布尔数组。此时可以将空间复杂度降到 O(1)（不包括 path 变量和 res 变量和递归栈空间消耗）。
            if (!list.contains(anInt)) {
                list.add(anInt);
                if (list.size() == ints.length) {
                    result.add(new ArrayList<>(list));
                    list.remove(list.size() - 1);
                    return;
                }
                backtracking(list, ints);
                list.remove(list.size() - 1);
            }
        }
    }
}
