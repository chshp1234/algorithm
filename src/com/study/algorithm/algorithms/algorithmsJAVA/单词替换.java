package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//在英语中，我们有一个叫做 词根(root) 的概念，可以词根后面添加其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
//
//现在，给定一个由许多词根组成的词典 dictionary 和一个用空格分隔单词形成的句子 sentence。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
//
//你需要输出替换之后的句子。
//
// 
//
//示例 1：
//
//输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
//输出："the cat was rat by the bat"
//示例 2：
//
//输入：dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
//输出："a a b c"
// 
//
//提示：
//
//1 <= dictionary.length <= 1000
//1 <= dictionary[i].length <= 100
//dictionary[i] 仅由小写字母组成。
//1 <= sentence.length <= 10^6
//sentence 仅由小写字母和空格组成。
//sentence 中单词的总量在范围 [1, 1000] 内。
//sentence 中每个单词的长度在范围 [1, 1000] 内。
//sentence 中单词之间由一个空格隔开。
//sentence 没有前导或尾随空格。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/replace-words
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 单词替换 {
    public String replaceWords(List<String> dictionary, String sentence) {
        //字典树
        //首先遍历字典数组，并生成字典树
        //遍历句子，根据字典树判断其中每个单词是否存在词根，若存在则返回词根替换原单词，否则保留原单词
        //最后返回新生成的句子
        Trie trie = new Trie((char) 0);
        for (String dic : dictionary) {
            genTrie(dic, trie);
        }

        StringBuilder stringBuilder = new StringBuilder();

        String[] strings = sentence.split(" ");
        for (String sen : strings) {
            stringBuilder.append(match(sen, trie)).append(" ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public void genTrie(String s, Trie p) {
        int index = 0;
        int len = s.length();
        while (index < len) {
            if (p.childs.containsKey(s.charAt(index))) {
                p = p.childs.get(s.charAt(index));
            } else {
                break;
            }
            index++;
        }
        while (index < len) {
            Trie temp;
            p.childs.put(s.charAt(index), temp = new Trie(s.charAt(index)));
            p = temp;
            index++;
        }
        p.end = true;
    }

    public String match(String s, Trie p) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, l = s.length(); i < l; i++) {
            if (p.childs.containsKey(s.charAt(i))) {
                p = p.childs.get(s.charAt(i));
                stringBuilder.append(p.c);
                if (p.end) {
                    return stringBuilder.toString();
                }
            } else {
                break;
            }
        }
        return s;
    }

    static class Trie {
        Map<Character, Trie> childs = new HashMap<>();
        char c;
        boolean end;

        public Trie(char c) {
            this.c = c;
        }
    }
}
