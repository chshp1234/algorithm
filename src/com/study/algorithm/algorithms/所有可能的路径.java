package com.study.algorithm.algorithms;

import java.util.ArrayList;
import java.util.List;

//给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
//
//二维数组的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些节点，空就是没有下一个结点了。
//
//译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a 。
//
// 
//
//示例 1：
//
//
//
//输入：graph = [[1,2],[3],[3],[]]
//输出：[[0,1,3],[0,2,3]]
//解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
//示例 2：
//
//
//
//输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
//输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
//示例 3：
//
//输入：graph = [[1],[]]
//输出：[[0,1]]
//示例 4：
//
//输入：graph = [[1,2,3],[2],[3],[]]
//输出：[[0,1,2,3],[0,2,3],[0,3]]
//示例 5：
//
//输入：graph = [[1,3],[2],[3],[]]
//输出：[[0,1,2,3],[0,3]]
// 
//
//提示：
//
//n == graph.length
//2 <= n <= 15
//0 <= graph[i][j] < n
//graph[i][j] != i（即，不存在自环）
//graph[i] 中的所有元素 互不相同
//保证输入为 有向无环图（DAG）
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/all-paths-from-source-to-target
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 所有可能的路径 {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    int target;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        //深度优先搜索，回溯
        //因为确保图时有向无环图，所以我们只需要从节点0开始，沿着每条有向边进行遍历即可
        //当遍历到目标节点时，把当前链路上的节点列表加入结果列表中，回溯继续下一个节点。
        //
        //因为无环，所以不用考虑会重复问题。
        target = graph.length - 1;
        temp.add(0);
        backTrack(0, graph);
        return result;
    }

    private void backTrack(int index, int[][] graph) {
        if (index == target) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int next : graph[index]) {
            temp.add(next);
            backTrack(next, graph);
            temp.remove(temp.size() - 1);
        }
    }
}
