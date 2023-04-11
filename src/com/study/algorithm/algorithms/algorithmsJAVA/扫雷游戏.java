package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

public class 扫雷游戏 {

    @Test
    public void 扫雷游戏() {
        char[][] ints = {
            {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'M'},
            {'E', 'E', 'M', 'E', 'E', 'E', 'E', 'E'},
            {'M', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'M', 'M', 'E', 'E', 'E', 'E'}
        };
        System.out.println("扫雷游戏:" + Arrays.deepToString(updateBoard(ints, new int[] {0, 0})));
    }

    // 让我们一起来玩扫雷游戏！
    //
    // 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B'
    //  代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
    //
    // 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
    //
    // 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
    // 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
    // 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
    // 如果在此次点击中，若无更多方块可被揭露，则返回面板。
    //
    //
    // 示例 1：
    //
    // 输入:
    //
    // [['E', 'E', 'E', 'E', 'E'],
    // ['E', 'E', 'M', 'E', 'E'],
    // ['E', 'E', 'E', 'E', 'E'],
    // ['E', 'E', 'E', 'E', 'E']]
    //
    // Click : [3,0]
    //
    // 输出:
    //
    // [['B', '1', 'E', '1', 'B'],
    // ['B', '1', 'M', '1', 'B'],
    // ['B', '1', '1', '1', 'B'],
    // ['B', 'B', 'B', 'B', 'B']]
    //
    // 注意：
    //
    // 输入矩阵的宽和高的范围为 [1,50]。
    // 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。
    // 输入面板不会是游戏结束的状态（即有地雷已被挖出）。
    // 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/minesweeper
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public char[][] updateBoard(char[][] board, int[] click) {

        // 思路就是根据题意，根据扫雷的规则，搜索每一个格子即可（到达终止条件）

        // 如果当前点击的就是累，直接标记并返回
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        // 深度优先搜索，按规则搜索每一个格子
        dfs(board, click[0], click[1]);

        return board;
    }

    private void dfs(char[][] board, int x, int y) {

        // 如果当前坐标没超出边界，并且当前点格子是未被翻开的格子
        if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'E') {

            // 此格子周围雷的数量
            int num =
                    isLandmine(board, x - 1, y)
                            + isLandmine(board, x - 1, y + 1)
                            + isLandmine(board, x, y + 1)
                            + isLandmine(board, x + 1, y + 1)
                            + isLandmine(board, x + 1, y)
                            + isLandmine(board, x + 1, y - 1)
                            + isLandmine(board, x, y - 1)
                            + isLandmine(board, x - 1, y - 1);

            // 如果格子周围没雷，则标记为‘B’，并且继续搜索周围的格子（注意是周围的8个方向）
            if (num == 0) {
                board[x][y] = 'B';
                dfs(board, x - 1, y);
                dfs(board, x, y + 1);
                dfs(board, x + 1, y);
                dfs(board, x, y - 1);
                dfs(board, x - 1, y + 1);
                dfs(board, x + 1, y + 1);
                dfs(board, x + 1, y - 1);
                dfs(board, x - 1, y - 1);
            }
            // 如果格子周围有雷，则标记此格子为雷的数量
            else {
                board[x][y] = (char) (num + 48);
            }
        }
    }

    // 判断当前格子是否为雷
    private int isLandmine(char[][] board, int x, int y) {
        if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
            return board[x][y] == 'M' ? 1 : 0;
        }
        return 0;
    }
}
