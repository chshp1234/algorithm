package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

//给你一个字符串 s，请你返回 两个相同字符之间的最长子字符串的长度 ，计算长度时不含这两个字符。如果不存在这样的子字符串，返回 -1 。
//
//子字符串 是字符串中的一个连续字符序列。
//
// 
//
//示例 1：
//
//输入：s = "aa"
//输出：0
//解释：最优的子字符串是两个 'a' 之间的空子字符串。
//示例 2：
//
//输入：s = "abca"
//输出：2
//解释：最优的子字符串是 "bc" 。
//示例 3：
//
//输入：s = "cbzxy"
//输出：-1
//解释：s 中不存在出现出现两次的字符，所以返回 -1 。
//示例 4：
//
//输入：s = "cabbac"
//输出：4
//解释：最优的子字符串是 "abba" ，其他的非最优解包括 "bb" 和 "" 。
// 
//
//提示：
//
//1 <= s.length <= 300
//s 只含小写英文字母
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/largest-substring-between-two-equal-characters
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 两个相同字符之间的最长子字符串 {
    @Test
    public void 两个相同字符之间的最长子字符串() {

        System.out.println(
                "两个相同字符之间的最长子字符串:" + maxLengthBetweenEqualCharacters("cbzxy"));
    }

    public int maxLengthBetweenEqualCharacters(String s) {
        //要求出两个字符间的最大间距
        //那么只需要将字符第一次出现的地方记录下来即可
        //之后出现相同字符，就可以和该字符第一次出现的地方相比较，就可得出这个字符间的最大间距
        //要求所有相同字符的最大间距，那么用同样的方式，记录一个最大值max即可
        int[] index = new int[26];
        int len = s.length();
        Arrays.fill(index, len);
        int max = 0;
        for (int i = 0; i < len; i++) {
            int c = s.charAt(i) - 'a';
            max = Math.max(max, i - index[c]);
            if (index[c] == -1) {
                index[c] = i;
            }
        }
        return max - 1;
    }
}
