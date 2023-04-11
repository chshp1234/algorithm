package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.PriorityQueue;

//给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
//
//连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
//
//请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
//
// 
//
//示例 1：
//
//
//
//输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
//输出：20
//解释：
//
//我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
//注意到任意两个点之间只有唯一一条路径互相到达。
//示例 2：
//
//输入：points = [[3,12],[-2,5],[-4,1]]
//输出：18
//示例 3：
//
//输入：points = [[0,0],[1,1],[1,0],[-1,1]]
//输出：4
//示例 4：
//
//输入：points = [[-1000000,-1000000],[1000000,1000000]]
//输出：4000000
//示例 5：
//
//输入：points = [[0,0]]
//输出：0
// 
//
//提示：
//
//1 <= points.length <= 1000
//-106 <= xi, yi <= 106
//所有点 (xi, yi) 两两不同。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/min-cost-to-connect-all-points
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 连接所有点的最小费用 {
    @Test
    public void 连接所有点的最小费用() {
        int[][] arrs = {{0, 0}, {5, 6}, {2, 3}, {7, 3}, {3, 2}, {4, 6}};

        System.out.println("连接所有点的最小费用：" + minCostConnectPoints(arrs));
    }

    public int minCostConnectPoints(int[][] points) {
        //Kruskal 算法,并查集
        //Kruskal 算法是最小生成树计算算法。
        //其根据每条边的权值，从小到大排序
        //每次从边中选出最小权值的边，若边的两个顶点属于不同连通分量，则将这条边加入结果，并合并两个顶点，使其属于同一个连通分量
        //重复以上步骤，直到选出n-1条边为止（生成树的边的数量为顶点数量n-1，在多一条边，将成环）
        //再使用并查集，来方便判断两个顶点是否属于同一个连通分量

        int len = points.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(len, (ints, t1) -> ints[2] - t1[2]);
        //计算n个顶点，每两个顶点两两相连后的边的权值，并根据权值大小加入优先队列中
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                queue.offer(new int[]{
                        i, j, Math.abs(points[j][1] - points[i][1]) + Math.abs(
                        points[j][0] - points[i][0])
                });
            }
        }

        //并查集，顶点集合，初始化时，每个顶点是一个单独的连通分量
        int[] members = new int[len];
        for (int i = 0; i < len; i++) {
            members[i] = i;
        }

        int[] temp;
        int result = 0;
        int side = 1;
        for (int i = 0, l = queue.size(); i < l; i++) {
            temp = queue.poll();
            //从优先队列中依次选出最小权值的边
            //若两个顶点不属于同一个连通分量，则合并两个顶点，并将边值加入结果
            //若加入的边的数量为顶点数量n-1，则返回
            if (find(members, temp[0]) != find(members, temp[1])) {
                result += temp[2];
                merge(members, temp[0], temp[1]);
                side++;
                if (side == len) {
                    break;
                }
            }
        }

        return result;
    }

    public int find(int[] members, int x) {
        if (members[x] == x) {
            return x;
        }
        int parent = find(members, members[x]);
        members[x] = parent;
        return parent;
    }

    public void merge(int[] members, int x, int y) {
        int xp = find(members, x);
        int yp = find(members, y);
        if (xp != yp) {
            members[xp] = yp;
        }
    }
}
