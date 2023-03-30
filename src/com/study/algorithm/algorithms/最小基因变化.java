package com.study.algorithm.algorithms;

import java.util.*;

//基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
//
//假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
//
//例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
//另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。
//
//给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
//
//注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
//
// 
//
//示例 1：
//
//输入：start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
//输出：1
//示例 2：
//
//输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
//输出：2
//示例 3：
//
//输入：start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
//输出：3
// 
//
//提示：
//
//start.length == 8
//end.length == 8
//0 <= bank.length <= 10
//bank[i].length == 8
//start、end 和 bank[i] 仅由字符 ['A', 'C', 'G', 'T'] 组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/minimum-genetic-mutation
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最小基因变化 {
    public int minMutation(String start, String end, String[] bank) {
        //图，广度优先搜索，哈希表
        //先用哈希表存储每个有效的基因序列
        //再对start的每个字符变换4次，判断变换后的基因序列是否在基因库中
        //若存在，并且该基因和end基因相等，那么返回变换的次数；若不相同，并且该基因没被遍历过，则加入队列，待下次进行变换
        //若不存在则继续改变并判断下一个字符。
        //若队列为空，则说明无法从start转换成end，返回-1.
        Set<String> visit = new HashSet<>();
        Set<String> map = new HashSet<>(Arrays.asList(bank));

        char[] gen = new char[]{'A', 'C', 'G', 'T'};

        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visit.add(start);

        int result = 0;
        while (!queue.isEmpty()) {
            result++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                char[] change = queue.poll().toCharArray();
                for (int j = 0; j < 8; j++) {
                    char org = change[j];
                    for (int k = 0; k < 4; k++) {
                        change[j] = gen[k];
                        String next = new String(change);

                        if (!map.contains(next)) {
                            continue;
                        }

                        if (end.equals(next)) {
                            return result;
                        }

                        if (!visit.contains(next)) {
                            visit.add(next);
                            queue.add(next);
                        }
                    }
                    change[j] = org;
                }
            }
        }

        return -1;
    }
}
