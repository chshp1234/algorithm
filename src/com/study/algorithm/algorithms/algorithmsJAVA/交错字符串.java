package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

@Deprecated
public class 交错字符串 {
    @Test
    public void 交错字符串() {
        System.out.println("交错字符串:" + isInterleave("aa", "ab", "aaba"));
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


        return true;
    }
}
