package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.*;

//有一组 n 个人作为实验对象，从 0 到 n - 1 编号，其中每个人都有不同数目的钱，以及不同程度的安静值（quietness）。为了方便起见，我们将编号为 x 的人简称为 "person x "。
//
//给你一个数组 richer ，其中 richer[i] = [ai, bi] 表示 person ai 比 person bi 更有钱。另给你一个整数数组 quiet ，其中 quiet[i] 是 person i 的安静值。richer 中所给出的数据 逻辑自恰（也就是说，在 person x 比 person y 更有钱的同时，不会出现 person y 比 person x 更有钱的情况 ）。
//
//现在，返回一个整数数组 answer 作为答案，其中 answer[x] = y 的前提是，在所有拥有的钱肯定不少于 person x 的人中，person y 是最安静的人（也就是安静值 quiet[y] 最小的人）。
//
// 
//
//示例 1：
//
//输入：richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
//输出：[5,5,2,5,4,5,6,7]
//解释：
//answer[0] = 5，
//person 5 比 person 3 有更多的钱，person 3 比 person 1 有更多的钱，person 1 比 person 0 有更多的钱。
//唯一较为安静（有较低的安静值 quiet[x]）的人是 person 7，
//但是目前还不清楚他是否比 person 0 更有钱。
//answer[7] = 7，
//在所有拥有的钱肯定不少于 person 7 的人中（这可能包括 person 3，4，5，6 以及 7），
//最安静（有较低安静值 quiet[x]）的人是 person 7。
//其他的答案也可以用类似的推理来解释。
//示例 2：
//
//输入：richer = [], quiet = [0]
//输出：[0]
// 
//提示：
//
//n == quiet.length
//1 <= n <= 500
//0 <= quiet[i] < n
//quiet 的所有值 互不相同
//0 <= richer.length <= n * (n - 1) / 2
//0 <= ai, bi < n
//ai != bi
//richer 中的所有数对 互不相同
//对 richer 的观察在逻辑上是一致的
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/loud-and-rich
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 喧闹和富有 {
    @Test
    public void 喧闹和富有() {
        int[][] richer = {{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}};
        int[] quiet = {3, 2, 5, 4, 6, 1, 7, 0};

        System.out.println("喧闹和富有：" + Arrays.toString(loudAndRich(richer, quiet)));
    }

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        //深度优先搜索（拓扑排序也可，广度优先搜索）
        //如果pi比pj有钱，那么我们另指向j，以此来建图；因为逻辑自恰，所以肯定是一个有向无环图
        //我们从入度为0（也就是最穷的人）的人开始进行深度优先搜索
        //直到出度为0的人（也就是最有钱的人），因为此时没人比他更有钱，那么比他更安静的也只有他自己，更新答案数组
        //我们另递归函数返回最安静的人，那么只要所有比他有钱的和他自己相比较后，最安静的人即为当前的答案，返回这个最安静的人
        //递归结束后返回答案数组即可
        int num = quiet.length;
        int[] result = new int[num];
        if (num == 1) {
            return result;
        }

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] ints : richer) {
            List<Integer> temp = graph.get(ints[1]);
            if (temp == null) {
                temp = new LinkedList<>();
                graph.put(ints[1], temp);
            }

            result[ints[0]]++;
            temp.add(ints[0]);
        }
        List<Integer> in0 = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            if (result[i] == 0) {
                in0.add(i);
            }
        }
        boolean[] visit = new boolean[num];

        for (int i : in0) {
            dfs(graph, quiet, i, visit, result);
        }

        return result;
    }

    public int dfs(Map<Integer, List<Integer>> graph, int[] quiet, int people, boolean[] visit, int[] result) {
        if (visit[people]) {
            return result[people];
        }

        List<Integer> out = graph.get(people);
        if (out == null) {
            result[people] = people;
            visit[people] = true;
            return people;
        }

        int minPeople = people;
        for (int i : out) {
            if (quiet[minPeople] > quiet[dfs(graph, quiet, i, visit, result)]) {
                minPeople = result[i];
            }
        }
        result[people] = minPeople;
        visit[people] = true;
        return minPeople;
    }
}
