package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
//
//换句话说，第一个字符串的排列之一是第二个字符串的子串。
//
//示例1:
//
//输入: s1 = "ab" s2 = "eidbaooo"
//输出: True
//解释: s2 包含 s1 的排列之一 ("ba").
// 
//
//示例2:
//
//输入: s1= "ab" s2 = "eidboaoo"
//输出: False
// 
//
//注意：
//
//输入的字符串只包含小写字母
//两个字符串的长度都在 [1, 10,000] 之间
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/permutation-in-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 字符串的子排列 {
    @Test
    public void 字符串的子排列() {
        System.out.println("字符串的子排列:" + checkInclusion("abt", "eidtbtbaooo"));
    }

    public boolean checkInclusion(String s1, String s2) {

        //滑动窗口
        //定义双指针start，end。
        //我们规定start和end区间的子串是s1排列后的子串，这样再遍历s2的时候，只需要从end下标开始往后遍历即可。并且当区间长度为s1大小时，此区间的子串就为s1的排列之一了。
        //我们令start和end区间的子串是s1排列后的子串，这样再遍历s2的时候，只需要从end下标开始往后遍历即可。
        //因为只有小写字母，我们可以用26大小的数组counter统计s1中所有字符出现的频次；
        //在end右移的过程中，每遍历一个字符，就从频次数组counter中把相应的值减一；
        //若频次数组中某个字符相减后，频次为-1了，说明这个字符在start-end区间中多出了一个，跳出循环，等待start右移；
        //此时先判断区间大小是否为s1的长度，如果是，说明此子区间为s1的排列之一，如果不是，那么start就需要右移了；
        //start右移，每移动一个字符，就需要把当前字符从频次数组中出现的次数+1，直到到达end下标当前的字符处（就是把频次中为-1的那个字符重新加回来）；
        //如此步骤，直到找到子区间或者遍历结束，没有排列之一。

        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 > len2) {
            return false;
        }

        int[] counter = new int[26];
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int start = 0;
        int end = 0;

        //统计s1中字符的频次
        for (int i = 0; i < len1; i++) {
            counter[chars1[i] - 'a']++;
        }

        //这个i代表end下标应该遍历到的位置（就是start-end区间范围为s1的大小）
        for (int i = len1; i <= len2; i = len1 + start) {

            //end右移，直到到头（说明找到答案），或者某个字符的频次为-1时（说明次区间中此字符出现的数量多了一个）跳出循环
            while (end < i) {
                if (--counter[chars2[end] - 'a'] < 0) {
                    break;
                }
                end++;
            }
            //判断是否符合条件
            if (start + len1 == end) {
                return true;
            }
            //start右移
            //右移过程中，需把之前减去的频次加回来，直到找到第一个end下标处的字符（把多减去的那个字符加回来）后跳出循环
            //此时start不再右移，因为此时start-end区间中的字符都在s1的字符的频次范围内，所以是符合条件的
            while (start <= end) {
                counter[chars2[start] - 'a']++;
                if (chars2[end] == chars2[start++]) {
                    break;
                }
            }
            //end继续右移，从这个字符下一位开始
            end++;
        }
        //没找到
        return false;
    }
}
