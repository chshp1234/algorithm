package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 单词搜索 {

    @Test
    public void 二进制求和() {
        System.out.println(
                "二进制求和:"
                        + exist(
                                new char[][] {
                                    {'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}
                                },
                                "ABCESF"));
    }

    // 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
    //
    // 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
    //
    //
    //
    // 示例:
    //
    // board =
    // [
    //  ['A','B','C','E'],
    //  ['S','F','C','S'],
    //  ['A','D','E','E']
    // ]
    //
    // 给定 word = "ABCCED", 返回 true
    // 给定 word = "SEE", 返回 true
    // 给定 word = "ABCB", 返回 false
    //
    //
    // 提示：
    //
    // board 和 word 中只包含大写和小写英文字母。
    // 1 <= board.length <= 200
    // 1 <= board[i].length <= 200
    // 1 <= word.length <= 10^3
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/word-search
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean exist(char[][] board, String word) {
        // 回溯算法
        // 当索搜到单词尾部时，表示可以找到，返回true。
        // 因为不能回头，用一个二维布尔值数组visit[r][c]表示当前位置board[r][c]的单词是否搜索过。
        // 若board[r][c]匹配到字符串当前匹配的下标时word.charAt(index)，则继续匹配上下左右，判断字符串下一个下标index+1，
        // 如果4个方向都不匹配，则返回fasle，表示此路已走到头。
        // 注：匹配时，需把visit数组当前位置设为true，回头时重新设置为fasle。

        int row = board.length;
        int col = board[0].length;
        int length = word.length();
        boolean[][] visit = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                if (board[i][j] == word.charAt(0)) {
                    visit[i][j] = true;
                    if (backtrack(board, i, j, visit, word, 1, length)) {
                        return true;
                    }
                    visit[i][j] = false;
                }
            }
        }
        return false;
    }

    public boolean backtrack(
            char[][] board, int row, int col, boolean[][] visit, String word, int index, int len) {

        // 出口条件，字符串已匹配完整
        if (index == len) {
            return true;
        }

        int l = col - 1;
        int t = row - 1;
        int r = col + 1;
        int b = row + 1;

        // 分别匹配左上右下4个方向
        if (l >= 0 && !visit[row][l] && board[row][l] == word.charAt(index)) {
            visit[row][l] = true;
            if (backtrack(board, row, l, visit, word, index + 1, len)) {
                return true;
            }
            visit[row][l] = false;
        }
        if (t >= 0 && !visit[t][col] && board[t][col] == word.charAt(index)) {
            visit[t][col] = true;
            if (backtrack(board, t, col, visit, word, index + 1, len)) {
                return true;
            }
            visit[t][col] = false;
        }
        if (r < board[0].length && !visit[row][r] && board[row][r] == word.charAt(index)) {
            visit[row][r] = true;
            if (backtrack(board, row, r, visit, word, index + 1, len)) {
                return true;
            }
            visit[row][r] = false;
        }
        if (b < board.length && !visit[b][col] && board[b][col] == word.charAt(index)) {
            visit[b][col] = true;
            if (backtrack(board, b, col, visit, word, index + 1, len)) {
                return true;
            }
            visit[b][col] = false;
        }

        // 4个方向都无法匹配
        return false;
    }
}
