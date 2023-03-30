package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 组合 {

    @Test
    public void 组合() {
        //        LogManager.getLogManager().addLogger(Logger.getLogger(""));

        System.out.println("组合:" + combine(4, 3));
    }

    // 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
    //
    // 示例:
    //
    // 输入: n = 4, k = 2
    // 输出:
    // [
    //  [2,4],
    //  [3,4],
    //  [2,3],
    //  [1,2],
    //  [1,3],
    //  [1,4]
    // ]
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/combinations
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {

        // 回溯算法
        backtrack(1, n, k, 0);

        return result;
    }

    private void backtrack(int index, int n, int k, int count) {

        // 剪枝，若当前下标index开始到n，可列出的所有组合的元素数量达不到k，则直接退出
        // 如index=4，n=4,k=3,此时若数组中只有一个元素1，但下标已到4，这里最多只能添加到2个元素，没有达到k，所以可以直接退出不必进行接下去的操作
        if ((count + n - index + 1) < k) {
            return;
        }

        if (count == k) {
            result.add(new ArrayList<>(temp));
            return;
        }

        while (index <= n) {
            temp.add(index);
            backtrack(index + 1, n, k, count + 1);
            temp.remove(count);
            index++;
        }
    }
}
