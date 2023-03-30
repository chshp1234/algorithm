package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 解数独 {

    @Test
    public void 解数独() {
        char[][] chars =
                new char[][] {
                    {'5', '3', ' ', ' ', '7', ' ', ' ', ' ', ' '},
                    {'6', ' ', ' ', '1', '9', '5', ' ', ' ', ' '},
                    {' ', '9', '8', ' ', ' ', ' ', ' ', '6', ' '},
                    {'8', ' ', ' ', ' ', '6', ' ', ' ', ' ', '3'},
                    {'4', ' ', ' ', '8', ' ', '3', ' ', ' ', '1'},
                    {'7', ' ', ' ', ' ', '2', ' ', ' ', ' ', '6'},
                    {' ', '6', ' ', ' ', ' ', ' ', '2', '8', ' '},
                    {' ', ' ', ' ', '4', '1', '9', ' ', ' ', '5'},
                    {' ', ' ', ' ', ' ', '8', ' ', ' ', '7', '9'}
                };

        solveSudoku(chars);

        System.out.println("解数独:" + Arrays.deepToString(chars));
    }

    public void solveSudoku(char[][] board) {

        // 哈希表，记录每一行、每一列以及每一个九宫格中的数字（不如二维数组有效，但相比应该更容易理解吧）
        List<Set<Character>> rowSet = new ArrayList<>(9);
        List<Set<Character>> colSet = new ArrayList<>(9);
        List<Set<Character>> boxSet = new ArrayList<>(9);

        // 代表已经确认的格子（确认当前位置的数字有效），将不进行操作
        boolean[][] visit = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            rowSet.add(new HashSet<>());
            colSet.add(new HashSet<>());
            boxSet.add(new HashSet<>());
        }

        // 填入每一行、列、九宫格初始记录的数字
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int boxIndex = (i / 3) * 3 + j / 3;
                if (board[i][j] != ' ') {
                    visit[i][j] = true;
                    rowSet.get(i).add(board[i][j]);
                    colSet.get(j).add(board[i][j]);
                    boxSet.get(boxIndex).add(board[i][j]);
                }
            }
        }

        // 搜索优化
        // 当一个空格位可以确认一个数字时，优先进行填充
        // 比如下面的数独中，第7行9列
        // '5', '3', ' ', ' ', '7', ' ', ' ', ' ', ' '
        // '6', ' ', ' ', '1', '9', '5', ' ', ' ', ' '
        // ' ', '9', '8', ' ', ' ', ' ', ' ', '6', ' '
        // '8', ' ', ' ', ' ', '6', ' ', ' ', ' ', '3'
        // '4', ' ', ' ', '8', ' ', '3', ' ', ' ', '1'
        // '7', ' ', ' ', ' ', '2', ' ', ' ', ' ', '6'
        // ' ', '6', ' ', ' ', ' ', ' ', '2', '8', ' '
        // ' ', ' ', ' ', '4', '1', '9', ' ', ' ', '5'
        // ' ', ' ', ' ', ' ', '8', ' ', ' ', '7', '9'
        // 可以看出，在第7行9列以及当前九宫格中，这个空格位只能填入数字'4'，所以可以优先进行填充，后续回溯搜索时就不必在此位进行操作
        // 直到无法进一步优化为止，再进行回溯搜索对数独进行填充

        boolean firstSearch = true;
        while (firstSearch) {

            // 是否有修改
            firstSearch = false;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {

                    // 根据当前行和列找出当前九宫格所在的位置
                    int boxIndex = (i / 3) * 3 + j / 3;

                    if (!visit[i][j]) {

                        // 可填充的数字个数，当可填充的数字只有一个时，才进行填充
                        int num = 0;
                        // 要填充的数字
                        char solo = ' ';
                        for (char count = '1'; count <= '9'; count++) {
                            if (!rowSet.get(i).contains(count)
                                    && !colSet.get(j).contains(count)
                                    && !boxSet.get(boxIndex).contains(count)) {
                                solo = count;
                                num++;
                            }
                        }
                        if (num == 1) {
                            // 当且仅当有一个数字可填充时
                            firstSearch = true;
                            board[i][j] = solo;
                            rowSet.get(i).add(solo);
                            colSet.get(j).add(solo);
                            boxSet.get(boxIndex).add(solo);
                            visit[i][j] = true;
                        }
                    }
                }
            }
        }

        backtrack(rowSet, colSet, boxSet, board, visit, 0);
    }

    // 回溯搜索
    public boolean backtrack(
            List<Set<Character>> rowSet,
            List<Set<Character>> colSet,
            List<Set<Character>> boxSet,
            char[][] board,
            boolean[][] visit,
            int index) {

        // index表示从左上角到右下角的线性位置
        if (index == 81) {
            return true;
        }

        // 根据位置计算当前所在‘行’
        int i = index / 9;

        // 根据位置计算当前所在‘列’
        int j = index % 9;

        // 当前空格是否已有有效数字
        if (!visit[i][j]) {

            // 根据当前行和列找出当前九宫格所在的位置
            int boxIndex = (i / 3) * 3 + j / 3;
            for (char count = '1'; count <= '9'; count++) {
                if (!rowSet.get(i).contains(count)
                        && !colSet.get(j).contains(count)
                        && !boxSet.get(boxIndex).contains(count)) {

                    // 只有当行、列、当前九宫格都没有这个数字时，进行填充
                    board[i][j] = count;
                    // 对应的当前行、列、九宫格记录当前数字
                    rowSet.get(i).add(count);
                    colSet.get(j).add(count);
                    boxSet.get(boxIndex).add(count);

                    // 如果返回false，表示后面某一空格无法正常填入数字，则进行回溯
                    if (backtrack(rowSet, colSet, boxSet, board, visit, index + 1)) {
                        return true;
                    }

                    // 回溯，取消当前行、列、九宫格的记录
                    rowSet.get(i).remove(count);
                    colSet.get(j).remove(count);
                    boxSet.get(boxIndex).remove(count);
                }
            }
            // 当前空格无法填充数字，表示当前位置是个空格，且9个数字都在当前的行、列、九宫格中有记录，则返回false，进行回溯
            return false;
        }

        return backtrack(rowSet, colSet, boxSet, board, visit, index + 1);
    }
}
