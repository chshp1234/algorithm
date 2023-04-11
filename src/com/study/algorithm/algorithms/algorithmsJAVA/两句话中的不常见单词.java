package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.*;

//句子 是一串由空格分隔的单词。每个 单词 仅由小写字母组成。
//
//如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 没有出现 ，那么这个单词就是 不常见的 。
//
//给你两个 句子 s1 和 s2 ，返回所有 不常用单词 的列表。返回列表中单词可以按 任意顺序 组织。
//
// 
//
//示例 1：
//
//输入：s1 = "this apple is sweet", s2 = "this apple is sour"
//输出：["sweet","sour"]
//示例 2：
//
//输入：s1 = "apple apple", s2 = "banana"
//输出：["banana"]
// 
//
//提示：
//
//1 <= s1.length, s2.length <= 200
//s1 和 s2 由小写英文字母和空格组成
//s1 和 s2 都不含前导或尾随空格
//s1 和 s2 中的所有单词间均由单个空格分隔
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/uncommon-words-from-two-sentences
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 两句话中的不常见单词 {
    public String[] uncommonFromSentences(String s1, String s2) {
        //哈希表
        //用哈希表分别记录两个句子中，每个单词出现的次数
        //判断句子中的单词时，若当前单词在当前句子只出现一次，并且不在另个句子中，则是“不常见”单词
        List<String> temp = new ArrayList<>();
        String[] sp1 = s1.split(" ");
        String[] sp2 = s2.split(" ");
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        for (String s : sp1) {
            map1.put(s, map1.getOrDefault(s, 0) + 1);
        }

        for (String s : sp2) {
            map2.put(s, map2.getOrDefault(s, 0) + 1);
        }

        for (String s : sp1) {
            if (map1.getOrDefault(s, 0) == 1 && !map2.containsKey(s)) {
                temp.add(s);
            }
        }

        for (String s : sp2) {
            if (map2.getOrDefault(s, 0) == 1 && !map1.containsKey(s)) {
                temp.add(s);
            }
        }

        int index = 0;
        String[] result = new String[temp.size()];
        for (String s : temp) {
            result[index++] = s;
        }
        return result;
    }
}
