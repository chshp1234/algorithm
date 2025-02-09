package com.study.algorithm.algorithms.algorithmsJAVA;

import com.sun.source.tree.Tree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//现有一棵 无向 树，树中包含 n 个节点，按从 0 到 n - 1 标记。树的根节点是节点 0 。给你一个长度为 n - 1 的二维整数数组 edges，其中 edges[i] = [ai, bi] 表示树中节点 ai 与节点 bi 之间存在一条边。
//
//如果一个节点的所有子节点为根的
//子树
// 包含的节点数相同，则认为该节点是一个 好节点。
//
//返回给定树中 好节点 的数量。
//
//子树 指的是一个节点以及它所有后代节点构成的一棵树。
//
//
//
//
//
//示例 1：
//
//输入：edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]
//
//输出：7
//
//说明：
//
//
//树的所有节点都是好节点。
//
//示例 2：
//
//输入：edges = [[0,1],[1,2],[2,3],[3,4],[0,5],[1,6],[2,7],[3,8]]
//
//输出：6
//
//说明：
//
//
//树中有 6 个好节点。上图中已将这些节点着色。
//
//示例 3：
//
//输入：edges = [[0,1],[1,2],[1,3],[1,4],[0,5],[5,6],[6,7],[7,8],[0,9],[9,10],[9,12],[10,11]]
//
//输出：12
//
//解释：
//
//
//除了节点 9 以外其他所有节点都是好节点。
public class 统计好节点的数目 {

    @Test
    public void 统计好节点的数目() {
        System.out.println("统计好节点的数目:" + countGoodNodes(new int[][]{{0, 1}, {1, 2}, {1, 3}, {1, 4}, {0, 5}, {5, 6}, {6, 7}, {7, 8}, {0, 9}, {9, 10}, {9, 12}, {10, 11}}));
    }

    int res = 0;

    public int countGoodNodes(int[][] edges) {

        //图，深度优先搜索
        //思路：
        // 1.将二维数组想象成图的邻接表，以此来构造图。
        // 2.从根节点‘0’出发，判断其每个子节点构成的子树的节点数量是否相同：
        //  2.1如果都相同，则此节点是好节点；
        //  2.2反之不是好节点。再用深度优先搜索；
        // 3.用上述方式判断每一个子节点是否是好节点，最后返回好节点的数量即可。
        Map<Integer, List<Integer>> tree = new HashMap<>();

        //构图
        for (int[] edge : edges) {
            List<Integer> nears = tree.get(edge[0]);
            if (nears == null) {
                nears = new ArrayList<>();
                tree.put(edge[0], nears);
            }
            nears.add(edge[1]);

            nears = tree.get(edge[1]);
            if (nears == null) {
                nears = new ArrayList<>();
                tree.put(edge[1], nears);
            }
            nears.add(edge[0]);
        }

        computeCountGoodNodes(tree, 0, -1);

        return res;
    }

    private int computeCountGoodNodes(Map<Integer, List<Integer>> tree, int root, int parent) {
        List<Integer> childs = tree.get(root);
        int count = 1;

        //如果没有子节点，是一个好节点
        if (childs == null) {
            res++;
            return count;
        }

        boolean isGood = true;
        int childCount = -1;
        for (int child : childs) {

            //因为是无向图，所以遍历时首先需要排除其父节点
            if (child == parent) {
                continue;
            }

            //子节点数量
            int tempCount = computeCountGoodNodes(tree, child, root);
            if (tempCount != childCount) {
                if (childCount == -1) {
                    //先记录第一个子节点的子树节点数量
                    childCount = tempCount;
                } else {
                    //子树的节点数和之前节点的子树的节点数不同，不是好节点
                    isGood = false;
                }
            }

            //更新节点数
            count += tempCount;
        }

        if (isGood) {
            res++;
        }

        return count;
    }
}
