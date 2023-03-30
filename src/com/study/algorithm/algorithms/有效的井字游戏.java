package com.study.algorithm.algorithms;

import org.junit.Test;

//给你一个字符串数组 board 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 board 所显示的状态时，才返回 true 。
//
//井字游戏的棋盘是一个 3 x 3 数组，由字符 ' '，'X' 和 'O' 组成。字符 ' ' 代表一个空位。
//
//以下是井字游戏的规则：
//
//玩家轮流将字符放入空位（' '）中。
//玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。
//'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。
//当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
//当所有位置非空时，也算为游戏结束。
//如果游戏结束，玩家不允许再放置字符。
// 
//
//示例 1：
//
//
//输入：board = ["O  ","   ","   "]
//输出：false
//解释：玩家 1 总是放字符 "X" 。
//示例 2：
//
//
//输入：board = ["XOX"," X ","   "]
//输出：false
//解释：玩家应该轮流放字符。
//示例 3：
//
//
//输入：board = ["XXX","   ","OOO"]
//输出：false
//Example 4:
//
//
//输入：board = ["XOX","O O","XOX"]
//输出：true
// 
//
//提示：
//
//board.length == 3
//board[i].length == 3
//board[i][j] 为 'X'、'O' 或 ' '
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/valid-tic-tac-toe-state
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 有效的井字游戏 {
    @Test
    public void 有效的井字游戏() {
        System.out.println("有效的井字游戏:" + validTicTacToe(new String[]{
                "XXO",
                "XOX",
                "OXO"}));
    }

    public boolean validTicTacToe(String[] board) {
        //模拟+分类讨论
        //讨论有效的棋盘，必须满足：
        //1.因为X先手，且有一位获胜后，游戏结束，那么：
        //1.1.X获胜，X的数量等于O的数量+1；O获胜，X的数量等于O的数量；没有胜负，X的数量等于O的数量+1或X的数量等于O的数量
        //1.2.X和O不能同时获胜
        char winner = 0;
        int xCount = 0;
        int oCount = 0;

        char c1;
        char c2;
        char c3;
        for (int i = 0; i < 3; i++) {
            c1 = board[i].charAt(0);
            c2 = board[i].charAt(1);
            c3 = board[i].charAt(2);
            if (c1 == 'X') {
                xCount++;
            } else if (c1 == 'O') {
                oCount++;
            }
            if (c2 == 'X') {
                xCount++;
            } else if (c2 == 'O') {
                oCount++;
            }
            if (c3 == 'X') {
                xCount++;
            } else if (c3 == 'O') {
                oCount++;
            }

            if (c1 == c2 && c1 == c3) {
                if (winner > 0 && winner != c1) {
                    //X和O不能同时获胜
                    return false;
                }
                winner = c1;
            }

            c1 = board[0].charAt(i);
            c2 = board[1].charAt(i);
            c3 = board[2].charAt(i);
            if (c1 == c2 && c1 == c3) {
                if (winner > 0 && winner != c1) {
                    //X和O不能同时获胜
                    return false;
                }
                winner = c1;
            }
        }

        c1 = board[0].charAt(0);
        c2 = board[1].charAt(1);
        c3 = board[2].charAt(2);
        if (c1 == c2 && c1 == c3) {
            if (winner > 0 && winner != c1) {
                //X和O不能同时获胜
                return false;
            }
            winner = c1;
        }

        c1 = board[0].charAt(2);
        c3 = board[2].charAt(0);
        if (c1 == c2 && c1 == c3) {
            if (winner > 0 && winner != c1) {
                //X和O不能同时获胜
                return false;
            }
            winner = c1;
        }

        //X获胜，X的数量等于O的数量+1
        if (winner == 'X' && xCount == oCount) {
            return false;
        }

        //O获胜，X的数量等于O的数量
        if (winner == 'O' && xCount != oCount) {
            return false;
        }

        //没有胜负
        if (oCount > xCount) {
            return false;
        }

        if (xCount > oCount + 1) {
            return false;
        }

        return true;
    }
}
