package com.study.algorithm.algorithms;

public class 实现一个魔法字典 {
    class MagicDictionary {
        //字典树
        //利用字典树存储每个单词
        //用DFS搜索字典树，每一个单词可以替换或者不替换
        //若已经替换了单词，那么之后的单词都不能替换，且遍历到字符串末尾时，同时需要是一个字典树的结尾，才能替换成功
        //若还没替换，那么当前单词可以选择替换或者不替换
        Tier root = new Tier((char) 0);

        public MagicDictionary() {

        }

        public void buildDict(String[] dictionary) {
            Tier parent;
            int charIndex;
            for (String dic : dictionary) {
                parent = root;
                for (int i = 0, l = dic.length(); i < l; i++) {
                    if (parent.childs[(charIndex = dic.charAt(i) - 'a')] == null) {
                        parent.childs[charIndex] = new Tier(dic.charAt(i));
                    }
                    parent = parent.childs[charIndex];
                }
                parent.isEnd = true;
            }
        }

        public boolean search(String searchWord) {
            return dfsSearch(searchWord, root, 0, false);
        }

        public boolean dfsSearch(String word, Tier parent, int index, boolean isReplace) {
            if (parent == null) {
                return false;
            }
            if (index == word.length()) {
                return parent.isEnd && isReplace;
            }
            if (isReplace) {
                if ((parent = parent.childs[word.charAt(index) - 'a']) == null) {
                    return false;
                }
                return dfsSearch(word, parent, index + 1, true);
            }
            if (dfsSearch(word, parent.childs[word.charAt(index) - 'a'], index + 1, false)) {
                return true;
            }
            for (Tier child : parent.childs) {
                if (child != null && child.aChar != word.charAt(index)) {
                    if (dfsSearch(word, child, index + 1, true)) {
                        return true;
                    }
                }
            }
            return false;
        }

        class Tier {
            Tier[] childs = new Tier[26];
            char aChar;
            boolean isEnd;

            public Tier(char c) {
                aChar = c;
            }
        }
    }


}
