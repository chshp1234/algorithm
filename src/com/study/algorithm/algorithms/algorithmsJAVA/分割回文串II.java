package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
//
//返回符合要求的 最少分割次数 。
//
// 
//
//示例 1：
//
//输入：s = "aab"
//输出：1
//解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
//示例 2：
//
//输入：s = "a"
//输出：0
//示例 3：
//
//输入：s = "ab"
//输出：1
// 
//
//提示：
//
//1 <= s.length <= 2000
//s 仅由小写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 分割回文串II {
    @Test
    public void 分割回文串II() {

        System.out.println("分割回文串II：" + minCutByDp(
                "abcdeedcfgfc"));
    }

    boolean[][] dp;
    int         len;
    int         count;
    char[]      chars;

    public int minCut(String s) {
        //思路一，回溯求出所有可分割的回文子串列表组合，找出最少的子串数量，思路同‘分割回文串’

        len = s.length();
        count = len;
        dp = new boolean[len][len];
        chars = s.toCharArray();

        //动态规划预处理
        //1.子串长度是奇数时，以i为中心，向两边扩散，判断当前子串是否是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
            int left  = i - 1;
            int right = i + 1;
            //向两边扩散
            while (left >= 0 && right < len) {
                if (chars[left] == chars[right]) {
                    //如果是两端字符相同，则子串是回文串
                    dp[left][right] = true;
                    left--;
                    right++;
                } else {
                    //如果不相同，跳出循环，因为后续的子串都将不是回文串
                    break;
                }
            }
        }
        //2.子串长度是偶数时，以i、i+1为中心，向两边扩散，判断当前子串是否是回文串
        for (int i = 0, l = len - 1; i < l; i++) {
            if (chars[i] == chars[i + 1]) {
                dp[i][i + 1] = true;
                int left  = i - 1;
                int right = i + 2;
                //向两边扩散判断
                while (left >= 0 && right < len) {
                    if (chars[left] == chars[right]) {
                        dp[left][right] = true;
                        left--;
                        right++;
                    } else {
                        break;
                    }
                }
            }
        }

        backTrack(0, 0);
        return count - 1;
    }

    public void backTrack(int index, int currCount) {
        if (index == len) {
            count = Math.min(currCount, count);
            return;
        }


        for (int i = 0, l = len - index; i < l; i++) {
            if (dp[index][index + i]) {
                currCount++;
                backTrack(index + i + 1, currCount);
                currCount--;
            }
        }
    }

    public int minCutByDp(String s) {

        //动态规划
        //状态定义：定义dp[i]为前i个字符中(s[0...i+1])，最少分割的子串数。
        //定义dp[i]为前i个字符中，最少分割子串数(s[0...i+1])，那么整个字符串s中，最少分割次数就为dp[len]-1
        //确定状态后，我们就需要确定状态转移方程。
        //方程定义：dp[i]=min(dp[j])+1，其中0≤j≤i，并且s[j...i-1]是一个回文串。
        //考虑区间s[i...j]是一个回文串，那么对于前j+1个字符中（也就是子串s[0...j]），
        //按s[0...i-1]与s[i...j]两部分分割子串的话，分割后最少的子串数就为dp[i]+1。
        //所以对于下标j处的‘最少分割子串数’，就可以从0遍历到j，寻找min(dp[i]+1)即可。

        //预处理字符串
        //对于回文串的判断，可以进行预处理每个子串是否是回文串，这样判断子串是否是回文串时只需O(1)的时间复杂度就可以获取到。
        //思路，中心扩散。
        //1.子串长度为奇数时，对于s[i]一个字符时，肯定是回文子串，以i为中心，判断回文子串，
        //使用双指针l、r，从i开始向两边扩散如果s[l]=s[r]，则s[l..r]是回文子串，继续扩散，直到一端到头、或者s[l]!=s[r]，则退出循环。
        //2.子串长度为偶数时，当s[i]=s[i+1]时，s[i,i+1]为回文子串，以[i,i+1]为中心，根据方法1的步骤，向两端扩散继续判断更长的回文子串。
        int         len        = s.length();
        boolean[][] b          = new boolean[len][len];
        int[]       dpMinCount = new int[len + 1];
        char[]      chars      = s.toCharArray();

        //预处理是否为回文子串
        //1.子串长度是奇数时，以i为中心，向两边扩散，判断当前子串是否是回文串
        for (int i = 0; i < len; i++) {
            b[i][i] = true;
            int left  = i - 1;
            int right = i + 1;
            //向两边扩散
            while (left >= 0 && right < len) {
                if (chars[left] == chars[right]) {
                    //如果是两端字符相同，则子串是回文串
                    b[left][right] = true;
                    left--;
                    right++;
                } else {
                    //如果不相同，跳出循环，因为后续的子串都将不是回文串
                    break;
                }
            }
        }
        //2.子串长度是偶数时，以i、i+1为中心，向两边扩散，判断当前子串是否是回文串
        for (int i = 0, l = len - 1; i < l; i++) {
            if (chars[i] == chars[i + 1]) {
                b[i][i + 1] = true;
                int left  = i - 1;
                int right = i + 2;
                //向两边扩散判断
                while (left >= 0 && right < len) {
                    if (chars[left] == chars[right]) {
                        b[left][right] = true;
                        left--;
                        right++;
                    } else {
                        break;
                    }
                }
            }
        }

        //定义dpMinCount[i]为前i个字符中，最少分割子串数，那么整个字符串s中，最少分割次数就为dp[len]-1
        Arrays.fill(dpMinCount, len);
        //前0个字符和1个字符时
        dpMinCount[0] = 0;
        dpMinCount[1] = 1;

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                if (b[j][i]) {
                    //当s[j...i]是一个回文串时，更新状态
                    dpMinCount[i + 1] = Math.min(dpMinCount[j], dpMinCount[i + 1]);
                }
            }
            //分割，数量+1
            dpMinCount[i + 1]++;
        }

        return dpMinCount[len] - 1;
    }
}
