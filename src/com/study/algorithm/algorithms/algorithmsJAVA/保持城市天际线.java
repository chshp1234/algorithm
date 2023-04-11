package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给你一座由 n x n 个街区组成的城市，每个街区都包含一座立方体建筑。给你一个下标从 0 开始的 n x n 整数矩阵 grid ，其中 grid[r][c] 表示坐落于 r 行 c 列的建筑物的 高度 。
//
//城市的 天际线 是从远处观察城市时，所有建筑物形成的外部轮廓。从东、南、西、北四个主要方向观测到的 天际线 可能不同。
//
//我们被允许为 任意数量的建筑物 的高度增加 任意增量（不同建筑物的增量可能不同） 。 高度为 0 的建筑物的高度也可以增加。然而，增加的建筑物高度 不能影响 从任何主要方向观察城市得到的 天际线 。
//
//在 不改变 从任何主要方向观测到的城市 天际线 的前提下，返回建筑物可以增加的 最大高度增量总和 。
//
// 
//
//示例 1：
//
//
//输入：grid = [
// [3,0,8,4],
// [2,4,5,7],
// [9,2,6,3],
// [0,3,1,0]
// ]
//输出：35
//解释：建筑物的高度如上图中心所示。
//用红色绘制从不同方向观看得到的天际线。
//在不影响天际线的情况下，增加建筑物的高度：
//gridNew = [ [8, 4, 8, 7],
//            [7, 4, 7, 7],
//            [9, 4, 8, 7],
//            [3, 3, 3, 3] ]
//示例 2：
//
//输入：grid = [[0,0,0],[0,0,0],[0,0,0]]
//输出：0
//解释：增加任何建筑物的高度都会导致天际线的变化。
// 
//
//提示：
//
//n == grid.length
//n == grid[r].length
//2 <= n <= 50
//0 <= grid[r][c] <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/max-increase-to-keep-city-skyline
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 保持城市天际线 {

    @Test
    public void 保持城市天际线() {
        int[][] matrix = {{3, 0, 8, 4}, {2, 4, 5, 7}, {9, 2, 6, 3}, {0, 3, 1, 0}};

        System.out.println("保持城市天际线：" + maxIncreaseKeepingSkyline(matrix));
    }

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        //贪心，最大值最小化
        //保持轮廓不变，只需要在当前位置的值不超过横竖中最大值较小的那个即可
        //一趟遍历，找出每一行最大值和每一列最大值
        //二趟遍历，当前列和当前行的最大值中较小的那个减去当前值，即为当前位置的增量
        //所有增量相加既是答案
        int len = grid.length;
        int[][] border = new int[len][2];
        for (int i = 0; i < len; i++) {

            for (int j = 0; j < len; j++) {
                border[i][0] = Math.max(border[i][0], grid[i][j]);
                border[j][1] = Math.max(border[j][1], grid[i][j]);
            }
        }

        int result = 0;
        for (int i = 0; i < len; i++) {

            for (int j = 0; j < len; j++) {
                result += Math.min(border[i][0], border[j][1]) - grid[i][j];
            }
        }

        return result;
    }
}
