package com.study.algorithm.algorithms;

//给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
//
//字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
//
// 
//
//示例 1：
//
//
//
//输入：text = "nlaebolko"
//输出：1
//示例 2：
//
//
//
//输入：text = "loonbalxballpoon"
//输出：2
//示例 3：
//
//输入：text = "leetcode"
//输出：0
// 
//
//提示：
//
//1 <= text.length <= 10^4
//text 全部由小写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximum-number-of-balloons
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 气球的最大数量 {
    public int maxNumberOfBalloons(String text) {
        //计数
        //统计每个字母出现的次数
        //字母“balloon”中出现次数最少的即为最多可拼接的单词数
        //注意‘l’和‘o’次数需要除以2（因为一个单词中各有2个字母）
        int len = text.length();
        if (len < 7) {
            return 0;
        }
        int[] counter = new int[26];
        for (int i = 0; i < len; i++) {
            counter[text.charAt(i) - 'a']++;
        }
        int result = counter[1];
        result = Math.min(result, counter[0]);
        result = Math.min(result, counter[11] / 2);
        result = Math.min(result, counter[13]);
        result = Math.min(result, counter[14] / 2);
        return result;
    }
}
