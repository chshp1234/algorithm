package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class 二叉树的层平均值 {

    @Test
    public void 二叉树的层平均值() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.right = new TreeNode(5);
        treeNode.right.right = new TreeNode(4);

        /*List<Integer> list = new ArrayList<>();
        list.add(1, 1);*/

        System.out.println("二叉树的层平均值:" + averageOfLevelsByDfs(treeNode));
    }

    // 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
    //
    //
    //
    // 示例 1：
    //
    // 输入：
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    // 输出：[3, 14.5, 11]
    // 解释：
    // 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
    //
    //
    // 提示：
    //
    // 节点值的范围在32位有符号整数范围内。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/average-of-levels-in-binary-tree
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<Double> averageOfLevelsByBfs1(TreeNode root) {

        // 广度优先搜索
        // 思路一：
        // 使用队列，按序添加下一层的元素
        // 最后加入root元素作为“哨兵”，当队列遍历到此元素时，说明一层的元素已经遍历完
        // 根据情况是否再次把哨兵节点加入队列（可记录当前层的非空元素数量，若大于0，则加入；简单的用一个布尔值判断当前层是否有节点存在，若有则加入）
        Deque<TreeNode> deque = new LinkedList<>();

        List<Double> result = new ArrayList<>();
        result.add((double) root.val);
        deque.offer(root.left);
        deque.offer(root.right);
        deque.offer(root);

        double sum = 0;
        int count = 0;
        TreeNode temp;

        while (!deque.isEmpty()) {
            temp = deque.poll();
            if (temp == null) {
                continue;
            }

            if (temp == root && count > 0) {
                // 遍历到此，说明当前层已遍历完，并且当前层的元素数量大于0，计算结果并再加入“哨兵”节点
                result.add(sum / count);
                count = 0;
                sum = 0;
                deque.offer(root);
                continue;
            }
            sum += temp.val;
            count++;
            deque.offer(temp.left);
            deque.offer(temp.right);
        }
        return result;
    }

    public List<Double> averageOfLevelsByBfs2(TreeNode root) {
        // 思路二：
        // 如何确保每一轮遍历的是同一层的全部节点呢？我们可以借鉴层次遍历的做法，广度优先搜索使用队列存储待访问节点，
        // 只要确保在每一轮遍历时，队列中的节点是同一层的全部节点即可。具体做法如下：
        //
        // ·初始时，将根节点加入队列；
        //
        // ·每一轮遍历时，将队列中的节点全部取出，计算这些节点的数量以及它们的节点值之和，并计算这些节点的平均值，
        // 然后将这些节点的全部非空子节点加入队列，重复上述操作直到队列为空，遍历结束。
        //
        // 由于初始时队列中只有根节点，满足队列中的节点是同一层的全部节点，每一轮遍历时都会将队列中的当前层节点全部取出，
        // 并将下一层的全部节点加入队列，因此可以确保每一轮遍历的是同一层的全部节点。
        //
        // 具体实现方面，可以在每一轮遍历之前获得队列中的节点数量 size，遍历时只遍历 size个节点，即可满足每一轮遍历的是同一层的全部节点。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/solution/er-cha-shu-de-ceng-ping-jun-zhi-by-leetcode-soluti/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        List<Double> averages = new ArrayList<Double>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            double sum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                TreeNode left = node.left, right = node.right;
                if (left != null) {
                    queue.offer(left);
                }
                if (right != null) {
                    queue.offer(right);
                }
            }
            averages.add(sum / size);
        }
        return averages;
    }

    public List<Double> averageOfLevelsByDfs(TreeNode root) {
        // 深度优先遍历
        // 可使用一个map记录每一层中元素的个数

        Map<Integer, Integer> map = new HashMap<>();
        List<Double> result = new ArrayList<>();

        dfs(result, map, root, 0);

        for (int i = 0, l = result.size(); i < l; i++) {
            result.set(i, result.get(i) / map.get(i));
        }

        return result;
    }

    public void dfs(List<Double> result, Map<Integer, Integer> map, TreeNode root, int tier) {

        if (result.size() <= tier) {
            // 若当前层还未有元素添加，直接添加该节点值
            result.add((double) root.val);
        } else {
            // 若当前层已有元素添加，则获取并累加
            result.set(tier, result.get(tier) + root.val);
        }

        // 当前层的元素数量+1
        map.put(tier, map.getOrDefault(tier, 0) + 1);

        // 继续递归遍历左右节点
        if (root.left != null) {
            dfs(result, map, root.left, tier + 1);
        }
        if (root.right != null) {
            dfs(result, map, root.right, tier + 1);
        }
    }
}
