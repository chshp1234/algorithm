package com.study.algorithm.algorithms;

//有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
//
//这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
//
//岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
//
//返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋 。
//
// 
//
//示例 1：
//
//
//
//输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
//输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
//示例 2：
//
//输入: heights = [[2,1],[1,2]]
//输出: [[0,0],[0,1],[1,0],[1,1]]
// 
//
//提示：
//
//m == heights.length
//n == heights[r].length
//1 <= m, n <= 200
//0 <= heights[r][c] <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/pacific-atlantic-water-flow
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 太平洋大西洋水流问题 {
    @Test
    public void 太平洋大西洋水流问题() {
        System.out.println("太平洋大西洋水流问题：" + pacificAtlantic(
                new int[][]{{2, 1},
                        {1, 2}
                }));
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        //二维数组遍历（深度优先搜索，广度优先搜索）
        //跟怒水往低处流，那么我们反过来想，海洋处肯定是最低处。
        //我们可以从海洋开始，从最低处往高处走，每走过一处，对该格子进行标记，表示，这个格子的水能流向海洋。

        //用两个二维数组boolean[][] pacific和 boolean[][] atlantic 格子[r,c]是否可以流向太平洋或大西洋。
        //从岛屿的四个边开始遍历，寻找“更高点”，因为四条边靠近海洋，此处的水肯定能流向海洋。
        //上边界和左边界，代表流向太平洋，根据以上分析，一步步找到更高点，标记此点位的水可以流向太平洋
        //同理，从下边界和右边界开始遍历，寻找可以流向大西洋的格子。

        //最后再遍历每个格子，根据前面得到的标记pacific和atlantic，如果该格子的水能同时流向太平洋和大西洋，则将该格子加入结果中。
        int row = heights.length - 1;
        int col = heights[0].length - 1;

        boolean[][] pacific = new boolean[row + 1][col + 1];
        boolean[][] atlantic = new boolean[row + 1][col + 1];

        //遍历上边界和下边界
        for (int i = 0; i <= col; i++) {
            dfs(heights, pacific, 0, i, 0);
            dfs(heights, atlantic, row, i, 0);
        }

        //遍历左边界和右边界
        for (int i = 0; i <= row; i++) {
            dfs(heights, pacific, i, 0, 0);
            dfs(heights, atlantic, i, col, 0);
        }

        List<List<Integer>> result = new ArrayList<>();

        List<Integer> temp;
        for (int r = 0; r <= row; r++) {
            for (int c = 0; c <= col; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    temp = new ArrayList<>();
                    temp.add(r);
                    temp.add(c);
                    result.add(temp);
                }
            }
        }

        return result;
    }

    public void dfs(int[][] heights, boolean[][] ocean, int r, int c, int lastH) {
        //如果该点超出边界，或者该格子已遍历，或者该格子高度小于上一个格子高度
        //则说明该点位的水无法流向上一个格子
        if (!isIsland(heights, r, c) || ocean[r][c] || heights[r][c] < lastH) {
            return;
        }

        //设该点位可以流向目标岛屿
        ocean[r][c] = true;

        //继续遍历4个方向
        dfs(heights, ocean, r - 1, c, heights[r][c]);
        dfs(heights, ocean, r + 1, c, heights[r][c]);
        dfs(heights, ocean, r, c - 1, heights[r][c]);
        dfs(heights, ocean, r, c + 1, heights[r][c]);
    }

    public boolean isIsland(int[][] heights, int r, int c) {
        return r >= 0 && c >= 0 && r != heights.length && c != heights[0].length;
    }
}
