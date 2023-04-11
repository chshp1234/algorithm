package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 判断子序列 {

    @Test
    public void 判断子序列() {
        System.out.println("判断子序列:" + isSubsequence("abc", "ahbgdc"));
    }

    // 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
    //
    // 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
    //
    // 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
    //
    // 示例 1:
    // s = "abc", t = "ahbgdc"
    //
    // 返回 true.
    //
    // 示例 2:
    // s = "axc", t = "ahbgdc"
    //
    // 返回 false.
    //
    // 后续挑战 :
    //
    // 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/is-subsequence
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean isSubsequence(String s, String t) {

        int index = 0;
        boolean match;
        int tl = t.length();

        // 方法一，双指针：两层循环，一个遍历s一个遍历t，当t遍历结束s还没遍历结束说明不是子序列
        for (int i = 0, l = s.length(); i < l; i++) {
            match = false;
            while (index < tl) {
                if (s.charAt(i) == t.charAt(index)) {
                    match = true;
                    index++;
                    break;
                }
                index++;
            }
            if (!match) {
                return false;
            }
        }

        return true;

        // 方法二，动态规划：
        // 思路及算法
        //
        // 考虑前面的双指针的做法，我们注意到我们有大量的时间用于在 tt 中找到下一个匹配字符。
        //
        // 这样我们可以预处理出对于 tt 的每一个位置，从该位置开始往后每一个字符第一次出现的位置。
        //
        // 我们可以使用动态规划的方法实现预处理，令 f[i][j] 表示字符串 t 中从位置 i 开始往后字符 j 第一次出现的位置。
        // 在进行状态转移时，如果 t 中位置 i 的字符就是 j，那么 f[i][j]=i，否则 j 出现在位置 i+1 开始往后，
        // 即f[i][j]=f[i+1][j]，因此我们要倒过来进行动态规划，从后往前枚举 i。
        // 假定下标从 0 开始，那么 f[i][j] 中有  0≤i≤m−1 ，对于边界状态f[m−1][..]，我们置 f[m][..] 为 m， 让 f[m−1][..]
        // 正常进行转移。这样如果f[i][j]=m，则表示从位置 i 开始往后不存在字符 j。
        // 这样，我们可以利用 f 数组，每次 O(1) 地跳转到下一个位置，直到位置变为 m （false）或 s 中的每一个字符都匹配成功（true）。
        //
        // 同时我们注意到，该解法中对 t 的处理与 s 无关，且预处理完成后，可以利用预处理数组的信息，线性地算出任意一个字符串 s 是否为 t
        // 的子串。这样我们就可以解决「后续挑战」啦。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/is-subsequence/solution/pan-duan-zi-xu-lie-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }
}
