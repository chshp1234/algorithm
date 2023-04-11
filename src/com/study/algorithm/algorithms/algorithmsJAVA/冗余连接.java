package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

public class 冗余连接 {

    @Test
    public void 冗余连接() {
        int[][] graph = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        System.out.println("冗余连接:" + Arrays.toString(findRedundantConnection(graph)));
    }

    // 在本问题中, 树指的是一个连通且无环的无向图。
    //
    // 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
    //
    // 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
    //
    // 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
    //
    // 示例 1：
    //
    // 输入: [[1,2], [1,3], [2,3]]
    // 输出: [2,3]
    // 解释: 给定的无向图为:
    //   1
    //  / \
    // 2 - 3
    // 示例 2：
    //
    // 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
    // 输出: [1,4]
    // 解释: 给定的无向图为:
    // 5 - 1 - 2
    //     |   |
    //     4 - 3
    // 注意:
    //
    // 输入的二维数组大小在 3 到 1000。
    // 二维数组中的整数在1到N之间，其中N是输入数组的大小。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/redundant-connection
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int[] findRedundantConnection(int[][] edges) {
        // 使用并查集
        // 当遍历到的一条边的两个端点属于同一个集合时，说明此边成环
        UnionFind unionFind = new UnionFind(edges.length + 1);
        int[] result = new int[2];

        for (int i = 0, l = edges.length; i < l; i++) {
            if (unionFind.find(edges[i][0]) == unionFind.find(edges[i][1])) {
                result[0] = edges[i][0];
                result[1] = edges[i][1];
            }
            unionFind.merge(edges[i][0], edges[i][1]);
        }

        return result;
    }

    // 并查集
    public class UnionFind {
        int[] members;

        public UnionFind(int count) {
            members = new int[count];

            for (int i = 0; i < count; i++) {
                members[i] = i;
            }
        }

        public int find(int i) {
            if (members[i] == i) {
                return i;
            }
            return find(members[i]);
        }

        public void merge(int index1, int index2) {
            members[find(index2)] = find(index1);
        }
    }
}
