package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class N皇后 {

    @Test
    public void N皇后() {
        System.out.println("N皇后:" + solveNQueens(5));
    }

    // n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
    //
    //
    //
    // 上图为 8 皇后问题的一种解法。
    //
    // 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
    //
    // 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
    //
    //
    //
    // 示例：
    //
    // 输入：4
    // 输出：[
    // [".Q..",  // 解法 1
    //  "...Q",
    //  "Q...",
    //  "..Q."],
    //
    // ["..Q.",  // 解法 2
    //  "Q...",
    //  "...Q",
    //  ".Q.."]
    // ]
    // 解释: 4 皇后问题存在两个不同的解法。
    //
    //
    // 提示：
    //
    // 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/n-queens
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<List<String>> solveNQueens(int n) {

        // 经典回溯：
        // 首先一行行进行放置，防止每一行中有两个皇后
        // 下一行放置时，判断当前位置是否在纵行、斜线上已有皇后，没有则放置，并继续进行下一行皇后的放置
        // 当放置到尾时，所有皇后都已放置，把当前结果加入列表/当前所有位置都不可行。回溯
        // 查找上一行皇后下一个可放置的正确位置，若当前位置都不可行，回溯，重复此判断
        // 直到遍历完所有位置

        List<List<String>> result = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        StringBuilder tempString = new StringBuilder();

        for (int i = 0; i < n; i++) {
            tempString.append('.');
        }

        backtrack(result, temp, new StringBuilder(tempString), 0, n);

        return result;
    }

    private void backtrack(
            List<List<String>> result,
            List<String> temp,
            StringBuilder stringBuilder,
            int tier,
            int n) {

        // 若遍历完，则将结果添加入列表中
        if (tier == n) {
            result.add(new ArrayList<>(temp));
            return;
        }

        StringBuilder tempString = new StringBuilder(stringBuilder);
        for (int i = 0; i < n; i++) {

            if (isCorrect(temp, tier, i)) {
                // 当前位置设置皇后
                tempString.setCharAt(i, 'Q');
                temp.add(tempString.toString());
                // 继续下一行的皇后位置判断
                backtrack(result, temp, new StringBuilder(stringBuilder), tier + 1, n);
                // 回溯，重置当前位置为空
                tempString.setCharAt(i, '.');
                temp.remove(tier);
            }
        }
    }

    // 判断当前位置是否正确
    private boolean isCorrect(List<String> temp, int tier, int index) {
        for (int i = 0, l = temp.size(); i < l; i++) {
            int p = temp.get(i).indexOf('Q');
            // 判断竖线、斜线位置
            if (p == index || Math.abs(p - index) == Math.abs(i - tier)) {
                return false;
            }
        }
        return true;
    }
}
