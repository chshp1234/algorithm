package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.Node;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 克隆图 {

    @Test
    public void 克隆图() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        List<Node> nodes1 = new ArrayList<>();
        nodes1.add(node2);
        nodes1.add(node4);
        node1.neighbors = nodes1;

        List<Node> nodes2 = new ArrayList<>();
        nodes2.add(node1);
        nodes2.add(node3);
        node2.neighbors = nodes2;

        List<Node> nodes3 = new ArrayList<>();
        nodes3.add(node2);
        nodes3.add(node4);
        node3.neighbors = nodes3;

        List<Node> nodes4 = new ArrayList<>();
        nodes4.add(node1);
        nodes4.add(node3);
        node4.neighbors = nodes4;

        System.out.println("克隆图：" + cloneGraph(node1));
    }

    // 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
    //
    // 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
    //
    // class Node {
    //    public int val;
    //    public List<Node> neighbors;
    // }
    //
    //
    // 测试用例格式：
    //
    // 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1（val = 1），第二个节点值为 2（val = 2），以此类推。该图在测试用例中使用邻接列表表示。
    //
    // 邻接列表 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
    //
    // 给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回。
    //
    //
    //
    // 示例 1：
    //
    //
    //
    // 输入：adjList = [[2,4],[1,3],[2,4],[1,3]]
    // 输出：[[2,4],[1,3],[2,4],[1,3]]
    // 解释：
    // 图中有 4 个节点。
    // 节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
    // 节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
    // 节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
    // 节点 4 的值是 4，它有两个邻居：节点 1 和 3 。
    // 示例 2：
    //
    //
    //
    // 输入：adjList = [[]]
    // 输出：[[]]
    // 解释：输入包含一个空列表。该图仅仅只有一个值为 1 的节点，它没有任何邻居。
    // 示例 3：
    //
    // 输入：adjList = []
    // 输出：[]
    // 解释：这个图是空的，它不含任何节点。
    // 示例 4：
    //
    //
    //
    // 输入：adjList = [[2],[1]]
    // 输出：[[2],[1]]
    //
    //
    // 提示：
    //
    // 节点数不超过 100 。
    // 每个节点值 Node.val 都是唯一的，1 <= Node.val <= 100。
    // 无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
    // 由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
    // 图是连通图，你可以从给定节点访问到所有节点。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/clone-graph
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // 深度优先遍历，由于每个节点值都不一致，用一个Map保存已遍历过的节点，后续当有节点指向此值的节点时，直接从Map中取。

        Node resultNode = new Node();

        Map<Integer, Node> map = new HashMap<>();

        DFS(node, resultNode, map);

        return resultNode;
    }

    public void DFS(Node original, Node copy, Map<Integer, Node> map) {

        Node temp;

        copy.val = original.val;
        map.put(copy.val, copy);

        for (int i = 0, l = original.neighbors.size(); i < l; i++) {

            // 若Map中没有该值的节点
            if (!map.containsKey(original.neighbors.get(i).val)) {

                // 复制节点，并使该节点的邻居添加此复制节点（相连）
                temp = new Node(original.neighbors.get(i).val);
                copy.neighbors.add(temp);

                // 继续遍历邻居节点
                DFS(original.neighbors.get(i), temp, map);
            }
            // 若Map中有值，则直接向当前复制节点的邻居中加入该值
            else {
                copy.neighbors.add(map.get(original.neighbors.get(i).val));
            }
        }
    }
}
