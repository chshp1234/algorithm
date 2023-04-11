package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 矩阵螺旋遍历 {
    @Test
    public void 螺旋矩阵() {
        int[][] ints = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("原始矩阵:" + Arrays.deepToString(ints));
        System.out.println("螺旋矩阵:" + spiralOrder(ints));
    }

    /**
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     *
     * <p>示例 1:
     *
     * <p>输入: [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ] 输出: [1,2,3,6,9,8,7,4,5] 示例 2:
     *
     * <p>输入: [ [1, 2, 3, 4], [5, 6, 7, 8], [9,10,11,12] ] 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
     *
     * @param matrix the matrix
     * @return the list
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        //## 解题思路
        //我们把矩阵的螺旋遍历类比贪吃蛇的移动，屏幕上布满了食物，小蛇需要靠操作吃完所有食物。
        //首先游戏开始时，蛇头从左上角开始，向右出发。我们要把食物全吃完，最行之有效的方法，其实就是顺时针，一层一层的往里吃（回想起小时候学习机玩贪吃蛇，也是这样的思路哈哈）。
        //
        //### 步骤
        //小蛇不能碰到边界，也不能碰到自己的身体，又因为这里屏幕上都是食物，那么我们每走过一格，身体也会变长一格，走过的路也都将变成身体，所以在一层层往里爬的过程中，自己的身体就相当于变成了边界。
        //1. 定义4个方向0,1,2,3，来确定当前小蛇的移动方向。
        //2. 定义4个边界l,t,r,b，来确定小蛇不能触及的边界。
        //3. 小蛇开始顺时针沿着边界移动，直到吃到最后一个食物，游戏胜利(＾－＾)V
        //
        //总的移动距离就是二维矩阵的大小。

        /*方向0向右，1向下，2向左，3向上*/
        int direction = 0;
        /*左右上下边界值*/
        int left = 0, right = matrix[0].length - 1, top = 1, bottom = matrix.length - 1;
        //当前"蛇头"坐标
        int r = 0, c = 0;

        List<Integer> result = new ArrayList<>();
        //总的移动距离就是二维矩阵的大小。
        for (int i = 0, l = matrix.length * matrix[0].length; i < l; i++) {
            //将当前坐标加入数组
            result.add(matrix[r][c]);
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
