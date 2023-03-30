package com.study.algorithm.algorithms;

import java.util.ArrayList;
import java.util.List;

//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
//
//异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
//
// 
//
//示例 1:
//
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 示例 2:
//
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
//提示:
//
//1 <= s.length, p.length <= 3 * 104
//s 和 p 仅包含小写字母
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 找到字符串中所有字母异位词 {
    public List<Integer> findAnagrams(String s, String p) {
        //哈希表+滑动窗口
        //首先，用哈希表（可以用26大小的数组）记录p字符串中的每个字符的数量
        //窗口滑动：
        //字符串s中的字符，在哈希表中是否存在，且当前剩余数量大于0：
        //若大于0，那么右指针r++；并且如果r - l == plen，说明窗口大小等于p的长度，说明找到一个字母异位词的子串，加入结果，左指针++，同时需要把上一个字符放回到哈希表中。
        //若小于0，说明当前窗口中的字符已不满足字母异位词的条件，左指针++，同时需要把上一个字符放回到哈希表中。
        //注意，对于上述若不满足时，l==r，说明还未找到符合条件的首字母，那么，就不能把当前字符加入哈希表中，只需左右指针同时右移一位；

        int len;

        List<Integer> result = new ArrayList<>();
        if ((len = s.length()) < p.length()) {
            return result;
        }

        int l = 0;
        int r = 0;
        int[] counter = new int[26];
        int plen = p.length();

        //哈希表统计p中字符的数量
        for (int i = 0; i < plen; i++) {
            counter[p.charAt(i) - 'a']++;
        }

        //像一条绿毛虫一样滑动起来
        while (r < len) {
            //s的字符存在于p中
            if (counter[s.charAt(r) - 'a'] > 0) {
                counter[s.charAt(r) - 'a']--;
                r++;

                //此时这个字符已是p的字母异位词
                if (r - l == plen) {
                    result.add(l);
                    //左指针加1，同时需把这个字符重新放回哈希表中
                    counter[s.charAt(l++) - 'a']++;
                }
            } else {
                //r=l，说明一个都未找到，左右指针+1
                if (r == l) {
                    r = ++l;
                } else {
                    //r！=l，说明找到过，左指针加1的同时，需把这个字符重新放回哈希表中
                    counter[s.charAt(l++) - 'a']++;
                }
            }
        }

        return result;
    }
}
