package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//把字符串 s 看作是 “abcdefghijklmnopqrstuvwxyz” 的无限环绕字符串，所以 s 看起来是这样的：
//
//"...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd...." . 
//现在给定另一个字符串 p 。返回 s 中 唯一 的 p 的 非空子串 的数量 。 
//
// 
//
//示例 1:
//
//输入: p = "a"
//输出: 1
//解释: 字符串 s 中只有一个"a"子字符。
//示例 2:
//
//输入: p = "cac"
//输出: 2
//解释: 字符串 s 中的字符串“cac”只有两个子串“a”、“c”。.
//示例 3:
//
//输入: p = "zab"
//输出: 6
//解释: 在字符串 s 中有六个子串“z”、“a”、“b”、“za”、“ab”、“zab”。
// 
//
//提示:
//
//1 <= p.length <= 105
//p 由小写英文字母构成
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/unique-substrings-in-wraparound-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 环绕字符串中唯一的子字符串 {
    @Test
    public void 环绕字符串中唯一的子字符串() {

        System.out.println("环绕字符串中唯一的子字符串：" + findSubstringInWraproundString("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"));
    }

    public int findSubstringInWraproundString(String p) {
        //动态规划
        //设状态dp[i]表示以下标i为结尾，存在于环绕串中子串的数量
        //那么当p[i]-p[i-1]==1或者p[i]-p[i-1]==-25时，说明当前字符和上一个字符是连续的，那么dp[i]=dp[i-1]+1
        //否则dp[i]=1
        //而这是求出所有子串的数量，但题目要求，子串必须唯一，就是说子串不可重复
        //那么我们也可以使用哈希表存储每一个子串，同样的方式我们可以用StringBuilder拼接每个连续的、存在于循环串的子串，并添加到哈希表中，最后返回哈希表中原数数量即可。
        //但是这种方式时间复杂度最坏为O(n^2),并不能通过，所以再换个思路记录。
        //使用dp[a..z]来表示以某个字母（a..z）为结尾，最长的子串的元素个数，这样就可以去除重复内容的子串，并且sum=sum(dp[a]+...+dp[z])
        int result = 0;

        int last = 1;
        int diff;
        //表示以某个字母（a..z）为结尾，最长的子串的元素个数
        int[] memo = new int[26];
        memo[p.charAt(0) - 'a'] = 1;
        for (int i = 1, l = p.length(); i < l; i++) {
            diff = p.charAt(i) - p.charAt(i - 1);

            if (diff == 1 || diff == -25) {
                //若当前字母和上一个字母是连续的
                //连续数量+1
                last++;
            } else {
                //否则连续的数量为1
                last = 1;
            }
            //以当前字母为结尾，最长的子串的元素数量
            memo[p.charAt(i) - 'a'] = Math.max(memo[p.charAt(i) - 'a'], last);
        }

        //sum=sum(dp[a]+...+dp[z])
        for (int i = 0; i < 26; i++) {
            result += memo[i];
        }

        return result;
    }
}
