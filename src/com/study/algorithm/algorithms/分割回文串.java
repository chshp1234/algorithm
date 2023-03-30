package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
//
//返回 s 所有可能的分割方案。
//
//示例:
//
//输入: "aab"
//输出:
//[
//  ["aa","b"],
//  ["a","a","b"]
//]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/palindrome-partitioning
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 分割回文串 {
    @Test
    public void 分割回文串() {

        System.out.println("分割回文串：" + partition("aabcdc"));
    }

    boolean[][] dp;
    int len;
    List<List<String>> result = new ArrayList<>();
    List<String> temp = new ArrayList<>();
    char[] chars;

    public List<List<String>> partition(String s) {
        //回溯
        //回溯算法中，接受参数index，代表从字符串中的第index下标处开始，寻找index~len之间的所有回文子串。
        //子串长度从1开始，直到len-index的长度到字符串结尾为止。
        //若当前子串s[i...j]不是回文串，则长度继续加1，直到末尾。
        //若当前子串s[i...j]是回文串，则加入临时数组中，下一个子串继续从j+1的下标出开始寻找，并重复以上步骤。
        //若index=len，则说明已遍历到结尾，之前加入临时数组的子串都符合条件，将此数组加入结果数组中（注意需要new新数组）。
        //直到当前长度到达末尾，回溯，并从临时数组中删除上一个加入的子串，开始判断下一个长度，若到结尾，则继续回溯。

        //动态规划预处理字符串
        //对于在回溯算法中判断子串是否是回文串，可以用双指针暴力判断当前子串是否是回文串
        //但这样明显会有很多重复计算
        //可以使用动态规划的思想，预先处理字符串，找出字符串中所有的回文子串，这样在回溯时，对于当前字符串是否是回文串就可以在O(1)时间内获知
        //思路，中心扩散法
        //1.子串长度为奇数时，对于s[i]一个字符时，肯定是回文子串，以i为中心，判断回文子串，
        //使用双指针l、r，从i开始向两边扩散如果s[l]=s[r]，则s[l..r]是回文子串，继续扩散，直到一端到头、或者s[l]!=s[r]，则退出循环
        //2.子串长度为偶数时，当s[i]=s[i+1]时，s[i,i+1]为回文子串，以[i,i+1]为中心，根据方法1的步骤，向两端扩散继续判断更长的回文子串
        len = s.length();
        dp = new boolean[len][len];
        chars = s.toCharArray();

        //动态规划预处理
        //1.子串长度是奇数时，以i为中心，向两边扩散，判断当前子串是否是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
            int left = i - 1;
            int right = i + 1;
            //向两边扩散
            while (left >= 0 && right < len) {
                if (chars[left] == chars[right]) {
                    //如果是两端字符相同，则子串是回文串
                    dp[left][right] = true;
                    left--;
                    right++;
                } else {
                    //如果不相同，跳出循环，因为后续的子串都将不是回文串
                    break;
                }
            }
        }
        //2.子串长度是偶数时，以i、i+1为中心，向两边扩散，判断当前子串是否是回文串
        for (int i = 0, l = len - 1; i < l; i++) {
            if (chars[i] == chars[i + 1]) {
                dp[i][i + 1] = true;
                int left = i - 1;
                int right = i + 2;
                //向两边扩散判断
                while (left >= 0 && right < len) {
                    if (chars[left] == chars[right]) {
                        dp[left][right] = true;
                        left--;
                        right++;
                    } else {
                        break;
                    }
                }
            }
        }

        backTrack(0);
        return result;
    }

    public void backTrack(int index) {
        if (index == len) {
            result.add(new ArrayList<>(temp));
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        int size = temp.size() - 1;

        for (int i = 0, l = len - index; i < l; i++) {
            stringBuilder.append(chars[index + i]);
            if (dp[index][index + i]) {
                temp.add(stringBuilder.toString());
                size++;
                backTrack(index + i + 1);
                temp.remove(size);
                size--;
            }
        }
    }
}
