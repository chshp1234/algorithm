package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//给你一个二叉树的根结点 root ，请返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
//
//一个结点的 「子树元素和」 定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
//
// 
//
//示例 1：
//
//
//
//输入: root = [5,2,-3]
//输出: [2,-3,4]
//示例 2：
//
//
//
//输入: root = [5,2,-5]
//输出: [2]
// 
//
//提示:
//
//节点数在 [1, 104] 范围内
//-105 <= Node.val <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/most-frequent-subtree-sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 出现次数最多的子树元素和 {
    Map<Integer, Integer> counter = new HashMap<>();
    int maxCount = 0;

    public int[] findFrequentTreeSum(TreeNode root) {
        //深度优先搜索
        //dfs搜索每颗子树
        //并将子树的元素和加入哈希表中
        //遍历时，统计每个子树的元素和出现的次数，并记录最大次数
        //最后根据最大次数，将出现最大次数的元素和加入结果列表中，并返回
        dfs(root);
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (entry.getValue() == maxCount) {
                list.add(entry.getKey());
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int sum = node.val + dfs(node.left) + dfs(node.right);
        int count = counter.getOrDefault(sum, 0) + 1;
        if (count > maxCount) {
            maxCount = count;
        }
        counter.put(sum, count);

        return sum;
    }
}
