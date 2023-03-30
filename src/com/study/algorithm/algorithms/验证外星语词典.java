package com.study.algorithm.algorithms;

import org.junit.Test;

//某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
//
//给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
//
// 
//
//示例 1：
//
//输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
//输出：true
//解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。
//示例 2：
//
//输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
//输出：false
//解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。
//示例 3：
//
//输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
//输出：false
//解释：当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小（更多信息）。
// 
//
//提示：
//
//1 <= words.length <= 100
//1 <= words[i].length <= 20
//order.length == 26
//在 words[i] 和 order 中的所有字符都是英文小写字母。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/verifying-an-alien-dictionary
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 验证外星语词典 {
    @Test
    public void 验证外星语词典() {

        System.out.println("验证外星语词典:" + isAlienSorted(new String[]{"miracx", "sxckdwzv", "dtijzluhts"}, "zkgwaverfimqxbnctdplsjyohu"));
    }

    public boolean isAlienSorted(String[] words, String order) {
        //哈希表
        //因为都是小写字母，所以用一个26长度的数组，映射原字符和其在当前字典中的位置
        //根据字符位置的映射，检查相邻俩字符串是否是字典升序排序即可
        int len;
        if ((len = words.length) == 1) {
            return true;
        }
        int[] map = new int[26];
        for (int i = 0; i < 26; i++) {
            map[order.charAt(i) - 'a'] = i;
        }

        OUT:
        for (int i = 1; i < len; i++) {
            String word1 = words[i - 1];
            String word2 = words[i];

            int index = 0;
            while (index < word1.length() && index < word2.length()) {
                int char1Index = map[word1.charAt(index) - 'a'];
                int char2Index = map[word2.charAt(index) - 'a'];
                if (char1Index < char2Index) {
                    continue OUT;
                }
                if (char1Index > char2Index) {
                    return false;
                }
                index++;
            }

            if (word1.length() > word2.length()) {
                return false;
            }
        }
        return true;
    }
}
