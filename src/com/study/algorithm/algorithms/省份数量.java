package com.study.algorithm.algorithms;

import org.junit.Test;

//有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
//
//省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
//
//给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
//
//返回矩阵中 省份 的数量。
//
// 
//
//示例 1：
//
//
//输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
//输出：2
//示例 2：
//
//
//输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
//输出：3
// 
//
//提示：
//
//1 <= n <= 200
//n == isConnected.length
//n == isConnected[i].length
//isConnected[i][j] 为 1 或 0
//isConnected[i][i] == 1
//isConnected[i][j] == isConnected[j][i]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/number-of-provinces
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 省份数量 {
    @Test
    public void 省份数量() {
        System.out.println("省份数量:" + findCircleNum(
                new int[][]{{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}}));
    }

    public int findCircleNum(int[][] isConnected) {
        //省份的数量就是求无向图的连通分量数量
        //深度优先搜索，广度优先搜索都可以（注意用一个标记数组保存已访问过的节点）
        //并查集
        //使用并查集+路径压缩，也可更方便的求出无向图的连通分量数量
        int count = isConnected.length;
        UnionFind unionFind = new UnionFind(count);
        for (int row = 0; row < count; row++) {
            for (int col = 0; col < count; col++) {
                if (isConnected[row][col] == 1) {
                    unionFind.merge(row, col);
                }
            }
        }
        return unionFind.getUnionCount();
    }

    class UnionFind {
        int[] members;

        public UnionFind(int count) {
            members = new int[count];
            for (int i = 0; i < count; i++) {
                members[i] = i;
            }
        }

        public int find(int x) {
            if (x == members[x]) {
                return members[x];
            }
            members[x] = find(members[x]);
            return members[x];
        }

        public void merge(int sub, int parent) {
            if (sub == parent) {
                return;
            }
            int rootS = find(sub);
            int rootP = find(parent);
            members[rootS] = rootP;
        }

        public int getUnionCount() {
            int size = 0;
            for (int i = 0, len = members.length; i < len; i++) {
                if (members[i] == i) {
                    size++;
                }
            }
            return size;
        }
    }
}
