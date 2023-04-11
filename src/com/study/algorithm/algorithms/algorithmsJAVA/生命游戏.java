package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

public class 生命游戏 {

    @Test
    public void 生命游戏() {

        int[][] ints = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gameOfLife(ints);
        System.out.println("生命游戏：" + Arrays.deepToString(ints));
    }
    /**
     * 根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
     *
     * <p>给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0
     * 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
     *
     * <p>如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡； 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
     * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡； 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
     * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
     *
     * <p>
     *
     * <p>示例：
     *
     * <p>输入： [   [0,1,0],   [0,0,1],   [1,1,1],   [0,0,0] ] 输出： [   [0,0,0],   [1,0,1],   [0,1,1],
     *   [0,1,0] ]
     *
     * <p>进阶：
     *
     * <p>你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
     * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/game-of-life
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public void gameOfLife(int[][] board) {
        int[][] temps = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                temps[i][j] = board[i][j];
                liveOrDead(board, temps, i, j);
            }
        }
    }
    /**
     * 生或死
     *
     * @param board the board
     * @return the int
     */
    public void liveOrDead(int[][] board, int[][] temps, int x, int y) {
        int liveCount = 0;
        int tempX, tempY;

        tempX = x - 1;
        tempY = y - 1;
        if (tempX >= 0 && tempY >= 0) {
            if (temps[tempX][tempY] == 1) {
                liveCount++;
            }
        }

        tempX = x - 1;
        tempY = y;
        if (tempX >= 0) {
            if (temps[tempX][tempY] == 1) {
                liveCount++;
            }
        }

        tempX = x - 1;
        tempY = y + 1;
        if (tempX >= 0 && tempY < temps[0].length) {
            if (temps[tempX][tempY] == 1) {
                liveCount++;
            }
        }

        tempX = x;
        tempY = y - 1;
        if (tempY >= 0) {
            if (temps[tempX][tempY] == 1) {
                liveCount++;
            }
        }

        tempX = x;
        tempY = y + 1;
        if (tempY < temps[0].length) {
            if (board[tempX][tempY] == 1) {
                liveCount++;
            }
        }

        tempX = x + 1;
        tempY = y - 1;
        if (tempX < temps.length && tempY >= 0) {
            if (board[tempX][tempY] == 1) {
                liveCount++;
            }
        }

        tempX = x + 1;
        tempY = y;
        if (tempX < temps.length) {
            if (board[tempX][tempY] == 1) {
                liveCount++;
            }
        }

        tempX = x + 1;
        tempY = y + 1;
        if (tempX < temps.length && tempY < temps[0].length) {
            if (board[tempX][tempY] == 1) {
                liveCount++;
            }
        }

        if (board[x][y] == 1 && (liveCount < 2 || liveCount > 3)) {
            board[x][y] = 0;
        } else if (board[x][y] == 0 && liveCount == 3) {
            board[x][y] = 1;
        }
    }
}
