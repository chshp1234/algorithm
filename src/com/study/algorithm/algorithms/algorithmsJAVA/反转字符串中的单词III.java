package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 反转字符串中的单词III {

    @Test
    public void 反转字符串中的单词III() {
        System.out.println("反转字符串中的单词III:" + reverseWords("Let's take LeetCode contest"));
    }

    public String reverseWords(String s) {

        // 翻转一个单词，当遍历字符串到空格时，可判定此时找到一个单词，再将此单词字符反转即可
        // 方法一：递推（官解），根据下标遍历字符串，当遍历到空格字符时，找到一个单词，进行下一步，对此单词进行反转（需保存单词字符的起止位置）
        // 方法二：递归（本解），递归的搜索字符串，当搜索到空格或结尾时，返回，再依次拼接当前下标的字符（当然也可以用递推的形式，手动维护一个栈空间）

        int len = s.length();

        // 前置条件
        if (len < 2) {
            return s;
        }

        int index = 0;
        StringBuilder stringBuilder = new StringBuilder();

        while (index < len) {
            // 添加空格字符，再最后输出时需删除首部空格
            stringBuilder.append(' ');
            // 反转一个单词，并返回此时的下标
            index = reverseWord(s, stringBuilder, index);
            index++;
        }

        return stringBuilder.deleteCharAt(0).toString();
    }

    private int reverseWord(String s, StringBuilder stringBuilder, int index) {
        if (index == s.length()) {
            return s.length();
        }

        int result = index;

        if (s.charAt(index) != ' ') {
            // 如果当前字符不是空格，则继续搜索下一个字符，并获取返回时的下标
            result = reverseWord(s, stringBuilder, index + 1);
            // 遇到空格或字符结尾时返回，拼接上当前下标的字符
            stringBuilder.append(s.charAt(index));
        }
        return result;
    }
}
