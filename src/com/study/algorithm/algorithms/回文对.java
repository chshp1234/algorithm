package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 回文对 {
    @Test
    public void 回文对() {
        System.out.print(
                "回文对" + palindromePairs(new String[] {"abcd", "dcba", "lls", "s", "sssll"}));
    }

    // 给定一组 互不相同 的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
    //
    //
    //
    // 示例 1：
    //
    // 输入：["abcd","dcba","lls","s","sssll"]
    // 输出：[[0,1],[1,0],[3,2],[2,4]]
    // 解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
    // 示例 2：
    //
    // 输入：["bat","tab","cat"]
    // 输出：[[0,1],[1,0]]
    // 解释：可拼接成的回文串为 ["battab","tabbat"]
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/palindrome-pairs
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<List<Integer>> palindromePairs(String[] words) {

        // 思路：1.用哈希表加入字符串数组中所有字符串的反转字符串
        // 2.判断每个字符串前缀、后缀是否是回文串，如果是回文串，则把剩余未判断的字符串的反转字符串（可从哈希表中获取）加入该字符串尾部或头部
        // （比如字符串lasls，后缀'sls'是回文串，则可以把'al'加入原字符串尾部，方可凑成回文串）

        int len = words.length;

        // 哈希表，存储字符串数组中所有字符串的翻转字符串
        Map<String, Integer> map = new HashMap<>(len);

        // 结果
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            // 存储字符串数组中所有字符串的翻转字符串
            map.put(overturn(words[i].toCharArray()), i);
        }

        String curr;
        for (int i = 0; i < len; i++) {
            curr = words[i];
            int cuLen = curr.length() - 1;
            List<Integer> list;

            // 1.先判断该字符串是否在哈希表中（代表整个字符串的翻转字符串凑成的回文串）
            Integer index = map.getOrDefault(curr, -1);
            if (index != -1 && index != i) {
                list = new ArrayList<>();
                list.add(i);
                list.add(index);
                result.add(list);
            }

            // 2.依次判断后缀是否有回文串
            for (int j = 0; j <= cuLen; j++) {

                if (validPalindrome(curr, cuLen - j, cuLen)) {
                    index = map.getOrDefault(curr.substring(0, cuLen - j), -1);
                    // 如果是回文串，并且能在哈希表中找到剩余字符串翻转后的字符串并且该字符串不是本身
                    if (index != -1 && index != i) {

                        list = new ArrayList<>();
                        list.add(i);
                        list.add(index);
                        result.add(list);
                    }
                }
            }

            // 2.依次判断前缀是否有回文串
            for (int j = 0; j <= cuLen; j++) {
                if (validPalindrome(curr, 0, j)) {
                    index = map.getOrDefault(curr.substring(j + 1, cuLen + 1), -1);
                    // 如果是回文串，并且能在哈希表中找到剩余字符串翻转后的字符串并且该字符串不是本身
                    if (index != -1 && index != i) {
                        list = new ArrayList<>();
                        list.add(index);
                        list.add(i);
                        result.add(list);
                    }
                }
            }
        }

        return result;
    }

    // 字符串翻转
    private String overturn(char[] chars) {
        int low = 0, high = chars.length - 1;
        while (low < high) {
            char temp = chars[low];
            chars[low] = chars[high];
            chars[high] = temp;
            low++;
            high--;
        }
        return new String(chars);
    }

    // 判断字符串是否是回文串
    private boolean validPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }
}
