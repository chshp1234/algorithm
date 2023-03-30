package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。
//
//现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。
//
//你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？
//
// 
//
//示例 1:
//
//输入: [[0,2],[1,3]]
//输出: 3
//解释:
//时间为0时，你位于坐标方格的位置为 (0, 0)。
//此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。
//
//等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
//示例2:
//
//输入: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
//输出: 16
//解释:
// 0  1  2  3  4
//24 23 22 21  5
//12 13 14 15 16
//11 17 18 19 20
//10  9  8  7  6
//
//最终的路线用加粗进行了标记。
//我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
// 
//
//提示:
//
//2 <= N <= 50.
//grid[i][j] 是 [0, ..., N*N - 1] 的排列。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/swim-in-rising-water
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 水位上升的泳池中游泳 {
    @Test
    public void 水位上升的泳池中游泳() {
        int[][] ints = new int[][]{
                {0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20},
                {10, 9, 8, 7, 6}
        };
        System.out.println("水位上升的泳池中游泳:" + swimInWater(ints));
    }

    public int swimInWater(int[][] grid) {
        //并查集
        //关于连通性的问题，如果只问结果，不问具体怎么连起来的，还可以考虑使用并查集。

        //把矩阵当成一个图，两点间的最大值为权值。
        //记录将所有边，权值和点的信息；
        //由小到大遍历权值，并连通两个点；若此时左上角和右下角能连通，则此权值即为答案。

        //注意条件：grid[i][j] 是 [0, ..., N*N - 1] 的排列。说明在矩阵中，所有柱子的高度是唯一的，
        //可以使用len*len大小的数组int[],记录每个柱子高度所对应的位置，
        //这样可以更高效的遍历高度值，并根据其比较上下左右各点中的高度，若大于边上某点的高度，则连通某点，并判断左上角和右下角是否连通。

        int len = grid.length;
        Map<Integer, List<int[]>> map = new HashMap<>(len);
        List<int[]> temp;
        int idx = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {

                if (i > 0) {
                    int diff = Math.max(grid[i - 1][j], grid[i][j]);
                    temp = map.get(diff);
                    if (temp == null) {
                        temp = new ArrayList<>();
                        map.put(diff, temp);
                    }
                    temp.add(new int[]{idx - len, idx});
                }
                if (j > 0) {
                    int diff = Math.max(grid[i][j - 1], grid[i][j]);
                    temp = map.get(diff);
                    if (temp == null) {
                        temp = new ArrayList<>();
                        map.put(diff, temp);
                    }
                    temp.add(new int[]{idx - 1, idx});
                }
                idx++;
            }
        }
        idx--;
        int[] members = new int[len * len];
        for (int i = 0, l = members.length; i < l; i++) {
            members[i] = i;
        }

        for (int i = 1, l = members.length; i < l; i++) {
            temp = map.get(i);
            if (temp != null) {
                for (int j = 0, jl = temp.size(); j < jl; j++) {
                    int[] edge = temp.get(j);
                    merge(members, edge[0], edge[1]);
                    if (isConnection(members, 0, idx)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public int find(int[] members, int x) {
        if (members[x] == x) {
            return x;
        }
        int p = find(members, members[x]);
        members[x] = p;
        return p;
    }

    public void merge(int[] members, int x, int y) {
        members[find(members, x)] = find(members, y);
    }

    public boolean isConnection(int[] members, int x, int y) {
        return find(members, x) == find(members, y);
    }

}
