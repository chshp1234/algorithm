package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

//给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
//
//你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
//
//返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
//
// 
//
//示例 1:
//
//输入：s = "dcab", pairs = [[0,3],[1,2]]
//输出："bacd"
//解释：
//交换 s[0] 和 s[3], s = "bcad"
//交换 s[1] 和 s[2], s = "bacd"
//示例 2：
//
//输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
//输出："abcd"
//解释：
//交换 s[0] 和 s[3], s = "bcad"
//交换 s[0] 和 s[2], s = "acbd"
//交换 s[1] 和 s[2], s = "abcd"
//示例 3：
//
//输入：s = "cba", pairs = [[0,1],[1,2]]
//输出："abc"
//解释：
//交换 s[0] 和 s[1], s = "bca"
//交换 s[1] 和 s[2], s = "bac"
//交换 s[0] 和 s[1], s = "abc"
// 
//
//提示：
//
//1 <= s.length <= 10^5
//0 <= pairs.length <= 10^5
//0 <= pairs[i][0], pairs[i][1] < s.length
//s 中只含有小写英文字母
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/smallest-string-with-swaps
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 交换字符串中的元素 {
    @Test
    public void 交换字符串中的元素() {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(3);
        lists.add(list);
        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        lists.add(list);
//        list = new ArrayList<>();
//        list.add(0);
//        list.add(2);
//        lists.add(list);
        System.out.println("交换字符串中的元素:" + smallestStringWithSwaps("dcab", lists));
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        //并查集
        //可以把paris对看成图中相连的两个点，所有paris对构成一个图
        //使用并查集，查找图中所有的连通分量
        //所有在同一个连通分量中的顶点，可以互相连通
        //根据规则互相连通的顶点可以相互交换
        //我们把同一个连通分量中的所有点，所对应的所有字符按字典序进行排序，当所有连通分量中的点排完序后，所生成的新字符串即为按规则生成的最小字典序的字符串
        int len = s.length();
        int[] members = new int[len];

        for (int i = 0; i < len; i++) {
            members[i] = i;
        }

        for (int i = 0, l = pairs.size(); i < l; i++) {
            merge(members, pairs.get(i).get(0), pairs.get(i).get(1));
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int root = find(members, i);
            List<Integer> item = map.get(root);
            if (item == null) {
                item = new ArrayList<>();
                map.put(root, item);
            }
            item.add(i);
        }

        PriorityQueue<Character> queue = new PriorityQueue<>();
        char[] chars = s.toCharArray();
        for (List<Integer> list : map.values()) {
            int l = list.size();
            for (int i = 0; i < l; i++) {
                queue.offer(chars[list.get(i)]);

            }
            for (int i = 0; i < l; i++) {
                chars[list.get(i)] = queue.poll();
            }
        }

        return new String(chars);

    }

    public int find(int[] members, int x) {
        if (members[x] == x) {
            return members[x];
        }
        int parent = find(members, members[x]);
        members[x] = parent;
        return parent;
    }

    public void merge(int[] members, int sub, int parent) {
        int subP = find(members, sub);
        int pP = find(members, parent);
        members[subP] = pP;
    }


}
