package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.algorithmsKT.活字印刷;
import org.junit.Test;

import java.util.*;

//现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
//
//例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
//返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
//
//
//
//示例 1：
//
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：[0,1]
//解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
//示例 2：
//
//输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//输出：[0,2,1,3]
//解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
//因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
//示例 3：
//
//输入：numCourses = 1, prerequisites = []
//输出：[0]
//
//
//提示：
//1 <= numCourses <= 2000
//0 <= prerequisites.length <= numCourses * (numCourses - 1)
//prerequisites[i].length == 2
//0 <= ai, bi < numCourses
//ai != bi
//所有[ai, bi] 互不相同
public class 课程表II {

    @Test
    public void 课程表II() {
        System.out.println("课程表II：" + Arrays.toString(findOrder(2, new int[][]{new int[]{1, 0}})));
    }

    //拓扑排序
    //经典拓扑排序应用
    //先对边集列表进行建图Map<Integer, MyPair>，其中MyPair表示当前节点（key）的出度nexts（子节点），以及入读数pres（前驱节点数）
    //再按照拓扑排序（参考‘课程表’）对图进行深度优先搜索/广度优先搜索
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, MyPair> out = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            MyPair pair = new MyPair();
            out.put(i, pair);
        }
        for (int[] ints : prerequisites) {
            MyPair pair = out.get(ints[1]);
            if (pair.nexts == null) {
                pair.nexts = new ArrayList<>();
            }
            pair.nexts.add(ints[0]);
            pair = out.get(ints[0]);
            pair.pres++;
        }

        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, MyPair> entry : out.entrySet()) {
            if (entry.getValue().pres == 0) {
                queue.offer(entry.getKey());
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer node = queue.poll();
                res.add(node);
                List<Integer> nexts = out.get(node).nexts;
                if (nexts != null) {
                    for (Integer next : nexts) {
                        if (--out.get(next).pres == 0) {
                            queue.offer(next);
                        }
                    }
                }
                out.remove(node);
            }
        }
        if (out.isEmpty()) {
            int[] result = new int[res.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = res.get(i);
            }
            return result;
        }
        return new int[0];
    }

    static class MyPair {
        List<Integer> nexts;
        int pres;
    }
}
