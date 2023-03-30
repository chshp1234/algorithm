package com.study.algorithm.algorithms;

import org.junit.Test;

//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
//
//示例 1:
//
//输入: s = "anagram", t = "nagaram"
//输出: true
//示例 2:
//
//输入: s = "rat", t = "car"
//输出: false
//说明:
//你可以假设字符串只包含小写字母。
//
//进阶:
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/valid-anagram
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 有效的字母异位词 {
    @Test
    public void 有效的字母异位词() {
        System.out.println("有效的字母异位词：" + isAnagram("abdc", "dbac"));
    }

    public boolean isAnagram(String s, String t) {
        // 本意就是统计字符串中每个字符出现的次数
        // 先统计s串中每个字符出现的次数，然后在t串中做比较
        // 遍历t串时，若发现此字符出现次数为0，则返回false，否则将对应的字符出现的次数-1。
        // 由于本题只给定小写字母，可以使用一个容量26的数组来保存字符串中字符出现的次数
        int[] table = new int[26];

        int len = s.length();

        if (len != t.length()) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            table[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < len; i++) {
            int curr = t.charAt(i) - 'a';
            if (table[curr] == 0) {
                return false;
            }
            table[curr]--;
        }

        return true;
    }
}
