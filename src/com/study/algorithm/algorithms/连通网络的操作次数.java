package com.study.algorithm.algorithms;

import org.junit.Test;

//用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，其中 connections[i] = [a, b] 连接了计算机 a 和 b。
//
//网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
//
//给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。 
//
// 
//
//示例 1：
//
//
//
//输入：n = 4, connections = [[0,1],[0,2],[1,2]]
//输出：1
//解释：拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。
//示例 2：
//
//
//
//输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
//输出：2
//示例 3：
//
//输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
//输出：-1
//解释：线缆数量不足。
//示例 4：
//
//输入：n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
//输出：0
// 
//
//提示：
//
//1 <= n <= 10^5
//1 <= connections.length <= min(n*(n-1)/2, 10^5)
//connections[i].length == 2
//0 <= connections[i][0], connections[i][1] < n
//connections[i][0] != connections[i][1]
//没有重复的连接。
//两台计算机不会通过多条线缆连接。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 连通网络的操作次数 {

    @Test
    public void 连通网络的操作次数() {
        //只需计算出该图的连通分量的数量count，要使得图连通，只需把所有连通分量进行联调，所以最少次数即为count-1。

        //方法一：深度优先搜索
        //首先，根据给出的条件进行构图
        //深度优先遍历图，每当搜索完一次时，把所有遍历过的节点标记为“已搜索”，并从下一个点继续下一轮遍历，
        //若下一个点标记为“已搜索”，说明已被遍历过，跳过；
        //若下一个点没被遍历过，则说明遍历到新的连通分量中，继续深度优先搜索该图；
        //重复，直到所有点都遍历完即可计算出连通分量的数量。

        //方法二：并查集
        //我们可以使用并查集来得到图中的连通分量数。
        //并查集本身就是用来维护连通性的数据结构。

        int[][] arrs = {{0, 1}};
        System.out.println("连通网络的操作次数：" + makeConnected(6, arrs));
    }

    public int makeConnected(int n, int[][] connections) {
        int len = connections.length;
        if (len < n - 1) {
            return -1;
        }

        int[] members = new int[n];
        for (int i = 0; i < n; i++) {
            members[i] = i;
        }

        for (int i = 0; i < len; i++) {
            merge(members, connections[i][0], connections[i][1]);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (members[i] == i) {
                count++;
            }
        }

        return count - 1;
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
