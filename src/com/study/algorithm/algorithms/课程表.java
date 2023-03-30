package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class 课程表 {

    @Test
    public void 课程表() {
        int[][] ints = {};
        System.out.println("课程表:" + canFinish(4, ints));
    }

    // 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
    //
    // 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
    //
    // 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
    //
    //
    //
    // 示例 1:
    //
    // 输入: 2, [[1,0]]
    // 输出: true
    // 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
    // 示例 2:
    //
    // 输入: 2, [[1,0],[0,1]]
    // 输出: false
    // 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
    //
    //
    // 提示：
    //
    // 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
    // 你可以假定输入的先决条件中没有重复的边。
    // 1 <= numCourses <= 10^5
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/course-schedule
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // 很明显，所有顶点都不相连（没有依赖关系）
        if (prerequisites.length == 0) {
            return true;
        }

        // 思路，根据图的拓扑排序进行遍历。

        // 两个Map，分别储存当前节点所依赖的节点列表和当前节点所指向的下一个节点列表。
        Map<Integer, List<Integer>> in = new HashMap<>();
        Map<Integer, List<Integer>> out = new HashMap<>();

        // 用一个栈（队列）保存图中入度为0的节点（当前节点没有依赖的节点）
        Deque<Integer> stack = new LinkedList<>();

        // 初始化数据，遍历prerequisites数组，对两个Map进行填充（形成整个图依赖关系）
        for (int i = 0, l = prerequisites.length; i < l; i++) {
            List<Integer> list = in.get(prerequisites[i][0]);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(prerequisites[i][1]);
            in.put(prerequisites[i][0], list);

            list = in.get(prerequisites[i][1]);
            if (list == null) {
                list = new ArrayList<>();
                in.put(prerequisites[i][1], list);
            }

            list = out.get(prerequisites[i][0]);
            if (list == null) {
                list = new ArrayList<>();
                out.put(prerequisites[i][0], list);
            }

            list = out.get(prerequisites[i][1]);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(prerequisites[i][0]);
            out.put(prerequisites[i][1], list);
        }

        System.out.println("课程表in:" + in);
        System.out.println("课程表out:" + out);

        // 遍历in，在其中把入度为0的节点添加进栈中
        in.forEach(
                new BiConsumer<Integer, List<Integer>>() {
                    @Override
                    public void accept(Integer integer, List<Integer> list) {
                        if (list.size() == 0) {
                            stack.push(integer);
                        }
                    }
                });

        System.out.println("入度为0的课程:" + stack);

        // 若栈中无元素，说明没有入度为0的节点，说明此图有环
        if (stack.size() == 0) {
            return false;
        }

        // 取出栈中元素
        while (!stack.isEmpty()) {
            // 取出栈中元素
            Integer node = stack.pop();
            // 把当前节点从图中删除（由于入度为0，前面没有其他节点指向此节点）
            in.remove(node);

            // 在out中找出此节点所指向的节点列表
            List<Integer> listOut = out.get(node);

            for (int i = 0, l = listOut.size(); i < l; i++) {

                // 获取指向的节点所依赖的节点列表
                List<Integer> listIn = in.get(listOut.get(i));

                // 由于此节点（入度为0的节点）已从图中删除，固可从当前依赖列表中删除
                listIn.remove(node);

                // 同上，若依赖列表为空，则入度为0，加入栈中
                if (listIn.size() == 0) {
                    stack.push(listOut.get(i));
                }
            }
        }

        // 遍历致此，若栈中元素为空（说明没有入度为0的节点），但图中还有节点未遍历完，说明此图有环
        if (in.size() > 0) {
            return false;
        }

        return true;
    }
}
