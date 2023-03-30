package com.study.algorithm.algorithms;

public class 有效的数独 {
    public boolean isValidSudoku(char[][] board) {
        //哈希表
        //一趟遍历，用哈希表记录每行，每列，每个方块中已存在的数字，若遍历过程中已存在数字，则返回fasle
        //按行遍历，我们只需要一个数字保存每行的数字；一个3*9的二维数组，保存每行大方块的数字；一个9*9的二维数组保存每列的数字。
        int[] rows;
        int[][] cols = new int[9][9];
        int[][] boxs;

        for (int i = 0; i < 3; i++) {
            int rowF = i * 3;
            boxs = new int[3][9];
            for (int j = 0; j < 3; j++) {
                int rowS = rowF + j;
                rows = new int[9];
                for (int k = 0; k < 9; k++) {
                    if (board[rowS][k] != '.') {
                        int num = board[rowS][k] - '1';
                        if (rows[num] != 0) {
                            return false;
                        }
                        rows[num]++;
                        if (boxs[k / 3][num] != 0) {
                            return false;
                        }
                        boxs[k / 3][num]++;
                        if (cols[k][num] != 0) {
                            return false;
                        }
                        cols[k][num]++;
                    }
                }
            }
        }

        return true;

    }
}
