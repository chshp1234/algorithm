package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class 重新安排行程 {

    @Test
    public void 重新安排行程() {

        List<List<String>> res = new ArrayList<>();

        List<String> item = new ArrayList<>();
        item.add("JFK");
        item.add("EZE");
        res.add(item);

        item = new ArrayList<>();
        item.add("JFK");
        item.add("ZEZ");
        res.add(item);

        item = new ArrayList<>();
        item.add("ZEZ");
        item.add("JFK");
        res.add(item);

        item = new ArrayList<>();
        item.add("EZE");
        item.add("TIA");
        res.add(item);

        item = new ArrayList<>();
        item.add("EZE");
        item.add("HBN");
        res.add(item);

        item = new ArrayList<>();
        item.add("TIA");
        item.add("EZE");
        res.add(item);

        // 简单的说，题解一，正向遍历欧拉路径，题解二（官解）逆向遍历欧拉路径
        System.out.println("重新安排行程:" + findItinerary(res));
    }

    // 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从
    // JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
    //
    // 说明:
    //
    // 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
    // 所有的机场都用三个大写字母表示（机场代码）。
    // 假定所有机票至少存在一种合理的行程。
    // 示例 1:
    //
    // 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
    // 输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
    // 示例 2:
    //
    // 输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
    // 输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
    // 解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/reconstruct-itinerary
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<String> findItinerary(List<List<String>> tickets) {

        // 有向有换图，所有票都有效，需要遍历所有边
        // 用一个哈希表存储所有节点，以及节点对应的出度节点
        Map<String, Queue<String>> in = new HashMap<>();

        int count = tickets.size();

        for (int i = 0; i < count; i++) {

            // 构造图
            String src = tickets.get(i).get(0);
            Queue<String> queue = in.get(src);
            if (queue == null) {
                queue = new PriorityQueue<>();
                in.put(src, queue);
            }
            queue.offer(tickets.get(i).get(1));
        }

        List<String> result = new ArrayList<>();

        result.add("JFK");

        dfs("JFK", in, result, 0, count);

        return result;
    }

    private boolean dfs(
            String current,
            Map<String, Queue<String>> in,
            List<String> result,
            int count,
            int sum) {

        // 若遍历的边的数量等于边的总数，则遍历完成
        if (count == sum) {
            return true;
        }

        // 顶点原来的出度顶点
        Queue<String> src = in.get(current);

        // 由于走过的节点将不再走，所以这里在创建一个队列
        Queue<String> dst = new PriorityQueue<>();

        // 加入原队列顶点
        if (src != null) {
            dst.addAll(src);
        }

        // temp代表之前所有走过的节点
        List<String> temp = new ArrayList<>();
        while (!dst.isEmpty()) {
            // 当前遍历的目标节点
            String s = dst.poll();

            // 经过的边将不能再次经过（防止在有环图中死循环）
            src.poll();
            result.add(s);

            if (temp.size() > 0) {
                // 重新向队列中加入上一个未走完的边（选择其他边时，若再次碰到子节点，还应该能走向这条边）
                String s1 = temp.get(temp.size() - 1);
                src.offer(s1);
            }

            // 继续遍历下一个节点的各条边
            if (dfs(s, in, result, count + 1, sum)) {
                return true;
            } else {

                // 若false，说明此路不通（遍历此节点不能完成所有边都走过的这个过程）
                // 加入上一个节点到列表
                temp.add(s);
                result.remove(result.size() - 1);
            }
        }

        if (src != null) {
            // 清空队列，再向中加入未走过的节点 （由于图有环，会重复加入节点，固此先清空队列，可优化）
            src.clear();
            src.addAll(temp);
        }

        // 如果还有边没遍历
        return false;
    }

    Map<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
    List<String> itinerary = new LinkedList<String>();

    // 思路及算法
    //
    // Hierholzer 算法用于在连通图中寻找欧拉路径，其流程如下：
    //
    // 从起点出发，进行深度优先搜索。
    //
    // 每次沿着某条边从某个顶点移动到另外一个顶点的时候，都需要删除这条边。
    //
    // 如果没有可移动的路径，则将所在节点加入到栈中，并返回。
    //
    // 当我们顺序地考虑该问题时，我们也许很难解决该问题，因为我们无法判断当前节点的哪一个分支是「死胡同」分支。
    //
    // 不妨倒过来思考。我们注意到只有那个入度与出度差为 1
    // 的节点会导致死胡同。而该节点必然是最后一个遍历到的节点。我们可以改变入栈的规则，当我们遍历完一个节点所连的所有节点后，我们才将该节点入栈（即逆序入栈）。
    //
    // 对于当前节点而言，从它的每一个非「死胡同」分支出发进行深度优先搜索，都将会搜回到当前节点。而从它的「死胡同」分支出发进行深度优先搜索将不会搜回到当前节点。也就是说当前节点的死胡同分支将会优先于其他非「死胡同」分支入栈。
    //
    // 这样就能保证我们可以「一笔画」地走完所有边，最终的栈中逆序地保存了「一笔画」的结果。我们只要将栈中的内容反转，即可得到答案。
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/reconstruct-itinerary/solution/zhong-xin-an-pai-xing-cheng-by-leetcode-solution/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public List<String> findItineraryByHierholzer(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0), dst = ticket.get(1);
            if (!map.containsKey(src)) {
                map.put(src, new PriorityQueue<String>());
            }
            map.get(src).offer(dst);
        }
        dfs("JFK");
        Collections.reverse(itinerary);
        return itinerary;
    }

    public void dfs(String curr) {
        while (map.containsKey(curr) && map.get(curr).size() > 0) {
            String tmp = map.get(curr).poll();
            dfs(tmp);
        }
        itinerary.add(curr);
    }
}
