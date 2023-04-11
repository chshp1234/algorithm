package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给定两个字符串 s 和 t，它们只包含小写字母。
//
//字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
//
//请找出在 t 中被添加的字母。
//
// 
//
//示例 1：
//
//输入：s = "abcd", t = "abcde"
//输出："e"
//解释：'e' 是那个被添加的字母。
//示例 2：
//
//输入：s = "", t = "y"
//输出："y"
//示例 3：
//
//输入：s = "a", t = "aa"
//输出："a"
//示例 4：
//
//输入：s = "ae", t = "aea"
//输出："a"
// 
//
//提示：
//
//0 <= s.length <= 1000
//t.length == s.length + 1
//s 和 t 只包含小写字母
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-the-difference
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 找不同 {
    @Test
    public void 找不同() {
        //方法一：哈希表
        //用哈希表记录第二个字符串中每个字符串出现的次数
        //再用第一个字符串每个出现的字符减去出现的次数，当次数为0时移出哈希表
        //最后哈希表中剩下的即为多出的字符

        //方法二：求和
        //每个字符的ASCII码进行求和，两个字符串求和后的差值，即为多出的那个字符的ASCII码值

        //方法三：位运算
        //既然只多出一个字符，其他都相同，可以使用异或运算找出那个多出的字符
        System.out.println("找不同:" + findTheDifference("abcd", "abcde"));
    }

    public char findTheDifference(String s, String t) {
        //位运算
        //既然第二个字符串只比第一个字符串多一个字符，其他字符都相同，那么可以使用异或运算，
        //相同字符异或结果为0，多出的字符异或结果就为此字符。
        char result = 0;
        for (int i = 0, l = s.length(); i < l; i++) {
            result ^= s.charAt(i);
        }

        for (int i = 0, l = t.length(); i < l; i++) {
            result ^= t.charAt(i);
        }
        return result;
    }
}
