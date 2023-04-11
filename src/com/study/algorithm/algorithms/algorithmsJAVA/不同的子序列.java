package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
//
//字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
//
//题目数据保证答案符合 32 位带符号整数范围。
//
// 
//
//示例 1：
//
//输入：s = "rabbbit", t = "rabbit"
//输出：3
//解释：
//如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
//(上箭头符号 ^ 表示选取的字母)
//rabbbit
//^^^^ ^^
//rabbbit
//^^ ^^^^
//rabbbit
//^^^ ^^^
//示例 2：
//
//输入：s = "babgbag", t = "bag"
//输出：5
//解释：
//如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
//(上箭头符号 ^ 表示选取的字母)
//babgbag
//^^ ^
//babgbag
//^^    ^
//babgbag
//^    ^^
//babgbag
//  ^  ^^
//babgbag
//    ^^^
// 
//
//提示：
//
//0 <= s.length, t.length <= 1000
//s 和 t 由英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/distinct-subsequences
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 不同的子序列 {
    @Test
    public void 不同的子序列() {
        System.out.println("不同的子序列:" + numDistinct("rabbbit", "rabbit"));
    }

    public int numDistinct(String s, String t) {

        //动态规划
        //设方程dp[i][j]表示：字符串t的前i+1个字符，在字符串s的前j+1个字符中，共有多少个不同子序列。
        //1.dp[0][j]:当字符串t取第一个时，相当于，在字符串s的前i项中共有几个字符t[0];
        //2.dp[i][j](i>0，j>=i):首先设置dp[i][i-1]=0,因为t要是s的一个子序列，所以在s中，长度比t小的子串中肯定不存在；
        //dp[i][j]=dp[i-1][j-1]+dp[i][j-1]：确定以s[j]为结尾时的数量+不以s[j]为结尾时的数量。
        //若t[i]==s[j],说明可以找到以s[j]为结尾时的一中组合，组合的数量就为dp[i-1][j-1],
        //若t[i]!=s[j],说明不能以s[j]为结尾，那么dp[i-1][j-1]=0.
        int sLen = s.length();
        int tLen = t.length();
        if (tLen > sLen) {
            return 0;
        }

        //因为dp[i][j]只和上一层数组有关，所以可以用滚动数组优化空间
        int[] dp = new int[sLen];

        char[] sChar   = s.toCharArray();
        char[] tChar   = t.toCharArray();
        char   current = tChar[0];
        if (current == sChar[0]) {
            dp[0] = 1;
        }
        for (int i = 1; i < sLen; i++) {
            if (current == sChar[i]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = dp[i - 1];
            }
        }

        for (int i = 1; i < tLen; i++) {
            //last就代表dp[i-1][j-1]
            int last = dp[i - 1];
            //比t长度还小的s串中，肯定找不到s的子串。
            dp[i - 1] = 0;
            current = tChar[i];
            for (int j = i; j < sLen; j++) {
                int temp = dp[j];
                if (current == sChar[j]) {
                    dp[j] = last + dp[j - 1];
                } else {
                    dp[j] = /*0+*/dp[j - 1];
                }
                last = temp;
            }
        }

        return dp[sLen - 1];
    }
}
