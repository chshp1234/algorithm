package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
//
//这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
//
//示例1:
//
//输入: pattern = "abba", str = "dog cat cat dog"
//输出: true
//示例 2:
//
//输入:pattern = "abba", str = "dog cat cat fish"
//输出: false
//示例 3:
//
//输入: pattern = "aaaa", str = "dog cat cat dog"
//输出: false
//示例 4:
//
//输入: pattern = "abba", str = "dog dog dog dog"
//输出: false
//说明:
//你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。 
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/word-pattern
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 单词规律 {
    @Test
    public void 单词规律() {
        System.out.println("单词规律:" + wordPattern("aabb", "dog dog cat fish"));
    }

    public boolean wordPattern(String pattern, String s) {
        //哈希表，双向映射
        //本题中，需要匹配字符和字符串一一对应，即一个匹配字符需要对应一个唯一的字符串，一个字符串也必须对应唯一的一个匹配字符。
        //由于匹配字符串中只有小写字母，可以用一个26空间大小的数组来维护字符到字符串的映射，再用一个Map来维护字符串到匹配字符的映射。
        //判断时：
        //若双方都为空，则将双方加入映射表中
        //若一个映射表为空一个不为空，则不匹配
        //若两个映射表都不为空，但映射后的数据不一致则不匹配。

        String[] pToS = new String[26];
        Map<String, Character> sToP = new HashMap<>();

        String[] split = s.split(" ");
        if (split.length != pattern.length()) {
            return false;
        }

        for (int i = 0, len = split.length; i < len; i++) {
            char patternChar = pattern.charAt(i);
            int hash = patternChar - 'a';
            Character character = sToP.get(split[i]);
            if (pToS[hash] == null && character == null) {
                pToS[hash] = split[i];
                sToP.put(split[i], patternChar);
            } else if (pToS[hash] == null || character == null || character != patternChar) {
                return false;
            }
        }

        return true;
    }
}
