package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

public class 图像渲染 {
    @Test
    public void 图像渲染() {

        System.out.println(
                "和至少为K的最短子数组："
                        + Arrays.deepToString(
                                floodFill(new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2)));
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        boolean[][] visit = new boolean[image.length][image[0].length];

        // 深度优先搜索，并且用一个二维数组记录已搜索过的坐标节点
        DFS(image, sr, sc, visit, image[sr][sc], newColor);
        return image;
    }

    private void DFS(int[][] image, int sr, int sc, boolean[][] visit, int srcColor, int newColor) {

        // 搜索节点未越界，并且没有被搜索过，并且等于源节点颜色
        if (0 <= sr
                && sr < image.length
                && 0 <= sc
                && sc < image[0].length
                && image[sr][sc] == srcColor
                && !visit[sr][sc]) {

            // 赋值目标节点未目标颜色，并且目标坐标设置为“已搜索”
            image[sr][sc] = newColor;
            visit[sr][sc] = true;
            DFS(image, sr - 1, sc, visit, srcColor, newColor);
            DFS(image, sr, sc + 1, visit, srcColor, newColor);
            DFS(image, sr + 1, sc, visit, srcColor, newColor);
            DFS(image, sr, sc - 1, visit, srcColor, newColor);
        }
    }
}
