package com.study.algorithm.algorithms;

//给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
//
//单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
//
// 
//
//示例 1：
//
//输入：s = "Hello World"
//输出：5
//示例 2：
//
//输入：s = "   fly me   to   the moon  "
//输出：4
//示例 3：
//
//输入：s = "luffy is still joyboy"
//输出：6
// 
//
//提示：
//
//1 <= s.length <= 104
//s 仅有英文字母和空格 ' ' 组成
//s 中至少存在一个单词
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/length-of-last-word
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最后一个单词的长度 {
    public int lengthOfLastWord(String s) {

        //反向遍历

        //既然要求最后一个单词，那么我们直接反向遍历字符串
        //1.首先去除末尾所有空格，直到到达最后一个单词的末尾字符（题目给出最少存在一个单词）
        //2.继续反向遍历，直到到达空格或者遍历到字符串头部，那么这个单词就是一个完整的单词，遍历过程中记录长度即可

        int index = s.length() - 1;
        while (index >= 0) {
            if (s.charAt(index) == ' ') {
                index--;
            } else {
                break;
            }
        }

        int result = 0;
        while (index >= 0) {
            if (s.charAt(index) != ' ') {
                result++;
            } else {
                break;
            }
            index--;
        }

        return result;

    }
}
