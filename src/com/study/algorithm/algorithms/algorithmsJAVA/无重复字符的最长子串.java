package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class 无重复字符的最长子串 {

    @Test
    public void 无重复字符的最长子串() {
        System.out.println("无重复字符的最长子串：" + lengthOfLongestSubstring("abcabcbb"));
    }
    // 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
    //
    // 示例 1:
    //
    // 输入: "abcabcbb"
    // 输出: 3
    // 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
    // 示例 2:
    //
    // 输入: "bbbbb"
    // 输出: 1
    // 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
    // 示例 3:
    //
    // 输入: "pwwkew"
    // 输出: 3
    // 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
    //      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> container = new HashMap<>();
        int low = 0, result = 1, len = s.length();
        Integer index;

        for (int i = 0; i < len; i++) {
            index = container.put(s.charAt(i), i);
            if (index != null && index >= low) {
                result = Math.max(i - low, result);
                low = index + 1;
            }
        }
        return Math.max(len - low, result);
    }
}
