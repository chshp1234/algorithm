package com.study.algorithm.algorithms;

import java.util.ArrayList;
import java.util.List;

//给你一个字符串 text ，该字符串由若干被空格包围的单词组成。每个单词由一个或者多个小写英文字母组成，并且两个单词之间至少存在一个空格。题目测试用例保证 text 至少包含一个单词 。
//
//请你重新排列空格，使每对相邻单词之间的空格数目都 相等 ，并尽可能 最大化 该数目。如果不能重新平均分配所有空格，请 将多余的空格放置在字符串末尾 ，这也意味着返回的字符串应当与原 text 字符串的长度相等。
//
//返回 重新排列空格后的字符串 。
//
// 
//
//示例 1：
//
//输入：text = "  this   is  a sentence "
//输出："this   is   a   sentence"
//解释：总共有 9 个空格和 4 个单词。可以将 9 个空格平均分配到相邻单词之间，相邻单词间空格数为：9 / (4-1) = 3 个。
//示例 2：
//
//输入：text = " practice   makes   perfect"
//输出："practice   makes   perfect "
//解释：总共有 7 个空格和 3 个单词。7 / (3-1) = 3 个空格加上 1 个多余的空格。多余的空格需要放在字符串的末尾。
//示例 3：
//
//输入：text = "hello   world"
//输出："hello   world"
//示例 4：
//
//输入：text = "  walks  udp package   into  bar a"
//输出："walks  udp  package  into  bar  a "
//示例 5：
//
//输入：text = "a"
//输出："a"
// 
//
//提示：
//
//1 <= text.length <= 100
//text 由小写英文字母和 ' ' 组成
//text 中至少包含一个单词
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/rearrange-spaces-between-words
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 重新排列单词间的空格 {
    public String reorderSpaces(String text) {
        //模拟
        //遍历，先统计字符串中空格的数量blankCount，以及单词的数量textCount
        //当单词数量为1时，将所有空格加入到单词尾部
        //否则单词间的间距为 blankCount / (textCount - 1)，最后尾部的空格数量为blankCount % (textCount - 1)
        //依次将单词，单词间距加入StringBuilder中，并且在尾部加入剩余数量的空格即可
        int blankCount = 0;
        StringBuilder stringBuilder = new StringBuilder();
        List<StringBuilder> strings = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                blankCount++;
                if (stringBuilder.length() > 0) {
                    strings.add(stringBuilder);
                    stringBuilder = new StringBuilder();
                }
            } else {
                stringBuilder.append(text.charAt(i));
            }
        }
        if (stringBuilder.length() > 0) {
            strings.add(stringBuilder);
            stringBuilder = new StringBuilder();
        }

        if (strings.size() == 1) {
            stringBuilder.append(strings.get(0));
            for (int t = 0; t < blankCount; t++) {
                stringBuilder.append(" ");
            }
        } else {
            int distance = blankCount / (strings.size() - 1);
            int tier = blankCount % (strings.size() - 1);

            for (int i = 0, l = strings.size() - 1; i < l; i++) {
                stringBuilder.append(strings.get(i));
                for (int b = 0; b < distance; b++) {
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.append(strings.get(strings.size() - 1));
            for (int t = 0; t < tier; t++) {
                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString();
    }

}
