package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 交错字符串 {
    @Test
    public void 交错字符串() {
        System.out.println("交错字符串:" + isInterleave("abababababababababababababababababababababababababababababababababababababababababababababababababbb",
                "babababababababababababababababababababababababababababababababababababababababababababababababaaaba",
                "abababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababbb"));
    }

    // 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
    //
    // 示例 1:
    //
    // 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
    // 输出: true
    // 示例 2:
    //
    // 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
    // 输出: false
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/interleaving-string
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();

        if ((len1 + len2) != len3) {
            return false;
        }
        visit = new boolean[len1 + 1][len2 + 1];
        return backtrack(0, 0, 0, s1, s2, s3);
    }

    boolean[][] visit;

    //回溯,状态记录
    //回溯判断每一个下标是否相同,如果相同,继续判断下一个下标,如果不相同则判断另一个字符串的下标的字符是否相同,如果相同继续判断,如果也不相同,说明无法按此子串进行分割,回溯
    //如果仅仅使用回溯,则会超时,这里再用一个变量boolean[index1][index2]记录遍历的s1的下标index1和s2的下标index2是否已经遍历过
    //如果boolean[index1][index2]=true,说明此组下标,已经遍历过,且无法由此交错组成字符串s3,直接返回fasle;否则说明还未遍历过,更新boolean[index1][index2]=true,并继续判断
    public boolean backtrack(int index1, int index2, int index3, String s1, String s2, String s3) {
        if (visit[index1][index2]) {
            return false;
        }
        visit[index1][index2] = true;
        if (index3 == s3.length()) {
            return true;
        }
        if (index1 == s1.length()) {
            while (index2 < s2.length()) {
                if (s2.charAt(index2++) != s3.charAt(index3++)) {
                    return false;
                }
            }
            return true;
        }
        if (index2 == s2.length()) {
            while (index1 < s1.length()) {
                if (s1.charAt(index1++) != s3.charAt(index3++)) {
                    return false;
                }
            }
            return true;
        }
        return (s1.charAt(index1) == s3.charAt(index3) && backtrack(index1 + 1, index2, index3 + 1, s1, s2, s3))
                || (s2.charAt(index2) == s3.charAt(index3) && backtrack(index1, index2 + 1, index3 + 1, s1, s2, s3));
    }
}
