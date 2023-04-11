package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
//
// 
//
//示例：
//
//s = "leetcode"
//返回 0
//
//s = "loveleetcode"
//返回 2
// 
//
//提示：你可以假定该字符串只包含小写字母。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 字符串中的第一个唯一字符 {
    @Test
    public void 字符串中的第一个唯一字符() {
        System.out.println("字符串中的第一个唯一字符:" + firstUniqChar("loveleetcode"));
    }

    public int firstUniqChar(String s) {
        //哈希映射
        //由于只存在小写字母，可用一个26长度的数组保存每个字符的数据
        //每个项由长度为2的数组，其中第一位代表每个字符出现的次数，第二位代表字符在字符串中第一个出现的位置
        //一次遍历完后，统计了每个字符在字符串中总共出现的频次，也记录了该字符在字符串中第一次出现的位置
        //最后在对此二维数组进行遍历，找出出现频次为1，且出现的位置最小的值
        //二维数组保存每个字符的数据
        //第一位代表每个字符出现的次数，第二位代表字符在字符串中第一个出现的位置

        //也可以使用一位数组，只统计字符出现的频次
        //统计完后再遍历一遍字符串，找出第一个出现频次为1的字符即可
        int[][] maps = new int[26][2];
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            int key = chars[i] - 'a';
            if (maps[key][0] == 0) {
                maps[key][1] = i;
            }
            maps[key][0]++;
        }

        //找出符合条件的结果
        int result = -1;
        for (int i = 0; i < 26; i++) {
            if (maps[i][0] == 1) {
                if (result == -1 || maps[i][1] < result) {
                    result = maps[i][1];
                }
            }
        }

        return result;
    }

}
