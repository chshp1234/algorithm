package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.algorithmsJAVA.N叉树的后序遍历.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
//
//树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
//
// 
//
//示例 1：
//
//
//
//输入：root = [1,null,3,2,4,null,5,6]
//输出：[[1],[3,2,4],[5,6]]
//示例 2：
//
//
//
//输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
//输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
// 
//
//提示：
//
//树的高度不会超过 1000
//树的节点总数在 [0, 10^4] 之间
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class N叉树的层序遍历 {
    public List<List<Integer>> levelOrder(Node root) {
        //队列
        //广度优先搜索，用队列层次遍历树
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        List<Integer> temp;
        Node node;
        while (!queue.isEmpty()) {
            temp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                node = queue.poll();
                temp.add(node.val);
                for (Node child : node.children) {
                    queue.offer(child);
                }
            }
            result.add(temp);
        }

        return result;
    }
}
