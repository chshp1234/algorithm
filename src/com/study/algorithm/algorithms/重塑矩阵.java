package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

//在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
//
//给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
//
//重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
//
//如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
//
//示例 1:
//
//输入:
//nums =
//[[1,2],
// [3,4]]
//r = 1, c = 4
//输出:
//[[1,2,3,4]]
//解释:
//行遍历nums的结果是 [1,2,3,4]。新的矩阵是 1 * 4 矩阵, 用之前的元素值一行一行填充新矩阵。
//示例 2:
//
//输入:
//nums =
//[[1,2],
// [3,4]]
//r = 2, c = 4
//输出:
//[[1,2],
// [3,4]]
//解释:
//没有办法将 2 * 2 矩阵转化为 2 * 4 矩阵。 所以输出原矩阵。
//注意：
//
//给定矩阵的宽和高范围在 [1, 100]。
//给定的 r 和 c 都是正数。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/reshape-the-matrix
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 重塑矩阵 {
    @Test
    public void 重塑矩阵() {
        int[][] ints = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println("重塑矩阵:" + Arrays.deepToString(matrixReshape(ints, 4, 3)));
    }

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        //二维数组一维表示
        //对于一个row行col列的二维数组，我们要把其中的元素降维打击为一个一位数组中的元素，
        //对于其中(i，j)坐标下的元素，展开后的下标为i*col+col。
        //同样，对于一个一维下标为n的元素，如果需要展开为col列的二维数组，其下标为（n/col，n%col）。能不能正常填充，就看行坐标是否越界。
        //可以看到，坐标之间的映射只跟二维数组的列数‘col’有关。
        int row = nums.length;
        int col = nums[0].length;
        if (row * col != r * c) {
            return nums;
        }

        //模拟一维下标
        int index = 0;
        int[][] result = new int[r][c];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //一位数组二维表示
                result[index / c][index % c] = nums[i][j];
                //坐标+1
                index++;
            }
        }
        return result;
    }
}
