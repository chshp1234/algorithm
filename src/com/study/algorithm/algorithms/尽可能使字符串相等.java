package com.study.algorithm.algorithms;

import org.junit.Test;

//给你两个长度相同的字符串，s 和 t。
//
//将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
//
//用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
//
//如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
//
//如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
//
// 
//
//示例 1：
//
//输入：s = "abcd", t = "bcdf", cost = 3
//输出：3
//解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
//示例 2：
//
//输入：s = "abcd", t = "cdef", cost = 3
//输出：1
//解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
//示例 3：
//
//输入：s = "abcd", t = "acde", cost = 0
//输出：1
//解释：你无法作出任何改动，所以最大长度为 1。
// 
//
//提示：
//
//1 <= s.length, t.length <= 10^5
//0 <= maxCost <= 10^6
//s 和 t 都只含小写英文字母。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/get-equal-substrings-within-budget
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 尽可能使字符串相等 {
    @Test
    public void 尽可能使字符串相等() {
        System.out.println("尽可能使字符串相等:" + equalSubstring("abcd", "acde", 0));
    }

    public int equalSubstring(String s, String t, int maxCost) {
        //双指针
        //维护头指针start，尾指针end，和指针区间总和sum
        //要使子数组长度尽可能大，就需要start尽量小，end尽量大
        //步骤：
        //每次我们令尾指针end+1，并使sum加上当前end指针处两字符的差值（绝对值）；
        //若相加后，此时sum<=maxCost，说明这个区间start-end符合条件。此时相当于窗口变长。
        //否则，令头指针start+1，并使sun减去之前start指针处两字符的差值（绝对值）。此时相当于窗口右移。
        //注意这里，不符合条件时start只需右移一格，我们不需要start一直右移，来重新使得窗口重新符合条件，因为：
        //1.我们只关心子数组的最大长度，不关心子数组到底有哪些
        //2.所以此时start-end的区间肯定到end为止，最大的子区间长度。极端情况，每一个字符差值都不符合条件，那么start将会一直等于end，最后相减的结果也将会是0。
        //最后举个例子，假设两字符最大区间为下标5-10（长度5）。
        //当start等于5之前，最大长度计算出为3，那么窗口右移的过程中，start=5，end=8，此时肯定是符合条件的，end将会一直到10。
        //此时end右移到11时不符合条件，start继续跟踪右移为6，直到字符串结尾，end和start之间的长度会一直是5。
        int len = s.length();
        int start = 0;
        int end = 0;
        int sum = 0;
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        while (end < len) {
            //窗口扩张
            sum += Math.abs(sc[end] - tc[end]);
            if (sum > maxCost) {
                //窗口右移
                sum -= Math.abs(sc[start] - tc[start]);
                start++;
            }
            end++;
        }
        return end - start;
    }
}
