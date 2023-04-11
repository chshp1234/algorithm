package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.*;

//在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。
//
//对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。
//
//返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。
//
//该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，满足 (i, j) 是图的一条有向边。
//
// 
//
//示例 1：
//
//
//输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
//输出：[2,4,5,6]
//解释：示意图如上。
//示例 2：
//
//输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
//输出：[4]
// 
//
//提示：
//
//n == graph.length
//1 <= n <= 104
//0 <= graph[i].length <= n
//graph[i] 按严格递增顺序排列。
//图中可能包含自环。
//图中边的数目在范围 [1, 4 * 104] 内。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-eventual-safe-states
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 找到最终的安全状态 {
    @Test
    public void 找到最终的安全状态() {
        int[][] ints = {{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}};
        System.out.println("找到最终的安全状态:" + eventualSafeNodes(ints));
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {

        //拓扑排序
        //我们反着搜索图，首先构图，注意我们将图的入度和出度全部调换。
        //那么在拓扑排序中，只需要找到所有入度为0的节点即可。因为入度为0，说明原图中，此节点出度为0，那么此节点就为一个终点，注意终点也是起点。


        //简单的说，排除所有成环的节点后，剩余的节点就为答案。
        //那么：
        //方法一：深度优先搜索 + 三色标记法
        //根据题意，若起始节点位于一个环内，或者能到达一个环，则该节点不是安全的。否则，该节点是安全的。
        //
        //我们可以使用深度优先搜索来找环，并在深度优先搜索时，用三种颜色对节点进行标记，标记的规则如下：
        //
        //白色（用 0 表示）：该节点尚未被访问；
        //灰色（用 1 表示）：该节点位于递归栈中，或者在某个环上；
        //黑色（用 2 表示）：该节点搜索完毕，是一个安全节点。
        //当我们首次访问一个节点时，将其标记为灰色，并继续搜索与其相连的节点。
        //
        //如果在搜索过程中遇到了一个灰色节点，则说明找到了一个环，此时退出搜索，栈中的节点仍保持为灰色，这一做法可以将「找到了环」这一信息传递到栈中的所有节点上。
        //
        //如果搜索过程中没有遇到灰色节点，则说明没有遇到环，那么递归返回前，我们将其标记由灰色改为黑色，即表示它是一个安全的节点。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/find-eventual-safe-states/solution/zhao-dao-zui-zhong-de-an-quan-zhuang-tai-yzfz/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        Map<Integer, Set<Integer>> graphIn = new HashMap<>();
        Map<Integer, Set<Integer>> graphOut = new HashMap<>();

        Deque<Integer> list = new LinkedList<>();
        int len = graph.length;
        for (int i = 0; i < len; i++) {
            int[] ins = graph[i];
            if (ins.length == 0) {
                list.offer(i);
                continue;
            }

            for (int in : ins) {
                Set<Integer> temp = graphIn.get(i);
                if (temp == null) {
                    temp = new HashSet<>();
                    graphIn.put(i, temp);
                }
                temp.add(in);

                temp = graphOut.get(in);
                if (temp == null) {
                    temp = new HashSet<>();
                    graphOut.put(in, temp);
                }
                temp.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!list.isEmpty()) {

            int temp = list.poll();
            result.add(temp);

            Set<Integer> outs = graphOut.get(temp);

            if (outs == null) {
                continue;
            }

            for (int out : outs) {

                Set<Integer> tempIn = graphIn.get(out);
                tempIn.remove(temp);
                if (tempIn.size() == 0) {
                    list.offer(out);
                }
            }

        }

        Collections.sort(result);

        return result;
    }
}
