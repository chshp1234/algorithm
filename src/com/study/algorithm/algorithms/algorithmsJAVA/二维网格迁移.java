package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//给你一个 m 行 n 列的二维网格 grid 和一个整数 k。你需要将 grid 迁移 k 次。
//
//每次「迁移」操作将会引发下述活动：
//
//位于 grid[i][j] 的元素将会移动到 grid[i][j + 1]。
//位于 grid[i][n - 1] 的元素将会移动到 grid[i + 1][0]。
//位于 grid[m - 1][n - 1] 的元素将会移动到 grid[0][0]。
//请你返回 k 次迁移操作后最终得到的 二维网格。
//
// 
//
//示例 1：
//
//
//
//输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
//输出：[[9,1,2],[3,4,5],[6,7,8]]
//示例 2：
//
//
//
//输入：grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
//输出：[[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
//示例 3：
//
//输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
//输出：[[1,2,3],[4,5,6],[7,8,9]]
// 
//
//提示：
//
//m == grid.length
//n == grid[i].length
//1 <= m <= 50
//1 <= n <= 50
//-1000 <= grid[i][j] <= 1000
//0 <= k <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/shift-2d-grid
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 二维网格迁移 {
    @Test
    public void 二维网格迁移() {
        System.out.println("二维网格迁移:" + shiftGrid(
                new int[][]{
                        {1, 2, 3, 4, 5},
                        {7, 8, 9, 10, 11}
                }, 7));
    }

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        //一维展开
        //变换K次相当于把二维数组展开成一维后，将元素向右移动k位
        //那么，我们只需要计算移动k位后，元素的新的r和c的下标即可
        //可用一个临时数组存储变化后的元素，再将元素添加进结果列表中
        int row = grid.length;
        int col = grid[0].length;
        int[][] temp = new int[row][col];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                temp[(r + ((c + k) / col)) % row][(c + k) % col] = grid[r][c];
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int r = 0; r < row; r++) {
            List<Integer> list = new ArrayList<>();
            for (int c = 0; c < col; c++) {
                list.add(temp[r][c]);
            }
            result.add(list);
        }

        return result;
    }
}
