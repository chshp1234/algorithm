package com.study.algorithm.algorithms;

import java.util.ArrayList;
import java.util.List;

//有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
//
//示例：
//
//输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
//输出：1
//提示：
//
//words.length <= 100000
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/find-closest-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 单词距离 {
    public int findClosest(String[] words, String word1, String word2) {
        //找出并计算w1和w2出现的所有下标,并分别加入数组中
        //双指针，遍历下标数组list1和list2，找出最小的差值即可
        //若方法有重复调用需求，则第一次遍历时，可用哈希表保存所有字符串，和其对应的下标的列表；判断时只需要出对应的两个字符串的下标列表，进行判断
        int len = words.length;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (words[i].equals(word1)) {
                list1.add(i);
            } else if (words[i].equals(word2)) {
                list2.add(i);
            }
        }

        if (list1.size() == 0 || list2.size() == 0) {
            return len;
        }

        int result = Integer.MAX_VALUE;
        for (int i1 = 0, i2 = 0; i1 < list1.size() && i2 < list2.size(); ) {
            result = Math.min(result, Math.abs(list1.get(i1) - list2.get(i2)));

            //较小的下标的数组指针右移
            if (list1.get(i1) > list2.get(i2)) {
                i2++;
            } else {
                i1++;
            }
        }

        return result;
    }
}
