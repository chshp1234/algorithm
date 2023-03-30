package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//你有一个单词列表 words 和一个模式  pattern，你想知道 words 中的哪些单词与模式匹配。
//
//如果存在字母的排列 p ，使得将模式中的每个字母 x 替换为 p(x) 之后，我们就得到了所需的单词，那么单词与模式是匹配的。
//
//（回想一下，字母的排列是从字母到字母的双射：每个字母映射到另一个字母，没有两个字母映射到同一个字母。）
//
//返回 words 中与给定模式匹配的单词列表。
//
//你可以按任何顺序返回答案。
//
// 
//
//示例：
//
//输入：words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
//输出：["mee","aqq"]
//解释：
//"mee" 与模式匹配，因为存在排列 {a -> m, b -> e, ...}。
//"ccc" 与模式不匹配，因为 {a -> c, b -> c, ...} 不是排列。
//因为 a 和 b 映射到同一个字母。
// 
//
//提示：
//
//1 <= words.length <= 50
//1 <= pattern.length = words[i].length <= 20
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/find-and-replace-pattern
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 查找和替换模式 {
    @Test
    public void 查找和替换模式() {
        System.out.println("查找和替换模式：" + findAndReplacePattern(new String[]{"mee"}, "abb"));
    }

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        //双向映射
        //原串和模式串中的每个字符应是一对一的关系
        //那么可以用两个哈希表构造字符之间的双向映射
        //对于两个字符，只有当两个映射为空或者映射结果互相匹配时，才可算是正确的映射结果，否则不符合替换模式
        List<String> result = new ArrayList<>();

        for (String word : words) {
            if (match(word, pattern)) {
                result.add(word);
            }
        }

        return result;
    }

    public boolean match(String word, String pattern) {
        Map<Character, Character> w2p = new HashMap<>();
        Map<Character, Character> p2w = new HashMap<>();
        int len = word.length();
        Character charW2P;
        Character charP2W;
        for (int i = 0; i < len; i++) {
            charW2P = w2p.get(word.charAt(i));
            charP2W = p2w.get(pattern.charAt(i));
            if (charW2P == null && charP2W == null) {
                w2p.put(word.charAt(i), pattern.charAt(i));
                p2w.put(pattern.charAt(i), word.charAt(i));
            } else if (charW2P == null || charP2W == null || charW2P != pattern.charAt(i) || charP2W != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
