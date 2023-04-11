package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给定两个字符串 a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。
//
//注意：字符串 "abc" 重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是 "abcabc"。
//
// 
//
//示例 1：
//
//输入：a = "abcd", b = "cdabcdab"
//输出：3
//解释：a 重复叠加三遍后为 "abcdabcdabcd", 此时 b 是其子串。
//示例 2：
//
//输入：a = "a", b = "aa"
//输出：2
//示例 3：
//
//输入：a = "a", b = "a"
//输出：1
//示例 4：
//
//输入：a = "abc", b = "wxyz"
//输出：-1
// 
//
//提示：
//
//1 <= a.length <= 104
//1 <= b.length <= 104
//a 和 b 由小写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/repeated-string-match
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 重复叠加字符串匹配 {

    @Test
    public void 重复叠加字符串匹配() {
        System.out.print("重复叠加字符串匹配：" + repeatedStringMatch("a", "a"));

    }

    public int repeatedStringMatch(String a, String b) {
        //b要是a叠加后的子串，那a最少叠加bLen / aLen +1（b的长度与a的长度取模！=0时）次，使得叠加后的a的长度大于b
        //判断是否存在子串，否则a再叠加一次（b的长度与a的长度取模=2，那么a要完全包裹b则需要再叠加一次）
        int bLen = b.length();
        int aLen = a.length();

        int min = bLen / aLen;
        if ((bLen % aLen) != 0) {
            min++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < min; i++) {
            sb.append(a);
        }

        a = sb.toString();
        if (a.contains(b)) {
            return min;
        }
        a = sb.append(a).toString();
        if (a.contains(b)) {
            return min + 1;
        }
        return -1;
    }
}
