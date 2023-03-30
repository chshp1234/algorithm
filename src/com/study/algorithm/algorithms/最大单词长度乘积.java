package com.study.algorithm.algorithms;

import java.util.Arrays;
import java.util.Comparator;

//给你一个字符串数组 words ，找出并返回 length(words[i]) * length(words[j]) 的最大值，并且这两个单词不含有公共字母。如果不存在这样的两个单词，返回 0 。
//
// 
//
//示例 1：
//
//输入：words = ["abcw","baz","foo","bar","xtfn","abcdef"]
//输出：16
//解释：这两个单词为 "abcw", "xtfn"。
//示例 2：
//
//输入：words = ["a","ab","abc","d","cd","bcd","abcd"]
//输出：4
//解释：这两个单词为 "ab", "cd"。
//示例 3：
//
//输入：words = ["a","aa","aaa","aaaa"]
//输出：0
//解释：不存在这样的两个单词。
// 
//
//提示：
//
//2 <= words.length <= 1000
//1 <= words[i].length <= 1000
//words[i] 仅包含小写字母
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-product-of-word-lengths
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最大单词长度乘积 {
    public int maxProduct(String[] words) {
        //位运算
        //使用位掩码hash来表示每个单词
        //因为字母只有26个，所以可以使用一个int值来表示一整个字符串中的所有字母，以此来记录一个字符串的位掩码
        //当两个字符串的位掩码取与操作后结果为0时，说明两个字符串中的所有字符皆不相同，那么两个字符串可以凑成组合，计算长度的乘积
        //最后返回乘积的最大值即可

        //排序，最大长度字符串放在后面，方便后续进行搜索优化
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int len = words.length;
        int[] hash = new int[len];
        for (int i = 0; i < len; i++) {
            int h = 0;
            //计算字符串的位掩码
            for (int j = 0, jl = words[i].length(); j < jl; j++) {
                h |= 1 << (words[i].charAt(j) - 'a');
            }
            hash[i] = h;
        }

        int result = 0;
        for (int i = len - 1; i >= 1; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if ((hash[i] & hash[j]) == 0) {
                    result = Math.max(result, words[i].length() * words[j].length());
                    //在i固定的情况下搜索到一对字符串，那么第二层j不用在继续遍历，因为就算得到另一对字符串，他们的长度乘积也只会更小。
                    break;
                }
            }
        }

        return result;
    }
}
