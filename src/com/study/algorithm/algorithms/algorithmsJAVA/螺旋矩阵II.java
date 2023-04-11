package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

//给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
//
// 
//
//示例 1：
//
//
//输入：n = 3
//输出：[[1,2,3],[8,9,4],[7,6,5]]
//示例 2：
//
//输入：n = 1
//输出：[[1]]
// 
//
//提示：
//
//1 <= n <= 20
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/spiral-matrix-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 螺旋矩阵II {

    @Test
    public void 螺旋矩阵II() {
        System.out.println("螺旋矩阵II:" + Arrays.deepToString(generateMatrix(5)));
    }

    public int[][] generateMatrix(int n) {
        int     len    = n * n;
        int     index  = 0;
        int[][] result = new int[n][n];

        /*方向0向右，1向下，2向左，3向上*/
        int direction = 0;
        /*左右上下边界值*/
        int left = 0, right = n - 1, top = 1, bottom = n - 1;
        //当前"蛇头"坐标
        int r = 0, c = 0;

        while (index < len) {
            result[r][c] = index + 1;
            index++;
            switch (direction) {
                //如果蛇头向右移动
                case 0:
                    if (c < right) {
                        //如果还没碰到右边界，那么就继续向右移动
                        c++;
                    } else {
                        //如果碰到右边界，则蛇头转向，向下移动，并且右边界减小一格（因为此列即将被身子占领）
                        direction = 1;
                        r++;
                        right--;
                    }
                    break;
                //如果蛇头向下移动
                case 1:
                    if (r < bottom) {
                        //如果还没碰到下边界，那么继续向下移动
                        r++;
                    } else {
                        //否则，转而向左移动，同样下边界减小一格
                        direction = 2;
                        c--;
                        bottom--;
                    }
                    break;
                //如果蛇头向左移动
                case 2:
                    if (c > left) {
                        //如果还没碰到左边界，那么继续想做移动
                        c--;
                    } else {
                        //否则，转而向上移动，左边界减小一格（左边界右移一格）
                        direction = 3;
                        r--;
                        left++;
                    }
                    break;
                //如果蛇头想上移动
                case 3:
                    if (r > top) {
                        //如果还没碰到上边界，那么继续向上移动
                        r--;
                    } else {
                        //否则，转而向右移动，上边界减小一格（上边界往下移一格）
                        direction = 0;
                        c++;
                        top++;
                    }
                    break;
            }
        }

        return result;

    }
}
