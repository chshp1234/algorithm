package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
//
//示例:
//
//输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//]
//说明：
//
//所有输入均为小写字母。
//不考虑答案输出的顺序。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/group-anagrams
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 字母异位词分组 {

    @Test
    public void 字母异位词分组() {
        String[] strings = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("字母异位词分组" + groupAnagrams(strings));
    }

    public List<List<String>> groupAnagrams(String[] strs) {

        // 哈希
        // 如果每个单词，字母相同，但字母位置不同视为同一个词，可对此类字母进行一定的计算，按规则存入哈希表中
        // 这个规则可以对单词字母进行排序，若是同类单词，则排序后的单词中等字母顺序肯定是一致的
        // 可使用Arrays对char[]数组进行排序后生成新的String
        // 因为题目给出单词均为小写字母，也可使用计数排序，重新生成新的String
        // 将新的String作为哈希表的键，值即为在结果数组中的位置

        Map<Integer, Integer> map = new HashMap<>();
        List<List<String>> result = new ArrayList<>();

        int size = 0;

        for (String string : strs) {
            int hashcode = getHashcode(string);
            Integer integer = map.get(hashcode);
            if (integer != null) {
                result.get(integer).add(string);
            } else {
                List<String> list = new ArrayList<>();
                list.add(string);
                result.add(list);
                map.put(hashcode, size);
                size++;
            }
        }

        return result;
    }

    public int getHashcode(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        return new String(chars).hashCode();
    }
}
