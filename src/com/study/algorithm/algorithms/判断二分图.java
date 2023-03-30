package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class 判断二分图 {

    @Test
    public void 判断二分图() {
        int[][] graph = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        System.out.println("是否是二分图:" + isBipartite(graph));
    }

    // 给定一个无向图graph，当这个图为二分图时返回true。
    //
    // 如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。
    //
    // graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i]
    //  中不存在i，并且graph[i]中没有重复的值。
    //
    //
    // 示例 1:
    // 输入: [[1,3], [0,2], [1,3], [0,2]]
    // 输出: true
    // 解释:
    // 无向图如下:
    // 0----1
    // |    |
    // |    |
    // 3----2
    // 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
    //
    // 示例 2:
    // 输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
    // 输出: false
    // 解释:
    // 无向图如下:
    // 0----1
    // | \  |
    // |  \ |
    // 3----2
    // 我们不能将节点分割成两个独立的子集。
    // 注意:
    //
    // graph 的长度范围为 [1, 100]。
    // graph[i] 中的元素的范围为 [0, graph.length - 1]。
    // graph[i] 不会包含 i 或者有重复的值。
    // 图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/is-graph-bipartite
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean isBipartite(int[][] graph) {
        // 思路：对于图中的任意两个节点 u 和 v，如果它们之间有一条边直接相连，那么 u 和 v 必须属于不同的集合。
        // 根据题意，二分图的定义，我们就用两个集合来分别指定集合A和B，对图进行深度优先遍历，把节点u加入集合A，与其相邻的节点v、w...加入集合B
        // 遍历结束时，此时所有节点分别分布在集合A和集合B中，说明此图是二分图，返回true；若遍历过程中，某个访问过的节点既出现在集合A也出现在集合B，
        // 则说明此图不是二分图，返回false.此外我们给定一个布尔值的数组，用于记录某个节点是否已经访问过。
        //
        // 举例如下无向图中：
        // 1.从顶点0开始，把0加入集合A，1和3加入集合B
        // 2.遍历到顶点1，把1加入到集合B，0和2加入集合A
        // 3.遍历到顶点2，把2加入到集合A，1和3加入到集合B
        // 4.遍历到顶点3，把3加入到集合B，0和2加入到集合A
        // 5.遍历结束，所有顶点分别在两个集合中，返回true
        // 0----1
        // |    |
        // |    |
        // 3----2
        //
        // 再举例无向图如下:
        // 1.从顶点0开始，把0加入集合A，1、2、3加入集合B
        // 2.遍历到顶点2，把2加入到顶点B，0、2加入到顶点A
        // 3.遍历到顶点2，把2加入到顶点1，注意，此时发现顶点2既在集合A也在集合B，所有此图不是二分图，返回false
        // 0----1
        // | \  |
        // |  \ |
        // 3----2
        //

        // 记录顶点遍历状态的布尔值
        boolean[] state = new boolean[graph.length];
        // 两个集合
        Set<Integer> ASet = new HashSet<>();
        Set<Integer> BSet = new HashSet<>();

        for (int i = 0, l = graph.length; i < l; i++) {
            // 如果该顶点还未遍历过
            if (!state[i]) {
                if (!isBipartite(graph, state, i, ASet, BSet)) {
                    return false;
                }
            }
        }

        return true;
    }

    // 深度优先遍历图（这里用Add和Other命名集合，代表，遍历到此的顶点，该加入的集合Add，邻接顶点加入到另一个集合Other）
    private boolean isBipartite(
            int[][] graph, boolean[] state, int index, Set<Integer> AddSet, Set<Integer> OtherSet) {

        // 如果该顶点已经遍历过
        if (state[index]) {
            return true;
        }

        // 先判断，该顶点是否在另一个集合中，若已经存在，则说明此图不是二分图
        if (OtherSet.contains(index)) {
            return false;
        }

        // 添加改顶点到集合中
        AddSet.add(index);

        // 把该顶点的邻接顶点加入到另一个集合中
        for (int i = 0, l = graph[index].length; i < l; i++) {

            // 同样先判断，该顶点是否在此集合（AddSet）中，若已经存在，则说明此图不是二分图
            if (AddSet.contains(graph[index][i])) {
                return false;
            }

            // 添加邻接顶点到另一个集合（OtherSet）中
            OtherSet.add(graph[index][i]);
        }

        // 把次顶点状态设置为“已读”
        state[index] = true;

        // 再对邻接顶点进行深度优先遍历
        for (int i = 0, l = graph[index].length; i < l; i++) {
            // 注意这里，在AddSet参数中放的是OtherSet，因为此时需要遍历的是顶点index的邻接顶点。
            if (!isBipartite(graph, state, graph[index][i], OtherSet, AddSet)) {
                return false;
            }
        }

        return true;
    }
}
