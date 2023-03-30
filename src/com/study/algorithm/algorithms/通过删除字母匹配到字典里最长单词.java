package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
//
//如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
//
// 
//
//示例 1：
//
//输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
//输出："apple"
//示例 2：
//
//输入：s = "abpcplea", dictionary = ["a","b","c"]
//输出："a"
// 
//
//提示：
//
//1 <= s.length <= 1000
//1 <= dictionary.length <= 1000
//1 <= dictionary[i].length <= 1000
//s 和 dictionary[i] 仅由小写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 通过删除字母匹配到字典里最长单词 {
    @Test
    public void 通过删除字母匹配到字典里最长单词() {
        List list = new ArrayList();
        list.add("aa");
        list.add("aaa");
        list.add("a");
        System.out.println("通过删除字母匹配到字典里最长单词:" + findLongestWord("aaa", list));
    }

    public String findLongestWord(String s, List<String> dictionary) {
        //方法一：双指针
        //双指针暴力匹配，用双指针每次对字典串和原串进行匹配，若能在原串中按序找到所有字典串中的字符，则匹配成功，最后比较字符串长度和字典序，选出符合条件的字符串。

        //方法二：哈希表
        //方法一中，每次都从头开始匹配原串，我们可以用哈希表记录原串中每个字符出现的位置
        //再在字典串中进行匹配时，直接找到该字符出现的下一个最近的位置即可

        int len = s.length();
        List<Integer>[] map = new ArrayList[26];

        //预处理s串，记录s中每个字符出现的位置
        List<Integer> temp;
        for (int i = 0; i < len; i++) {
            int index = s.charAt(i) - 'a';
            temp = map[index];
            if (temp == null) {
                temp = new ArrayList();
                map[index] = temp;
            }

            temp.add(i);
        }

        //初始化一个结果
        String result = "{";

        //遍历字典
        OUT:
        for (String string : dictionary) {
            if (string.length() < result.length()) {
                continue;
            }

            int last = -1;
            int sLen = string.length();

            //遍历字典中的字符串
            SECOND:
            for (int i = 0; i < sLen; i++) {
                temp = map[string.charAt(i) - 'a'];
                if (temp == null) {
                    //原串中没有该字符，则继续遍历下一个字典串
                    continue OUT;
                }

                //遍历该字符在原串s中出现的位置
                for (int j = 0, jl = temp.size(); j < jl; j++) {
                    if (temp.get(j) > last) {
                        //只有当该字符出现的位置大于上一个字符出现的位置（该字符的位置在上一个字符出现的位置之后）
                        last = temp.get(j);
                        //继续遍历下一个字符
                        continue SECOND;
                    }
                }

                //在上一个字符出现的位置（在s串中）之后，已无该字符
                continue OUT;
            }

            //若该字典串长度大于上一个匹配成功的字典串，或者改字典串的字典序小于上一个字典串
            if (string.length() > result.length() || result.compareTo(string) > 0) {
                result = string;
            }
        }

        return result.equals("{") ? "" : result;

    }

    //方法三：动态规划
    //基于方法二改进，方法二中，虽然对s串进行了预处理，每次匹配字典串时，不用从头遍历s，但依然要每次从哈希表中取出某个字符所有的位置下标进行一一比较
    //所以，对此我们可以用动态规划再次进行改进。
    //令 f[i][j] 表示字符串 s 中从位置 i 开始往后字符 j 第一次出现的位置。
    //在进行状态转移时，如果 s 中位置 i 的字符就是 j，那么 f[i][j]=i，否则 j 出现在位置 i+1 开始往后，
    //即 f[i][j]=f[i+1][j]；因此我们要倒过来进行动态规划，从后往前枚举 i。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/solution/tong-guo-shan-chu-zi-mu-pi-pei-dao-zi-di-at66/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public String findLongestWordByDp(String s, List<String> dictionary) {
        int m = s.length();
        int[][] f = new int[m + 1][26];
        Arrays.fill(f[m], m);

        for (int i = m - 1; i >= 0; --i) {
            for (int j = 0; j < 26; ++j) {
                if (s.charAt(i) == (char) ('a' + j)) {
                    f[i][j] = i;
                } else {
                    f[i][j] = f[i + 1][j];
                }
            }
        }
        String res = "";
        for (String t : dictionary) {
            boolean match = true;
            int j = 0;
            for (int i = 0; i < t.length(); ++i) {
                if (f[j][t.charAt(i) - 'a'] == m) {
                    match = false;
                    break;
                }
                j = f[j][t.charAt(i) - 'a'] + 1;
            }
            if (match) {
                if (t.length() > res.length() || (t.length() == res.length() && t.compareTo(res) < 0)) {
                    res = t;
                }
            }
        }
        return res;
    }
}
