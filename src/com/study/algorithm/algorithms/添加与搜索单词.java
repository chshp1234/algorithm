package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
//
//实现词典类 WordDictionary ：
//
//WordDictionary() 初始化词典对象
//void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
//bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
// 
//
//示例：
//
//输入：
//["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
//[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
//输出：
//[null,null,null,null,false,true,true,true]
//
//解释：
//WordDictionary wordDictionary = new WordDictionary();
//wordDictionary.addWord("bad");
//wordDictionary.addWord("dad");
//wordDictionary.addWord("mad");
//wordDictionary.search("pad"); // return False
//wordDictionary.search("bad"); // return True
//wordDictionary.search(".ad"); // return True
//wordDictionary.search("b.."); // return True
// 
//
//提示：
//
//1 <= word.length <= 500
//addWord 中的 word 由小写英文字母组成
//search 中的 word 由 '.' 或小写英文字母组成
//最多调用 50000 次 addWord 和 search
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/design-add-and-search-words-data-structure
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 添加与搜索单词 {
    @Test
    public void 添加与搜索单词() {
        System.out.println("添加与搜索单词:");
        添加与搜索单词 words = new 添加与搜索单词();
        words.addWord("bad");
        words.addWord("dad");
        words.addWord("mad");
        System.out.println(words.search("pad"));
        System.out.println(words.search("bad"));
        System.out.println(words.search("b.."));

    }

    //根节点
    Node[] nodes = new Node[26];

    public 添加与搜索单词() {
        //字典树
    }

    public void addWord(String word) {
        char root = (char) (word.charAt(0) - 'a');
        Node node = nodes[root];
        if (node == null) {
            node = nodes[root] = new Node(word.charAt(0));
        }
        for (int i = 1, l = word.length(); i < l; i++) {
            char next = word.charAt(i);
            Node nextNode = node.childs.get(next);
            if (nextNode == null) {
                node.childs.put(next, nextNode = new Node(next));
            }
            node = nextNode;
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        char root = word.charAt(0);
        if (root == '.') {
            for (int i = 0; i < 26; i++) {
                if (dfs(word, 1, nodes[i])) {
                    return true;
                }
            }
            return false;
        } else {
            return dfs(word, 1, nodes[root - 'a']);
        }

    }

    public boolean dfs(String word, int index, Node node) {
        if (node == null) {
            return false;
        }
        if (index == word.length()) {
            return node.isEnd;
        }
        if (word.charAt(index) == '.') {
            for (Map.Entry<Character, Node> entry : node.childs.entrySet()) {
                if (dfs(word, index + 1, entry.getValue())) {
                    return true;
                }
            }
            return false;
        } else {
            return dfs(word, index + 1, node.childs.get(word.charAt(index)));
        }
    }

    //字典树
    public static class Node {
        //子节点
        public Map<Character, Node> childs = new HashMap<>();
        //到此节点为止，是否为一个单词的尾节点
        public boolean isEnd = false;
        //当前字符
        public char mChar;

        public Node(char c) {
            mChar = c;
        }
    }
}
