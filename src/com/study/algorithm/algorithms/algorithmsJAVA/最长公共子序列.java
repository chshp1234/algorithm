package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
//
//一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
//
//例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
//两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
//
// 
//
//示例 1：
//
//输入：text1 = "abcde", text2 = "ace"
//输出：3
//解释：最长公共子序列是 "ace" ，它的长度为 3 。
//示例 2：
//
//输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc" ，它的长度为 3 。
//示例 3：
//
//输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0 。
// 
//
//提示：
//
//1 <= text1.length, text2.length <= 1000
//text1 和 text2 仅由小写英文字符组成。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-common-subsequence
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最长公共子序列 {
    @Test
    public void 最长公共子序列() {

        System.out.println("最长公共子序列：" + longestCommonSubsequence("mhunuzqrkzsn",
                                                                 "szulspmhwpazoxijw"
                                                                ));
        //        System.out.println("有效括号的嵌套深度：" + calculateDepth(seq, seq.length()));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        //动态规划
        //状态dp[i][j]代表（text1[0...i],text2[0...j]），在text1中0~i，text2中0~j，最长的子序列长度
        //方程：dp[i][j]=dp[i-1][j-1]+1(当text1[i]=text2[j]时)，若不相等，dp[i][j]=matx(dp[i-1][j],dp[i][j-1])
        //可用滚动数组优化空间复杂度
        int len1 = text1.length();
        int len2 = text2.length();
        if (len1 > len2) {
            return longestCommonSubsequence(text2, text1);
        }

        int[] dp = new int[len2 + 1];

        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();

        char char1;
        int  temp;
        int  last;

        for (int i = 1; i <= len1; i++) {
            char1 = chars1[i - 1];
            last = 0;
            for (int j = 1; j <= len2; j++) {
                temp = dp[j];
                if (char1 == chars2[j - 1]) {
                    dp[j] = last + 1;
                } else {
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
                last = temp;
            }
        }
        return dp[len2];
    }
}
