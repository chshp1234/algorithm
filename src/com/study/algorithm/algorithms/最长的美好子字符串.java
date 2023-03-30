package com.study.algorithm.algorithms;

import org.junit.Test;

//当一个字符串 s 包含的每一种字母的大写和小写形式 同时 出现在 s 中，就称这个字符串 s 是 美好 字符串。比方说，"abABB" 是美好字符串，因为 'A' 和 'a' 同时出现了，且 'B' 和 'b' 也同时出现了。然而，"abA" 不是美好字符串因为 'b' 出现了，而 'B' 没有出现。
//
//给你一个字符串 s ，请你返回 s 最长的 美好子字符串 。如果有多个答案，请你返回 最早 出现的一个。如果不存在美好子字符串，请你返回一个空字符串。
//
// 
//
//示例 1：
//
//输入：s = "YazaAay"
//输出："aAa"
//解释："aAa" 是一个美好字符串，因为这个子串中仅含一种字母，其小写形式 'a' 和大写形式 'A' 也同时出现了。
//"aAa" 是最长的美好子字符串。
//示例 2：
//
//输入：s = "Bb"
//输出："Bb"
//解释："Bb" 是美好字符串，因为 'B' 和 'b' 都出现了。整个字符串也是原字符串的子字符串。
//示例 3：
//
//输入：s = "c"
//输出：""
//解释：没有美好子字符串。
//示例 4：
//
//输入：s = "dDzeE"
//输出："dD"
//解释："dD" 和 "eE" 都是最长美好子字符串。
//由于有多个美好子字符串，返回 "dD" ，因为它出现得最早。
// 
//
//提示：
//
//1 <= s.length <= 100
//s 只包含大写和小写英文字母。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-nice-substring
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最长的美好子字符串 {
    @Test
    public void 最长的美好子字符串() {
        System.out.println("最长的美好子字符串：" + longestNiceSubstring("Bb"));
    }

    public String longestNiceSubstring(String s) {
        //枚举
        //两层循环，枚举每一个子串，判断每个子串是否满足条件
        //判断子串是否满足条件时，我们只需要知道其中每个小写字母是否有对应的大写字母即可，而不需要知道小写字母和大写字母的个数
        //因为字母最多只有26个，我们可以使用两个int值‘up’和‘low’，分别存储代表不同的大写和小写字母
        //若子串中up==low，则代表这个子串中，所有大写字母都有对应的小写字母，那么即为美好字符串。
        int len = s.length();
        if (len == 1) {
            return "";
        }

        int maxLen = 0;
        int maxIndex = 0;
        for (int i = 0; i < len; i++) {
            //所有大写字母
            int up = 0;
            //所有小写字母
            int low = 0;
            //遍历子串
            for (int j = i; j < len; j++) {
                char c = s.charAt(j);
                if (c <= 90) {
                    //若是大写字母，则将该字母加入up中
                    up |= 1 << (c - 'A');
                } else {
                    //若是小写字母，则将该字母加入low中
                    low |= 1 << (c - 'a');
                }

                //up=low，则该串是完美字符串
                if (up == low && (j - i + 1) > maxLen) {
                    //(j - i + 1) > maxLen，则更新最长完美子串的起始坐标，和最长的长度
                    maxLen = j - i + 1;
                    maxIndex = i;
                }
            }
        }
        return maxLen > 0 ? s.substring(maxIndex, maxIndex + maxLen) : "";
    }
}
