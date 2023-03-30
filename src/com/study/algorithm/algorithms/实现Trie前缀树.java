package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
//
//请你实现 Trie 类：
//
//Trie() 初始化前缀树对象。
//void insert(String word) 向前缀树中插入字符串 word 。
//boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
//boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
// 
//
//示例：
//
//输入
//["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
//[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
//输出
//[null, null, true, false, true, null, true]
//
//解释
//Trie trie = new Trie();
//trie.insert("apple");
//trie.search("apple");   // 返回 True
//trie.search("app");     // 返回 False
//trie.startsWith("app"); // 返回 True
//trie.insert("app");
//trie.search("app");     // 返回 True
// 
//
//提示：
//
//1 <= word.length, prefix.length <= 2000
//word 和 prefix 仅由小写英文字母组成
//insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 实现Trie前缀树 {
    @Test
    public void 实现Trie前缀树() {
        实现Trie前缀树 trie = new 实现Trie前缀树();
        trie.insert("apple");
        boolean b;
        b = trie.search("apple");   // 返回 True
        b = trie.search("app");     // 返回 False
        b = trie.startsWith("app"); // 返回 True
        trie.insert("app");
        b = trie.search("app");     // 返回 True

    }

    //字典树
    //每个子节点由不同字符组成
    //每个子节点需要判断当前节点是否为一个单词的尾节点
    Node node;

    /** Initialize your data structure here. */
    public 实现Trie前缀树() {
        node = new Node((char) 0);
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {

        char[] chars = word.toCharArray();
        Node   temp  = node;

        for (char c : chars) {
            Node child = temp.childs.get(c);
            if (child == null) {
                temp.childs.put(c, child = new Node(c));
            }
            temp = child;
        }
        temp.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] chars = word.toCharArray();
        Node   temp  = node;
        for (char c : chars) {
            if ((temp = temp.childs.get(c)) == null) {
                return false;
            }
        }
        return temp.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        char[] chars = prefix.toCharArray();
        Node   temp  = node;
        for (char c : chars) {
            if ((temp = temp.childs.get(c)) == null) {
                return false;
            }
        }
        return true;
    }

    public static class Node {
        //子节点
        public Map<Character, Node> childs = new HashMap<>();
        //到此节点为止，是否为一个单词的尾节点
        public boolean              isEnd  = false;
        //当前字符
        public char                 mChar;

        public Node(char c) {
            mChar = c;
        }
    }
}
